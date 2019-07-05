package checkout.offer.reward;

import checkout.Check;

public class FlatReward implements Reward {
    @Override
    public int getReward(Check check) {
        return 0;
    }
}
