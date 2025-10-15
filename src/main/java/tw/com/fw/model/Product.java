package tw.com.fw.model;

public class Product {
	private int ProductId;
	private String name;
	private String description;
	private int price;
	private int stock;
	private String imageUrl;
	

    public Product() {}

    public Product(int ProductId, String name, String description, int price, int stock, String imageUrl) {
        this.ProductId = ProductId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.imageUrl = imageUrl;
    }

    public int getId() { return ProductId; }
    public void setId(int ProductId) { this.ProductId = ProductId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    @Override
    public String toString() {
        return "Product [ProductId=" + ProductId + ", name=" + name + ", price=" + price +
               ", stock=" + stock + ", description=" + description + "]";
    }
}