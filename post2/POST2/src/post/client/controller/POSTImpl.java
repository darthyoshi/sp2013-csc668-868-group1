package post.client.controller;

import post.model.ProductCatalog;
import post.model.Receipt;
import post.model.StoreDescription;
import post.model.Transaction;
import post.server.controller.Store;

/**
 * Class POSTImpl implements the POST interface to instantiate
 * @author Kay Choi
 */
public class POSTImpl implements POST {
    private Store store;

    /**
     * Class constructor.
     * @param store the Store that the POSTImpl belongs to
     */
    public POSTImpl(Store store) {
        this.store = store;
    }

    /**
     * @return Receipt
     * @param transaction
     */
    @Override
    public Receipt recordTransaction(Transaction transaction) {
        return store.recordTransaction(transaction);
    }

    /**
     * @return ProductCatalog
     */
    @Override
    public ProductCatalog getCatalog() {
        return store.getCatalog();
    }

    /**
     * @return TransactionBuilder
     */
    @Override
    public TransactionBuilder startTransaction() {
        return new TransactionBuilder(this, System.currentTimeMillis());
    }

    @Override
    public StoreDescription getDescription() {
        return store.getDescription();
    }
}
