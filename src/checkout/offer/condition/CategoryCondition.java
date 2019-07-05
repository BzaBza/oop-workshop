package checkout.offer.condition;

import checkout.Category;
import checkout.Check;
import checkout.Product;

public class CategoryCondition implements Condition {
    private Category category;
    public CategoryCondition(Category category){
        this.category = category;
    }
    @Override
    public Product isSuitable(Check check) {
        Product isCheckHasThisProduct = null;
        for(int i = 0; i < check.getAllProducts().size(); i++){
            if (check.getAllProducts().get(i).getCategory().equals(category)){
                isCheckHasThisProduct = check.getAllProducts().get(i);
            }
        }
        return  isCheckHasThisProduct;
    }
}
