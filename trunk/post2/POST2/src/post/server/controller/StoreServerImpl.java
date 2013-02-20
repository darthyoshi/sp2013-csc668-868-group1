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
 * @author steven
 */
public class StoreServerImpl implements TransactionReceiver, Store {
    private StoreDescription storeDescription;
    private SalesLog salesLog;
    private ProductCatalog catalog;
    private TransactionReceiver paymentProcessor;

    /**
     * Create a new store.
     * @param storeDescription a description of the store
     * @param salesLog the saleslog the store should write to
     * @param catalog a catalog of products available through the store.
     * @param paymentProcessor returns a receipt object or null if the payment is incorrect or declined
     */
    public StoreServerImpl(StoreDescription storeDescription, SalesLog salesLog, ProductCatalog catalog, PaymentProcessor paymentProcessor) {
        this.storeDescription = storeDescription;
        this.salesLog = salesLog;
        this.catalog = catalog;
        this.paymentProcessor = paymentProcessor;
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
        
        return salesLog.recordTransaction(transaction);
    }
}
