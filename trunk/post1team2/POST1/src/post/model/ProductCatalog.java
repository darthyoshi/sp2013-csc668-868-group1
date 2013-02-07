package post.model;

import java.util.Collection;
import java.util.Collections;
import java.util.List;



/**
 * Class ProductCatalog
 */
public class ProductCatalog {

    /**
     * Create a new product catalog for the specified group of products.
     * @param products the products available
     */
    public ProductCatalog(Collection<ProductSpecification> products) {
    }

    /**
     * Look up a product by its UPC. Note that the return value may be 
     * null if there is no product with the specified UPC in this catalog.
     * @return ProductSpecification
     * @param upc
     */
    public ProductSpecification lookup(String upc) {
        return null;
    }

    /**
     * Get a list of all products available in this catalog.
     * @return List<ProductSpecification>
     */
    public List<ProductSpecification> getProducts() {
        return Collections.emptyList();
    }
}
