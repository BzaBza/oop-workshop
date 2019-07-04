package checkout;

public class Product {
    public String name;
    public int price;
    Category category;

    public Product(int price, String name, Category category) {
        this.price = price;
        this.name = name;
        this.category = category;
    }

    public Product(int price, String name) {
        this(price, name, null);
    }
}
