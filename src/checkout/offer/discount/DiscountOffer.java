package checkout.offer.discount;

import checkout.Check;
import checkout.Product;
import checkout.offer.Offer;
import checkout.offer.condition.Condition;

import java.time.LocalDate;

public class DiscountOffer extends Offer {
    private double discount;
    private Condition condition;

    public DiscountOffer(LocalDate expiration, Condition condition, int discount) {
        super(expiration);
        this.discount = discount * 0.01;
        this.condition = condition;
    }

    @Override
    protected void setOffer(Check check) {
        Product product = condition.isSuitable(check);
        int productsCount;
        if (product != null) {
            check.useDiscount(discount, product);
            productsCount = (int) check.getAllProducts().stream().filter(p -> p.getName() == product.getName()).count();
            if (productsCount == 2){
                check.addPresent(product, 1);
            }
        }
        System.out.println(check.getAllProducts());
    }

    @Override
    public boolean isValid(Check check) {
        return check.getAllProducts().size() > 0 && check.getTotalCost() > 0;
    }
}
