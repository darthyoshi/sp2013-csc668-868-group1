package post.client.controller;

import java.util.HashMap;
import post.model.*;

/**
 * Class TransactionBuilder collects transaction data from a customer and uses
 * it to build a Transaction object.
 * @author Kay Choi
 */
public class TransactionBuilder {

    private HashMap<String, Integer> items;
    private final long time;
    private final POST post;
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
        items = new HashMap<String, Integer>();
        time = timestamp;
        amountDue = 0f;
        post = parent;
    }

    /**
     * Set the name of the customer involved in this transaction.
     * @param name the name of the customer
     */
    public void setCustomer(String name) {
        customer = name;
    }

    /**
     * Adds a new item to the transaction. The UPC of the item is stored, along
     * with the quantity. The subtotal is also updated.
     * @return true if the UPC was valid
     * @param upc the UPC of the item being purchased
     * @param quantity the number of items being purchased
     */
    public boolean addLineItem(String upc, int quantity) {
        ProductSpecification product = post.getCatalog().lookup(upc);

        if(product != null){
            if(items.get(upc) != null) {  //UPC previously entered
                int num = items.get(upc);
                items.put(upc, num + quantity);
            }
            else {
                items.put(upc, quantity);
            }

            amountDue += quantity * product.getPrice();
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Removes an item from the transaction.
     * @param upc the UPC of the item
     */
    public void removeLineItem(String upc) {
        Integer quantity = items.remove(upc);
        if(quantity != null) {
            amountDue -= quantity * post.getCatalog().lookup(upc).getPrice();
        }
    }

    /**
     * Issue payment to complete a transaction. This will return a full
     * Transaction object; note that this transaction is not necessarily
     * recorded to the originating POST.
     * @return Transaction an object representing the processed transaction, if
     *   the payment amount is sufficient
     * @param payment the specific payment for this transaction
     */
    public Transaction completeSale(Payment payment) {
        if(payment.getAmount() >= amountDue) {
            return new Transaction(customer, time, payment, items, post);
        }
        else {
            return null;
        }
    }

    /**
     * Get the current amount due (total price of all items)
     * @return float the current subtotal
     */
    public float getAmountDue() {
        return amountDue;
    }
}
