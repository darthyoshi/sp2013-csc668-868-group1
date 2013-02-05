package csc868.group1.team2.post1.server.controller;

import csc868.group1.team2.post1.server.view.TransactionLog;

/**
 * Presents a general-purpose implementation of a store.
 *
 * This simply connects to a transaction log and a product catalog, as 
 * provided during the constructor.
 * 
 * @author woeltjen
 */
public class StoreImpl implements Store {
    private Object catalog;
    private TransactionLog transactionLog;

    public StoreImpl(Object catalog, TransactionLog transactionLog) {
        this.catalog = catalog;
        this.transactionLog = transactionLog;
    }
    
    @Override
    public void recordSale(Object transaction) {
        transactionLog.recordTransaction(transaction);
        // TODO: What if it can't be recorded?
    }

    @Override
    public Object getCatalog() {
        return catalog;
    }

    @Override
    public boolean validatePayment(Object payment) {
        // Assume everything is valid.
        return true;
    }
    
}
