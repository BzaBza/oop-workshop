package checkout.offer.condition;

import checkout.Check;
import checkout.Product;

public class TotalCostCondition implements Condition {
    private int totalCost;
    private String name;

    public TotalCostCondition(int declaredTotalCost, String name){
        this.totalCost = declaredTotalCost;
        this.name = name;
    }

    @Override
    public Product isSuitable(Check check) {
        Product isCheckHasThisProduct = null;
        if(check.getTotalCost() >= totalCost){
            for(int i = 0; i < check.getAllProducts().size(); i++) {
                if (check.getAllProducts().get(i).getName().equals(name)) {
                    isCheckHasThisProduct = check.getAllProducts().get(i);
                }
            }
        }
        return  isCheckHasThisProduct;
    }
}
