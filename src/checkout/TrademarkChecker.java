package checkout;

public class TrademarkChecker {
    private String[] trademarks = {"Mivina", "KvasTaras"};

    public boolean checkIsSuitableTrademark(String currentTrademark){
        boolean isSuitableTrademark = false;
        for (String trademark : trademarks) {
            if(trademark == currentTrademark){
                isSuitableTrademark = true;
            }
        }
        return isSuitableTrademark;
    }
}
