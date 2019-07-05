package checkout.offer.condition;

import checkout.Check;
import checkout.Product;

public class TrademarkCondition implements Condition {

    private String trademark;

    public TrademarkCondition(String trademark) {
        this.trademark = trademark;
    }

    @Override

    public Product isSuitable(Check check) {
        Product isCheckHasThisProduct = null;
        for(int i = 0; i < check.getAllProducts().size(); i++){
            if (check.getAllProducts().get(i).getTrademark().equals(trademark)){
                isCheckHasThisProduct = check.getAllProducts().get(i);
            }
        }
        return  isCheckHasThisProduct;
    }
}
