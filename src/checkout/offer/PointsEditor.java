package checkout.offer;

import checkout.Check;
import checkout.TrademarkChecker;

import java.time.LocalDate;

public class PointsEditor extends Offer {
    private TrademarkChecker trademarkChecker;
    private String currentProductName = "Milk";
    private int additionalPoints = 0;

    public PointsEditor(String descriptionProduc,LocalDate expiration) {
        super(expiration);
        trademarkChecker = new TrademarkChecker();
    }

    private int setAdditionalPoints(Check check) {
        additionalPoints = check.getAllProducts().stream()
                .filter(p -> p.name == currentProductName || trademarkChecker.checkIsSuitableTrademark(p.name))
                .mapToInt(p -> p.price)
                .reduce(0, (a, b) -> a + b);
        return additionalPoints;
    }

    @Override

    public void setOffer(Check check) {
        int additionalPoints = setAdditionalPoints(check);
        check.addPoints(additionalPoints);
    }

    @Override

    public boolean isValid(Check check){
//        boolean isValid = false;
//        if (additionalPoints > 0){
//            isValid = true;
//        }
        return true;
    }
}
