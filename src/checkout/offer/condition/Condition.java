package checkout.offer.condition;

import checkout.Product;

public interface Condition {
     boolean isSuitable(Product product);
}

