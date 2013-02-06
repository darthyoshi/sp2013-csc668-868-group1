package csc868.group1.team2.post1.client.controller;

/**
 *
 * @author Kay Choi
 */
public class TransactionBuilder {
    private java.util.HashMap<String, Integer> items;
    private int time;
    private String date;

    public TransactionBuilder(String date, int time) {
        items = new java.util.HashMap<String, Integer>();
        this.date = date;
        this.time = time;
    }

    public void addLineItem(String upc, int quantity) {
        items.put(upc, quantity);
    }

    public void removeLineItem(String upc) {
        items.remove(upc);
    }

    public Transaction complete() {
        return new Transaction(date, time, items);
    }
}
