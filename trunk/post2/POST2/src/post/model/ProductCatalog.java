package post.model;

import java.util.*;

/**
 * A catalog of all available products within the store. Useful for identifying
 * products by UPC, or for getting a full listing of products available.
 * @author woeltjen
 */
public class ProductCatalog {
    private Map<String, ProductSpecification> productMap =
            new HashMap<String, ProductSpecification>();

    /**
     * Create a new product catalog for the specified group of products.
     * @param products the products available
     */
    public ProductCatalog(Collection<ProductSpecification> products) {
        for (ProductSpecification p : products) {
            productMap.put(p.getUpc(), p);
        }
    }

    /**
     * Look up a product by its UPC. Note that the return value may be 
     * null if there is no product with the specified UPC in this catalog.
     * @return ProductSpecification
     * @param upc
     */
    public ProductSpecification lookup(String upc) {
        return productMap.get(upc);
    }

    /**
     * Get a list of all products available in this catalog.
     * @return List<ProductSpecification>
     */
    public List<ProductSpecification> getProducts() {
        List<ProductSpecification> copy = new ArrayList<ProductSpecification>();
        copy.addAll(productMap.values());
        return copy;
    }
}
