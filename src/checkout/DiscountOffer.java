package checkout;

import java.time.LocalDate;

public class DiscountOffer extends Offer {
    public final String productName;

    public DiscountOffer(String productName, LocalDate expiration) {
        super(expiration);
        this.productName = productName;
    }

    @Override

    public void setOffer(Check check) {
        check.getCostWithDiscountOffer(productName);
    }
}
