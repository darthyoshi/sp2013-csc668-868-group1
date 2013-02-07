package post.model;

/**
 * Class ProductSpecification
 */
public class ProductSpecification {

    //
    // Fields
    //
    private String upc;
    private String description;
    private float price;

    public ProductSpecification(String upc, String description, float price) {
        this.upc = upc;
        this.description = description;
        this.price = price;
    }

    /**
     * Get the value of upc
     *
     * @return the value of upc
     */
    public String getUpc() {
        return upc;
    }

    /**
     * Get the value of description
     *
     * @return the value of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the value of price
     *
     * @return the value of price
     */
    public float getPrice() {
        return price;
    }
}
