package checkout.offer.condition;

import checkout.Product;

public class CategoryCondition implements Condition {
    private String category;
    public CategoryCondition(String category){
        this.category = category;
    }
    @Override
    public boolean isSuitable(Product product) {
        return product.getCategory().equals(category);
    }
}
