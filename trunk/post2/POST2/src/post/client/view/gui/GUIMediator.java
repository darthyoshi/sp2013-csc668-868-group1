package post.client.view.gui;

import java.awt.Dialog;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import post.client.controller.POST;
import post.client.controller.TransactionBuilder;
import post.client.view.gui.TransactionThread.TransactionCallback;
import post.model.ProductSpecification;
import post.model.Receipt;
import post.model.Transaction;

/**
 *
 * @author woeltjen
 */
public class GUIMediator {
    private POST         post;
    private TransactionBuilder activeTransaction;
    
    private CustomerArea customerArea = new CustomerArea();
    private ProductArea  productArea  = new ProductArea();
    private InvoiceArea  invoiceArea  = new InvoiceArea();
    private TimeArea     timeArea     = new TimeArea();
    private PaymentArea  paymentArea  = new PaymentArea();
    
    public GUIMediator(POST post) {
        this.post = post;
        productArea.setUPCChoices(getUPCs(post.getCatalog().getProducts()));
        
        customerArea.addDocumentListener(customerListener);
        productArea.addActionListener(productAction); 
        paymentArea.addActionListener(payAction);
        
        activeTransaction = post.startTransaction();
        
        transactionUpdated();
    }
    
    private DocumentListener customerListener = new DocumentListener() {

        @Override
        public void changedUpdate(DocumentEvent de) {
            activeTransaction.setCustomer(customerArea.getCustomerName());
            transactionUpdated();
        }

        @Override
        public void insertUpdate(DocumentEvent de) {
            activeTransaction.setCustomer(customerArea.getCustomerName());
            transactionUpdated();        
        }

        @Override
        public void removeUpdate(DocumentEvent de) {
            activeTransaction.setCustomer(customerArea.getCustomerName());
            transactionUpdated();
        }
        
    };
       
    private ActionListener payAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            new TransactionHandler().recordTransaction(
                    activeTransaction.completeSale(paymentArea.getPayment()));
        }        
    };
    
    private ActionListener productAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            activeTransaction.addLineItem(productArea.getUPC(), 
                    productArea.getQuantity());            
            transactionUpdated();
            invoiceArea.repaint();
        }        
    };
    
    private void transactionUpdated() {
        invoiceArea.setTransactionBuilder(activeTransaction);
        paymentArea.setPayEnabled(!customerArea.getCustomerName().isEmpty());
        paymentArea.setAmountDue(activeTransaction.getAmountDue());
        invoiceArea.updateTable();
    }
    
    public String getStoreName() {
        return post.getDescription().getName();
    }
    
    public CustomerArea getCustomerArea() {
        return customerArea;
    }

    public InvoiceArea getInvoiceArea() {
        return invoiceArea;
    }

    public PaymentArea getPaymentArea() {
        return paymentArea;
    }

    public ProductArea getProductArea() {
        return productArea;
    }

    public TimeArea getTimeArea() {
        return timeArea;
    }
    
    private void clear() {
        activeTransaction = post.startTransaction();
        invoiceArea.setTransactionBuilder(activeTransaction);
        customerArea.clear();
        paymentArea.clear();
        productArea.clear();        
    }
    
    private static String[] getUPCs (List<ProductSpecification> productSpec) {
        String[] upcs = new String[productSpec.size()];
        for (int i = 0; i < productSpec.size(); i++) {
            upcs[i] = productSpec.get(i).getUpc(); 
        }
        Arrays.sort(upcs); // Alphabetize for presentation
        return upcs;
    }
    
    private class TransactionHandler implements TransactionCallback {
        Window window;
        JDialog pleaseWait;
        
        public void recordTransaction(Transaction t) {
            // Pop a please wait dialog which blocks the GUI
            window = SwingUtilities.getWindowAncestor(paymentArea);
            window.setFocusableWindowState(false);
            pleaseWait = 
                    new JDialog(SwingUtilities.getWindowAncestor(paymentArea), 
                    "Please wait...", 
                    Dialog.ModalityType.MODELESS);
            // TODO: Move this to a non-GUI thread!    
            pleaseWait.setTitle("Please wait...");
            pleaseWait.getContentPane().add(new JLabel("Sending transaction..."));
            pleaseWait.pack();
            pleaseWait.setVisible(true);
    
            new TransactionThread(post, t, this).start();            
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
            clear();
            new ReceiptView(r).setVisible(true);
        }
    }
}
