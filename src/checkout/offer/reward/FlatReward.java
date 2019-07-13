package checkout.offer.reward;

import checkout.Check;
import checkout.Product;

public class FlatReward implements Reward {
    @Override
    public void getReward (Check check, Product product) {
        check.addPoints(product.getPrice(), product);
    }
}
