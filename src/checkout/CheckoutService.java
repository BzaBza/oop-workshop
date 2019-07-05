package checkout;

import checkout.offer.Offer;

import java.time.LocalDate;
import java.util.ArrayList;

public class CheckoutService {

    private ArrayList<Offer> offers = new ArrayList<>();
    private Check check;

    public void openCheck() {
        check = new Check();
    }

    public void addProduct(Product product) {
        if (check == null) {
            openCheck();
        }
        check.addProduct(product);
    }

    public Check closeCheck() {
        Check closedCheck = check;
        for (Offer offer : offers) {
            offer.apply(check);
        }
        offers.clear();
        check = null;
        return closedCheck;
    }

    public void useOffer(Offer offer) {
        offers.add(offer);
    }
}
