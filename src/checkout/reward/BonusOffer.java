package checkout.reward;


import checkout.Check;
import checkout.TrademarkChecker;
import checkout.offer.Offer;

import java.time.LocalDate;

public class BonusOffer extends Offer {
    private TrademarkChecker trademarkChecker;
    private String currentProductName = "Milk";
    private int additionalPoints = 0;

    protected BonusOffer(LocalDate expiration) {
        super(expiration);
    }
    private Reward reward = new Reward() {
        @Override
        public void calcPoints(Check check) {
            additionalPoints = check.getAllProducts().stream()
                .filter(p -> p.name == currentProductName || trademarkChecker.checkIsSuitableTrademark(p.name))
                .mapToInt(p -> p.price)
                .reduce(0, (a, b) -> a + b);
        }
    };

    @Override
    protected void setOffer(Check check) {
        reward.calcPoints(check);
    }

    @Override
    public boolean isValid(Check check) {
        boolean isValid = false;
        if (additionalPoints > 0){
            isValid = true;
        }
        return isValid;
    }
}
