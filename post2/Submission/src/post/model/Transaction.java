package post.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import post.store.Store;

/**
 * Represents a completed sale
 * @author woeltjen
 */
public class Transaction implements Serializable {

    private String customer;
    private long timestamp;
    private Payment payment;
    private List<LineItem> lineItems;
    private StoreDescription store;

    /**
     * Create a new transaction object to describe a sale.
     * @param customer the name of the customer
     * @param timestamp the time of the sale (ms since UNIX epoch)
     * @param payment the payment issued by the customer
     * @param lineItems the products purchased (UPC/quantity pairs)
     * @param store the store from which these objects were purchased
     */
    public Transaction(String customer, long timestamp, Payment payment,
            Map<String, Integer> lineItems, Store store) {
        this.customer = customer;
        this.timestamp = timestamp;
        this.payment = payment;
        this.store = store.getDescription();
        this.lineItems = new ArrayList<LineItem>();

        for (Entry<String, Integer> entry : lineItems.entrySet()) {
            this.lineItems.add(new LineItem(store.getCatalog().lookup(entry.getKey()), entry.getValue()));
        }
    }

 
    /**
     * Get the time of purchase. This is in milliseconds since the UNIX epoch
     * (January 1, 1970 00:00:00)
     * @return the time at which the sale occurred
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Get the payment issued for this sale
     * @return the payment
     */
    public Payment getPayment() {
        return payment;
    }

    /**
     * Get all LineItems for this purchase. Each line item represents an
     * individual product purchased at some quantity.
     * @return the items purchased
     */
    public List<LineItem> getLineItems() {
        return lineItems;
    }

    /**
     * Get a description of the store where the sale was made.
     * @return the store description
     */
    public StoreDescription getStore() {
        return store;
    }

    /**
     * Get the name of the customer involved in the sale
     * @return
     */
    public String getCustomer() {
        return customer;
    }
}
