package checkout;

import java.time.LocalDate;

public class FactorByCategoryOffer extends Offer {
    final Category category;
    final int factor;

    public FactorByCategoryOffer(Category category, int factor, LocalDate expiration) {
        super(expiration);
        this.category = category;
        this.factor = factor;
    }

    @Override
    public void setOffer(Check check) {
        int points = check.getCostByCategory(category);
        check.addPoints(points * (factor - 1));
        if (points != 0) {
            check.addOffer(this);
        }
    }
}
