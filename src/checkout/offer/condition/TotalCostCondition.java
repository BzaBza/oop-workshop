package checkout.offer.condition;

import checkout.Product;

public class TotalCostCondition implements Condition {

    @Override
    public boolean isSuitable(Product product) {
        return false;
    }
}
