package post.server.controller;

import post.model.ProductCatalog;
import post.model.StoreDescription;

/**
 * Represents a Store. In addition to receiving transactions, a store also
 * provides information about itself (description, product catalog).
 */
public interface Store extends TransactionReceiver {

    /**
     * Get a description of this store
     * @return
     */
    public StoreDescription getDescription();

    /**
     * Get a catalog of products available through this store.
     * @return       ProductCatalog
     */
    public ProductCatalog getCatalog();
}
