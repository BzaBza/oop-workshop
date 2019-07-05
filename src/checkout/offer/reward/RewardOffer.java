package checkout.offer.reward;

import checkout.Check;
import checkout.offer.Offer;
import checkout.offer.condition.Condition;

import java.time.LocalDate;

public class RewardOffer extends Offer {
    public RewardOffer(LocalDate expiration){
        super(expiration);
    }


    @Override
    protected void setOffer(Check check) {

    }

    @Override
    public boolean isValid(Check check) {
        return true;
    }
}
