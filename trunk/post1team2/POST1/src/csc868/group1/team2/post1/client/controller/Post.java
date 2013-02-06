package csc868.group1.team2.post1.client.controller;

/**
 *
 * @author Kay Choi
 */
public class Post {
    private Store store;
    private String date;
    private int time;

    public Post(Store store) {
        this.store = store;
    }

    public TransactionBuilder startTransaction() {
        return new TransactionBuilder(date, time);
    }

    public Receipt recordSale(Transaction trans) {
        return new Receipt(store, trans);
    }
}
