package checkout.offer;

import checkout.Check;

import java.time.LocalDate;

public class DiscountOffer extends Offer {
    private final String productName;
    private final int discount;
    private int totalPriceWithDiscount;

    public DiscountOffer(String productName, int discount, LocalDate expiration) {
        super(expiration);
        this.productName = productName;
        this.discount = discount;
    }

    @Override


    public void setOffer(Check check) {
        check.setCostWithDiscount(totalPriceWithDiscount);
    }

    @Override

    public boolean isValid(Check check) {
        boolean isValid = false;
        if (discount > 0) {
            int discountProductsPrice = check.getAllProducts().stream()
                    .filter(p -> p.name.equals(productName))
                    .mapToInt(p -> p.price)
                    .reduce(0, (a, b) -> a + b);
            totalPriceWithDiscount = discountProductsPrice * discount / 100;
            isValid = true;
        }
        return isValid;
    }
}
