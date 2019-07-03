package checkout;

public class SpecialOffer extends Offer {
    public final int points = 2;
    public final String trademark;
    public final String productName;

    public SpecialOffer(String trademark, String productName) {
        this.trademark = trademark;
        this.productName = productName;
    }
    public SpecialOffer(String trademark) {
        this.trademark = trademark;
        this.productName = null;
    }

    @Override

    public void apply(Check check) {
        check.getPointsByProductNameOrTrademark(trademark, productName);
    }
}
