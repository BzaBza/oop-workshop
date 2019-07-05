package checkout.offer.condition;

import checkout.Product;

public class NameCondition implements Condition {
    private String name;
    public NameCondition(String name) {
        this.name = name;
    }

    @Override
    public boolean isSuitable(Product product) {
        return  product.getName().equals(name);
    }
}
