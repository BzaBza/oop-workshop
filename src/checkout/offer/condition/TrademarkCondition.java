package checkout.offer.condition;

import checkout.Product;

public class TrademarkCondition implements Condition {

    private String trademark;

    public TrademarkCondition(String trademark) {
        this.trademark = trademark;
    }

    @Override
    public boolean isSuitable(Product product) {
        return product.getTrademark().equals(trademark);
    }
}
