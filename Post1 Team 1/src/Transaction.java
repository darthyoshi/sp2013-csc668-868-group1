/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author steven
 */
//Holds transaction info
public class Transaction {

    private String customer;
    private long timeStamp;
    TransactionItem[] transItems = new TransactionItem[100];    //holds transaction items
    private Payment payment;
    private int numTransItems;
    //Payment payment;

    public Transaction(String customer, long timeStamp, TransactionItem[] transItems, Payment payment, int numTransItems) {

        this.customer = customer;
        this.timeStamp = timeStamp;
        this.transItems = new TransactionItem[100];
        this.payment = payment;
        this.numTransItems = numTransItems;
    }

    //getters and setters
    public TransactionItem[] getTransItems() {
        return transItems;
    }

    private void setTransItems(TransactionItem[] transItems) {
        this.transItems = transItems;
    }

    public String getCustomer() {
        return customer;
    }

    private void setCustomer(String customer) {
        this.customer = customer;
    }

    public long getTimestamp() {
        return timeStamp;
    }

    private void setTimestamp(long timestamp) {
        this.timeStamp = timestamp;

    }

    public Payment getPayment() {
        return payment;
    }

    private void setPayment(Payment payment) {
        this.payment = payment;
    }
}

//Holds an items upc and quantity
class TransactionItem {

    private int upc;
    private int quantity;

    //if there is only one item
    public TransactionItem(int upc) {
        this.upc = upc;
    }
    //if there is a quantity as well

    public TransactionItem(int upc, int quantity) {
        this.upc = upc;
        this.quantity = quantity;
    }

    private void setUPC(int upc) {
        this.upc = upc;
    }

    public int getUPC() {
        return upc;
    }

    private void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}