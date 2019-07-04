package checkout.discount;

import checkout.Check;

public class DiscountByPercent implements Discount{
    private int discount;
    private String productName;
    private int totalPriceWithDiscount;

    public boolean isValid(Check check) {
        boolean isValid = false;
        if (discount > 0) {
            int discountProductsPrice = check.getAllProducts().stream()
                    .filter(p -> p.name.equals(productName))
                    .mapToInt(p -> p.price)
                    .reduce(0, (a, b) -> a + b);
            totalPriceWithDiscount = discountProductsPrice * discount / 100;
            isValid = true;
        }
        return isValid;
    }
    @Override
    public void getDiscount() {

    }
}
