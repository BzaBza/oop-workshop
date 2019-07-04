package checkout;

import java.time.LocalDate;

public abstract class Offer {
    private final LocalDate expiration;

    public Offer(LocalDate expiration){
        this.expiration = expiration;
    }

    public void apply(Check check){
        LocalDate today = LocalDate.now();
        if (today.equals(expiration) || today.isBefore(expiration)) {
            setOffer(check);
        }
    }

    protected abstract void setOffer(Check check);
}
