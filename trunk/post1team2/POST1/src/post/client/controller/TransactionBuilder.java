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
    private float amountDue;
    private String customer;

    /**
     * Create a new transaction builder. The specified POST is used for 
     * validation when needed (for instance, confirming valid UPCs)
     * 
     * Note that the timestamp is in milliseconds since the UNIX epoch 
     * (January 1, 1970 00:00:00 GMT)
     * 
     * @param parent the POST used for validation
     * @param timestamp the time (in ms since UNIX epoch)
     */
    public TransactionBuilder(POST parent, long timestamp) {
        items = new java.util.HashMap<String, Integer>();
        time = timestamp;
    }

    /**
     * Set the name of the customer involved in this transaction.
     * @param name the name of the customer
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
        // TODO: Track amount due
        // TODO: What if same UPC is encountered twice? Add quantity?
        return true;
    }
    
    public void removeLineItem(String upc) {
        items.remove(upc);
    }

    /**
     * Issue payment to complete a transaction. This will return a full 
     * Transaction object; note that this transaction is not necessary 
     * recorded to the originating POST.
     * @return Transaction
     * @param payment the specific payment for this transaction
     */
    public Transaction completeSale(Payment payment) {
        // TODO: Validate that payment matches amount due?
        return new Transaction(customer, time, payment, items, post);
    }
    
    /**
     * Get the current amount due (total price of all items) 
     * @return 
     */
    public float getAmountDue() {
        return 0f; // TODO: Return amount due
    }
}
