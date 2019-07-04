package checkout.discount;

import checkout.Check;
import checkout.offer.Offer;

import java.time.LocalDate;

public class DiscountOffer extends Offer {
    protected DiscountOffer(LocalDate expiration) {
        super(expiration);
    }

    @Override
    protected void setOffer(Check check) {

    }

    @Override
    public boolean isValid(Check check) {
        return false;
    }
}
