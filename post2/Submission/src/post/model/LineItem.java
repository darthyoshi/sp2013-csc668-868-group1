package post.model;

import java.io.Serializable;


/**
 * A LineItem describes the purchase of a specific product, at a specific 
 * quantity.
 * 
 * @author woeltjen
 */
public class LineItem implements Serializable {
    private ProductSpecification productSpec;
    private int quantity;

    /**
     * Create a new line item. This includes both the product purchased, and the
     * quantity purchased.
     *
     * @param productSpec
     * @param quantity
     */
    public LineItem(ProductSpecification productSpec, int quantity) {
        this.productSpec = productSpec;
        this.quantity = quantity;
    }
     
    /**
     * Get the product involved in this line item.
     *
     * @return the specification for the product purchased.
     */
    public ProductSpecification getProductSpec() {
        return productSpec;
    }

    /**
     * Get the quantity of the item purchased.
     *
     * @return the quantity of the item purchased
     */
    public int getQuantity() {
        return quantity;
    }
}
