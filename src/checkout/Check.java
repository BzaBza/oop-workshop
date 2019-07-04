package checkout;

import checkout.offer.Offer;

import java.util.ArrayList;
import java.util.List;

public class Check {
    private List<Product> products = new ArrayList<>();
    private ArrayList<Offer> availableOffers = new ArrayList<>();
    private int points = 0;
    private int totalCostWithDiscount;

    public int getTotalCost() {
        int totalCost = 0;
        if (totalCostWithDiscount > 0) {
            totalCost = totalCostWithDiscount;
        } else {
            for (Product product : this.products) {
                totalCost += product.price;
            }
        }
        return totalCost;
    }

    void addProduct(Product product) {
        products.add(product);
    }

    public int getTotalPoints() {
        return getTotalCost() + points;
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public int getCostByCategory(Category category) {
        return products.stream()
                .filter(p -> p.category == category)
                .mapToInt(p -> p.price)
                .reduce(0, (a, b) -> a + b);
    }

    public void setCostWithDiscount(int totalCostWithDiscount) {
        this.totalCostWithDiscount = totalCostWithDiscount;

    }

    public void addOffer(Offer offer) {
        availableOffers.add(offer);
    }
}
