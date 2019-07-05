package checkout.offer.discount;

import checkout.Check;
import checkout.Product;
import checkout.offer.Offer;
import checkout.offer.condition.Condition;

import java.time.LocalDate;

public class DiscountOffer extends Offer {
    private double discount;
    private Condition condition;

    public DiscountOffer(LocalDate expiration, Condition condition, int discount) {
        super(expiration);
        this.discount = discount * 0.01;
        this.condition = condition;
    }

    @Override
    protected void setOffer(Check check) {
        for (Product product : check.getAllProducts()) {
            if (condition.isSuitable(product)) {
                int price = product.getPrice();
                product.setPrice((int)(price * discount));
            }
        }
    }

    @Override
    public boolean isValid(Check check) {
        return true;
    }
}
