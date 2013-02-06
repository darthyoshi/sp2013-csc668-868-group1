package post.client.controller;

import post.model.Transaction;
import post.model.Payment;

/**
 *
 * @author Kay Choi
 */
public class TransactionBuilder {

    private java.util.HashMap<String, Integer> items;
    private long time;
    private POST post;

    public TransactionBuilder(POST parent, long timestamp) {
        items = new java.util.HashMap<String, Integer>();
        time = timestamp;
        post = parent;
    }

    /**
     * @param name
     */
    public void setCustomer(String name) {
    }

    /**
     * @return true if the item was added (implies upc was valid)
     * @param upc
     * @param quantity
     */
    public boolean addLineItem(String upc, int quantity) {
        items.put(upc, quantity);
        return true;
    }
    
    public void removeLineItem(String upc) {
        items.remove(upc);
    }


    /**
     * @return Transaction
     * @param payment
     */
    public Transaction completeSale(Payment payment) {
        // TODO: Include customer name, store
        return new Transaction("", time, payment, items, null);
    }
}
