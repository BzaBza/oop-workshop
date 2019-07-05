package checkout;

public class Product {
    private String name;
    private int price;
    private Category category;
    private String trademark;

    public Product(int price, String name, Category category, String trademark) {
        this.price = price;
        this.name = name;
        this.category = category;
        this.trademark = trademark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTrademark() {
        return trademark;
    }

    public void setTrademark(String trademark) {
        this.trademark = trademark;
    }
}
