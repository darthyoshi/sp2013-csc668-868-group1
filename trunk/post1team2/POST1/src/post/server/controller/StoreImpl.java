package post.server.controller;

import post.model.ProductCatalog;
import post.model.Receipt;
import post.model.StoreDescription;
import post.model.Transaction;

/**
 * Provides a general implementation of a store.
 *
 * This simply connects the Store's interface to a specific group of relevant
 * objects provided during the constructor call.
 * 
 * @author woeltjen
 */
public class StoreImpl implements TransactionReceiver, Store {
    private StoreDescription storeDescription;
    private TransactionLog transactionLog;
    private ProductCatalog catalog;

    /**
     * Create a new store.
     * @param storeDescription a description of the store
     * @param transactionLog the transaction log the store should write to
     * @param catalog a catalog of products available through the store.
     */
    public StoreImpl(StoreDescription storeDescription, TransactionLog transactionLog, ProductCatalog catalog) {
        this.storeDescription = storeDescription;
        this.transactionLog = transactionLog;
        this.catalog = catalog;
    }

    @Override
    public StoreDescription getDescription() {
        return storeDescription;
    }

    @Override
    public ProductCatalog getCatalog() {
        return catalog;
    }

    @Override
    public Receipt recordTransaction(Transaction transaction) {
        return transactionLog.recordTransaction(transaction);
    }
}
