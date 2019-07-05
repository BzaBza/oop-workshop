package checkout.offer.discount;

import checkout.Check;

public interface DiscountRule {
    int calculateDiscount(Check check);
}
