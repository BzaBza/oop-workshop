package checkout.offer;

import checkout.Check;

import java.time.LocalDate;

public class AnyGoodsOffer extends Offer {
    private final int totalCost;
    private final int points;

    public AnyGoodsOffer(int totalCost, int points, LocalDate expiration) {
        super(expiration);
        this.totalCost = totalCost;
        this.points = points;
    }

    @Override

    public void setOffer(Check check) {
        if (totalCost <= check.getTotalCost()) {
            check.addPoints(points);
        }
    }

    @Override

    public boolean isValid(Check check){
        boolean isValid = false;
        if (points > 0 && totalCost > 0){
            isValid = true;
        }
        return isValid;
    }
}
