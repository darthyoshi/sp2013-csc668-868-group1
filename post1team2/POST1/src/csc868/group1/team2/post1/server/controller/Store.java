package csc868.group1.team2.post1.server.controller;

/**
 * The store is the main hub for interactions. Multiple POSTs may connect to 
 * a store and report transactions, or get necessary information (product 
 * catalog etc) to/from the store.
 * 
 * @author woeltjen
 */
public interface Store {
    /**
     * Record that a sale has occurred.
     * @param transaction (...model.Transaction) the transaction that has completed
     */
    public void recordSale(Object transaction); 
    
    /**
     * Get the product catalog supported by this store.
     * @return (...model.ProductCatalog)
     */
    public Object getCatalog();
    
    /** 
     * Check to see if a given payment is valid. This is primarily used  
     * to validate credit card transactions.
     * @param payment (...model.Payment)
     * @return true if the payment is valid; otherwise false
     */
    public boolean validatePayment(Object payment);
}
