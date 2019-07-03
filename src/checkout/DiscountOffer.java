package checkout;

public class DiscountOffer extends Offer {
    public final String productName;

    public DiscountOffer(String productName) {
        this.productName = productName;
    }

    @Override

    public void apply(Check check) {
        check.getCostWithDiscountOffer(productName);
    }
}
