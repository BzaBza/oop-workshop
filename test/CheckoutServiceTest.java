import checkout.*;
import checkout.offer.Offer;
import checkout.offer.condition.CategoryCondition;
import checkout.offer.condition.NameCondition;
import checkout.offer.condition.TotalCostCondition;
import checkout.offer.condition.TrademarkCondition;
import checkout.offer.discount.DiscountOffer;
import checkout.offer.reward.RewardOffer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CheckoutServiceTest {

    private Product milk_7;
    private CheckoutService checkoutService;
    private Product bred_3;
    private LocalDate today;
    private LocalDate specificDateBeforeToday;
    private LocalDate specificDateAfterToday;
    private Product productWithDiscount;

    @BeforeEach
    void setUp() {
        checkoutService = new CheckoutService();
        checkoutService.openCheck();

        milk_7 = new Product(7, "Milk", Category.MILK, "borjomi");
        bred_3 = new Product(3, "Bred", Category.BRED,"lacalut");
        today = LocalDate.now();
        specificDateBeforeToday = LocalDate.of(2017, Month.NOVEMBER, 30);
        specificDateAfterToday = today.plusYears(100);
    }

    @Test
    void closeCheck__withOneProduct() {
        checkoutService.addProduct(milk_7);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalCost(), is(7));
    }

    @Test
    void closeCheck__withTwoProducts() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalCost(), is(10));
    }

    @Test
    void addProduct__whenCheckIsClosed__opensNewCheck() {
        checkoutService.addProduct(milk_7);
        Check milkCheck = checkoutService.closeCheck();
        assertThat(milkCheck.getTotalCost(), is(7));

        checkoutService.addProduct(bred_3);
        Check bredCheck = checkoutService.closeCheck();
        assertThat(bredCheck.getTotalCost(), is(3));
    }

    @Test
    void closeCheck__calcTotalPoints() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(10));
    }

    @Test
    void useOffer__beforeCloseCheck__withTwoProducts() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        Offer offer = new RewardOffer(today, new NameCondition("Milk"));
        checkoutService.useOffer(offer);
        checkoutService.addProduct(bred_3);

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(31));
    }

    @Test
    void expiration__ofOffer__isExpired() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);

        Offer offer = new RewardOffer(specificDateBeforeToday, new NameCondition("Milk"));
        checkoutService.useOffer(offer);

        checkoutService.addProduct(bred_3);

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(17));
    }

    @Test
    void offer__date__today() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        Offer offer = new RewardOffer(today, new NameCondition("Milk"));
        checkoutService.useOffer(offer);

        checkoutService.addProduct(bred_3);

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(31));
    }

    @Test
    void check__expirationOfferData__notExpired() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        Offer offer = new RewardOffer(specificDateAfterToday, new NameCondition("Milk"));
        checkoutService.useOffer(offer);

        checkoutService.addProduct(bred_3);

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(31));
    }

    @Test
    void addPoints__byProductName__getMorePointsByMilk() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);

        checkoutService.addProduct(bred_3);
        Offer offer = new RewardOffer(specificDateAfterToday, new NameCondition("Milk"));
        checkoutService.useOffer(offer);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(31));
    }
    @Test
    void addPoints__byTrademark__getMorePointsByMilk() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);

        checkoutService.addProduct(bred_3);
        Offer offer = new RewardOffer(specificDateAfterToday, new TrademarkCondition("borjomi"));
        checkoutService.useOffer(offer);

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(31));
    }

    @Test
    void discountOffer__correct__productName() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        Offer offer = new DiscountOffer(specificDateAfterToday, new NameCondition("Milk"), 50);

        checkoutService.useOffer(offer);

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalCost(), is(6));
    }
    @Test
    void discountOffer__incorrect__productName() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        Offer offer = new DiscountOffer(specificDateAfterToday, new NameCondition("Meat"), 50);

        checkoutService.useOffer(offer);

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalCost(), is(10));
    }

    @Test
    void discountOffer__correct__category() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        Offer z = new DiscountOffer(specificDateAfterToday, new CategoryCondition(Category.MILK), 50);

        checkoutService.useOffer(z);

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalCost(), is(6));
    }

    @Test
    void discountOffer__correct__totalCost() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        Offer offer = new DiscountOffer(specificDateAfterToday, new TotalCostCondition(5, "Milk"), 50);

        checkoutService.useOffer(offer);

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalCost(), is(6));
    }

    @Test
    void discountOffer__correctTrademark__decreesTotalCost() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        Offer offer = new DiscountOffer(specificDateAfterToday, new TrademarkCondition("borjomi"), 50);

        checkoutService.useOffer(offer);

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalCost(), is(6));
    }
    @Test
    void rewardOffer__correctTrademark__FlatReward() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        Offer offer = new RewardOffer(specificDateAfterToday, new TrademarkCondition("borjomi"));

        checkoutService.useOffer(offer);

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(17));
    }
    @Test
    void rewardOffer__correctTrademark__factorReward() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        Offer offer = new RewardOffer(specificDateAfterToday, new TotalCostCondition(6, "Milk"));

        checkoutService.useOffer(offer);

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(24));
    }
    @Test
    void discountOffer__correctTrademark__getThreeMilks() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        Offer offer = new DiscountOffer(specificDateAfterToday, new TrademarkCondition("borjomi"), 50);

        checkoutService.useOffer(offer);

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalCost(), is(11));
    }
}
