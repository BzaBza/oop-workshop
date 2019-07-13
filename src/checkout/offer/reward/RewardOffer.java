package checkout.offer.reward;

import checkout.Check;
import checkout.Product;
import checkout.offer.Offer;
import checkout.offer.condition.*;

import java.time.LocalDate;

public class RewardOffer extends Offer {
    private Condition condition;
    private TrademarkCondition trademarkCondition;

    public RewardOffer(LocalDate expiration, Condition condition) {
        super(expiration);
        this.condition = condition;
    }

    @Override
    protected void setOffer(Check check) {
        Product product = condition.isSuitable(check);
        if (product != null) {
            if (condition instanceof TrademarkCondition || condition instanceof NameCondition){
                new FlatReward().getReward(check, product);
            }
            if (condition instanceof TotalCostCondition || condition instanceof CategoryCondition){
                new FactorReward().getReward(check, product);
            }
        }
    }

    @Override
    public boolean isValid(Check check) {
        return check.getAllProducts().size() > 0 && check.getTotalCost() > 0;
    }
}
