package post.client.view.gui;

import javax.swing.SwingUtilities;
import post.model.Receipt;
import post.model.Transaction;
import post.server.controller.TransactionReceiver;

/**
 * Submits transactions on a separate thread from the caller. This allows 
 * transactions to be submitted from the GUI without pausing the GUI thread.
 * @author woeltjen
 */
public class TransactionThread extends Thread {

    private TransactionReceiver receiver;
    private Transaction transaction;
    private TransactionCallback callback;

    /**
     * Create a new thread for handling a transaction.
     * @param receiver the destination for this transaction
     * @param t the transaction
     * @param callback the object to be notified on success/failure
     */
    public TransactionThread(TransactionReceiver receiver, Transaction t,
            TransactionCallback callback) {
        this.receiver = receiver;
        this.transaction = t;
        this.callback = callback;
    }

    @Override
    public void run() {
        final Receipt receipt = receiver.recordTransaction(transaction);
        // Assume that this callback needs to be on the EDT
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (receipt != null) {
                    callback.transactionSuccessful(receipt);
                } else {
                    callback.transactionFailed();
                }
            }
        });
    }

    /**
     * Callbacks for success/failure of the transaction
     */
    public static interface TransactionCallback {
        public void transactionSuccessful(Receipt r);
        public void transactionFailed();
    }
}
