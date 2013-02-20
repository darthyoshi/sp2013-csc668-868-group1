/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author woeltjen
 */
public class TransactionHandler implements TransactionCallback {
    private Window window;
    private JDialog pleaseWait;
    private GUIMediator mediator;
    private TransactionReceiver receiver;
    
    public TransactionHandler(Window window, GUIMediator mediator, 
            TransactionReceiver receiver) {
        this.window = window;
        this.mediator = mediator;
        this.receiver = receiver;
    }
    
    
    
    public void recordTransaction(Transaction t) {
            // Pop a please wait dialog which blocks the GUI    
            window.setFocusableWindowState(false);
            pleaseWait = 
                    new JDialog(window, 
                    "Please wait...", 
                    Dialog.ModalityType.MODELESS);
            // TODO: Move this to a non-GUI thread!    
            pleaseWait.setTitle("Please wait...");
            pleaseWait.getContentPane().add(new JLabel("Sending transaction..."));
            pleaseWait.pack();
            pleaseWait.setVisible(true);
    
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
