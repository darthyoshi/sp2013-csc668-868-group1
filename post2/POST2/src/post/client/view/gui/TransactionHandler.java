package post.client.view.gui;

import java.awt.Dialog;
import java.awt.Window;
import javax.swing.JDialog;
import javax.swing.JLabel;
import post.client.view.gui.TransactionThread.TransactionCallback;
import post.model.Receipt;
import post.model.Transaction;
import post.server.controller.TransactionReceiver;

/**
 * A TransactionHandler is responsible for initiating the issue of transactions
 * to a POST object from the GUI.
 *
 * Because recording a transaction may take some time, this should not occur on
 * the GUI thread; however, the cashier should also not be able to interact with
 * the GUI until the previous transaction has completed. To balance this, a
 * dialog is displayed letting the user know that the transaction is pending;
 * once complete, the receipt is displayed and access to the GUI is restored.
 *
 * @author woeltjen
 */
public class TransactionHandler implements TransactionCallback {

    private Window window;
    private JDialog pleaseWait;
    private GUIMediator mediator;
    private TransactionReceiver receiver;

    /**
     * Create a new transaction handler.
     * @param window the window to block while transaction is pending
     * @param mediator the mediator which will produce the next transaction
     * @param receiver the destination for transactions to be handled
     */
    public TransactionHandler(Window window, GUIMediator mediator,
            TransactionReceiver receiver) {
        this.window = window;
        this.mediator = mediator;
        this.receiver = receiver;
    }

    /**
     * Record a new transaction.
     * @param t 
     */
    public void recordTransaction(Transaction t) {
        // Pop a please wait dialog which blocks the GUI    
        window.setFocusableWindowState(false);
        pleaseWait =
                new JDialog(window,
                "Please wait...",
                Dialog.ModalityType.MODELESS);
        pleaseWait.setTitle("Please wait...");
        pleaseWait.getContentPane().add(new JLabel("Sending transaction..."));
        pleaseWait.pack();
        pleaseWait.setVisible(true);

        // Then, initiate a transaction on a separate thread
        new TransactionThread(receiver, t, this).start();
    }

    @Override
    public void transactionFailed() {
        window.setFocusableWindowState(true);
        pleaseWait.setVisible(false);
    }

    @Override
    public void transactionSuccessful(Receipt r) {
        window.setFocusableWindowState(true);
        pleaseWait.setVisible(false);
        mediator.clear();
        new ReceiptView(r).setVisible(true);
    }
}
