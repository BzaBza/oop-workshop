package checkout.offer;

import checkout.Check;

import java.time.LocalDate;

public abstract class Offer {
    private final LocalDate expiration;
    private boolean condition;

    protected Offer(LocalDate expiration){
        this.expiration = expiration;
    }

    public void apply(Check check){
        LocalDate today = LocalDate.now();
        if (today.equals(expiration) || today.isBefore(expiration) && isValid(check)) {
            setOffer(check);
        }
    }

    protected abstract void setOffer(Check check);

    public abstract boolean isValid(Check check);

}
