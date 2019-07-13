package checkout;

import checkout.offer.Offer;

import java.util.ArrayList;
import java.util.List;

public class Check {
    private List<Product> products = new ArrayList<>();
    private ArrayList<Offer> availableOffers = new ArrayList<>();
    private int points = 0;
    private int presentPrice = 0;
    private double discount;
    private Product presentProduct;

    public int getTotalCost() {
        int totalCost = 0;

        if (this.discount > 0) {
            for (Product product : this.products) {
                totalCost += product.getPrice();
            }
            totalCost = (int) (totalCost - discount);
            if (presentPrice > 0){
                totalCost = totalCost - presentProduct.getPrice() + presentPrice;
            }
        } else {
            for (Product product : this.products) {
                totalCost += product.getPrice();
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

    public void addPresent(Product product, int presentPrice) {
        products.add(product);
        this.presentProduct = product;
        this.presentPrice = presentPrice;
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public void useDiscount(double discount, Product product) {
        int productsCount;
        productsCount = (int) products.stream().filter(p -> p.getName() == product.getName()).count();
        this.discount = (product.getPrice() - product.getPrice() * discount) * productsCount;
    }

    public void addPoints(int points, Product product) {
        products.forEach(p -> {
                    if (p.getName().equals(product.getName())) {
                        this.points += points;
                    }
                }
        );
    }
}
