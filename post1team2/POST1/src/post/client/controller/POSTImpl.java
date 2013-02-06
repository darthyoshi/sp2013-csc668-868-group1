package post.client.controller;

import post.model.Transaction;
import post.model.Receipt;
import post.model.ProductCatalog;
import post.server.controller.Store;
import post.model.StoreDescription;

/**
 *
 * @author Kay Choi
 */
public class POSTImpl implements POST {
    private Store store;

    public POSTImpl(Store store) {
        this.store = store;
    }

    
    /**
     * @return Receipt
     * @param transaction
     */
    public Receipt recordTransaction(Transaction transaction) {
        return store.recordTransaction(transaction);
    }

    /**
     * @return ProductCatalog
     */
    public ProductCatalog getCatalog() {
        return store.getCatalog();
    }

    /**
     * @return TransactionBuilder
     */
    public TransactionBuilder startTransaction() {
        return new TransactionBuilder(this, System.currentTimeMillis());
    }

    @Override
    public StoreDescription getDescription() {
        return store.getDescription();
    }
}
