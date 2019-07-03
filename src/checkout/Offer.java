package checkout;

import java.time.LocalDate;

public abstract class Offer {

    public abstract void apply(Check check);

    public boolean isOfferExpired(LocalDate date) {
        boolean isExpiredOffer = false;
        LocalDate today = LocalDate.now();
        if (!today.equals(date) && !today.isBefore(date)) {
            isExpiredOffer = true;
        }
        return isExpiredOffer;
    }
}
