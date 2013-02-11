package post.server.controller;

import post.model.Receipt;
import post.model.Transaction;

/**
 * A TransactionReceiver is simply eligible to record transactions. It will
 * also provide Receipt objects to confirm that the transaction has been
 * recorded.
 */
public interface TransactionReceiver {

    /**
     * Record the specified transaction. Note that this method is responsible
     * for issuing a Receipt object (this may be null in cases where errors
     * have occurred.)
     * @return       Receipt a receipt for the transaction
     * @param        transaction the transaction to record
     */
    public Receipt recordTransaction(Transaction transaction);
}
