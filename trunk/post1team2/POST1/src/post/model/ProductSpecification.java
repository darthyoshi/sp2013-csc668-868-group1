package post.model;

/**
 * Describes relevant information about a specific product in inventory.
 * @author woeltjen
 */
public class ProductSpecification {
    private String upc;
    private String description;
    private float price;

    /**
     * Create a new product specification
     * @param upc the products 4-digit locator
     * @param description a text description of the product
     * @param price the products price, in dollars
     */
    public ProductSpecification(String upc, String description, float price) {
        this.upc = upc;
        this.description = description;
        this.price = price;
    }

    /**
     * Get the product's UPC code
     *
     * @return the value of upc
     */
    public String getUpc() {
        return upc;
    }

    /**
     * Get a short human-readable description of the product
     *
     * @return the value of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the price of the product
     *
     * @return the value of price
     */
    public float getPrice() {
        return price;
    }
}
