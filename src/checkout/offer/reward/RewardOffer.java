package checkout.offer.reward;

import checkout.Check;
import checkout.Product;
import checkout.offer.Offer;
import checkout.offer.condition.Condition;

import java.time.LocalDate;

public class RewardOffer extends Offer {
    private Condition condition;

    public RewardOffer(LocalDate expiration, Condition condition) {
        super(expiration);
        this.condition = condition;
    }


    @Override
    protected void setOffer(Check check) {
        Product product = condition.isSuitable(check);
        if (product != null) {

        }
    }

    @Override
    public boolean isValid(Check check) {
        return true;
    }
}
