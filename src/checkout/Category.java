package checkout;

import java.time.LocalDate;

public enum Category {
    MILK;

    public abstract static class Offer {
        private final LocalDate expiration;

        Offer(LocalDate expiration){
            this.expiration = expiration;
        }

        void apply(Check check){
            LocalDate today = LocalDate.now();
            if (today.equals(expiration) || today.isBefore(expiration) && isValid(check)) {
                setOffer(check);
            }
        }

        protected abstract void setOffer(Check check);

        public abstract boolean isValid(Check check);

    }
}
