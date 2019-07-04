package checkout;

import java.time.LocalDate;

public class PointsEditor extends Offer {
    private TrademarkChecker trademarkChecker;
    private String currentProductName = "Milk";
    private String descriptionProduc;

    public PointsEditor(String descriptionProduc,LocalDate expiration) {
        super(expiration);
        trademarkChecker = new TrademarkChecker();
        this.descriptionProduc = descriptionProduc;
    }

    private int setAdditionalPoints(Check check) {
        int additionalPoints;
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
}
