package post.client.view.gui;

import java.awt.Component;
import javax.swing.SwingUtilities;
import post.model.Receipt;
import post.model.Transaction;
import post.server.controller.TransactionReceiver;

/**
 *
 * @author woeltjen
 */
public class TransactionThread extends Thread {

    private TransactionReceiver receiver;
    private Transaction transaction;
    private TransactionCallback callback;

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

    public static interface TransactionCallback {

        public void transactionSuccessful(Receipt r);

        public void transactionFailed();
    }
}
