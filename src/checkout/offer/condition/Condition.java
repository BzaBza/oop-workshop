package checkout.offer.condition;


import checkout.Check;
import checkout.Product;

public interface Condition {
     Product isSuitable(Check check);
}

