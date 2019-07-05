package checkout.offer.condition;

import checkout.Check;
import checkout.Product;

public class NameCondition implements Condition {
    private String name;

    public NameCondition(String name) {
        this.name = name;
    }

    @Override
    public Product isSuitable(Check check) {
        Product isCheckHasThisProduct = null;
        for(int i = 0; i < check.getAllProducts().size(); i++){
            if (check.getAllProducts().get(i).getName().equals(name)){
                isCheckHasThisProduct = check.getAllProducts().get(i);
            }
        }
        return  isCheckHasThisProduct;
    }
}
