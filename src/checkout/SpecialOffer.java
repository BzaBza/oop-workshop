package checkout;

public class SpecialOffer extends Offer {
    public final int totalCost;
    public final int points = 2;
    public final String specialTrademark = "KvasTaras";
    public final String trademark;

    public SpecialOffer(int totalCost, String trademark) {
        this.totalCost = totalCost;
        this.trademark = trademark;
    }

    @Override

    public void apply(Check check) {
        if (specialTrademark.equals(trademark) && totalCost >= 1){
            check.addPoints(points);
        }
    }
}
