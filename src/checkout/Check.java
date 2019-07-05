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

        for (Product product : this.products) {
            System.out.println(product.getPrice() + " product name");
            totalCost += product.getPrice();
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
                .filter(p -> p.getCategory() == category)
                .mapToInt(p -> p.getPrice())
                .reduce(0, (a, b) -> a + b);
    }

    public void setCostWithDiscount(int totalCostWithDiscount) {
        this.totalCostWithDiscount = totalCostWithDiscount;

    }

    public void addOffer(Offer offer) {
        availableOffers.add(offer);
    }
}
