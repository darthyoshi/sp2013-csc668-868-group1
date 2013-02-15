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
    private Component dialog;

    public TransactionThread(TransactionReceiver receiver, Transaction t,
            Component progressDialog) {
        this.receiver = receiver;
        this.transaction = t;
        this.dialog = progressDialog;
    }

    @Override
    public void run() {
        final Receipt receipt = receiver.recordTransaction(transaction);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                dialog.setVisible(false);
                if (receipt != null) {
                    new ReceiptView(receipt).setVisible(true);
                }
            }
        });
    }
}
