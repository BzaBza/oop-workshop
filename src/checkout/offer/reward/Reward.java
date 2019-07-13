package checkout.offer.reward;

import checkout.Check;
import checkout.Product;

public interface Reward {
    void getReward(Check check, Product product);
}
