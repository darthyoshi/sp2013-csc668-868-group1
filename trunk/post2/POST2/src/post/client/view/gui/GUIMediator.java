package post.client.view.gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import post.client.controller.POST;
import post.client.controller.TransactionBuilder;
import post.model.ProductSpecification;

/**
 * Mediates interactions between GUI elements and the active transaction.
 * @author woeltjen
 */
public class GUIMediator {
    private POST         post;
    private TransactionBuilder activeTransaction;
    
    // The GUI components used for a cashier GUI
    private CustomerArea customerArea = new CustomerArea();
    private ProductArea  productArea  = new ProductArea();
    private LineItemArea  invoiceArea  = new LineItemArea();
    private TimeArea     timeArea     = new TimeArea();
    private PaymentArea  paymentArea  = new PaymentArea();
    
    /**
     * Create a new mediator for interactions between the cashier's GUI 
     * and the specified POST.
     * 
     * @param post 
     */
    public GUIMediator(POST post) {
        this.post = post;
        productArea.setUPCChoices(getUPCs(post.getCatalog().getProducts()));
        
        // Listen for changes that need to be mediated
        customerArea.addDocumentListener(customerListener);
        productArea.addActionListener(productAction); 
        paymentArea.addActionListener(payAction);
        
        activeTransaction = post.startTransaction();
        
        transactionUpdated();
    }
    
    /**
     * Listens for changes to the customer's name and updates the 
     * current transaction. Note that this fires transactionUpdated as well 
     * (among other things, this enables/disables the payment area depending 
     * upon whether or not there is a non-empty customer name)
     */
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
       
    /**
     * Invoked when "Pay" is clicked - transmits a new transaction to the 
     * processor.
     */
    private ActionListener payAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            // Let TransactionHandler show the "wait" dialog and spawn the 
            // thread that will submit the transaction
            new TransactionHandler(SwingUtilities.getWindowAncestor(paymentArea), 
                    GUIMediator.this, post).recordTransaction(
                    activeTransaction.completeSale(paymentArea.getPayment()));
        }        
    };
    
    /**
     * Invoked when the user adds a new product, via the product area.
     */
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
    
    /**
     * Get the name of the Store
     * @return 
     */
    public String getStoreName() {
        return post.getDescription().getName();
    }
    
    /**
     * Get the GUI element allowing the customer's name to be entered.
     * @return 
     */
    public Component getCustomerArea() {
        return customerArea;
    }

    /**
     * Get the GUI element which will display line items & total.
     * @return 
     */
    public Component getInvoiceArea() {
        return invoiceArea;
    }

    /**
     * Get the GUI element that will allow payment to be selected.
     * @return 
     */
    public Component getPaymentArea() {
        return paymentArea;
    }

    /**
     * Get the GUI element that allows products to be added to the transaction
     * @return 
     */
    public Component getProductArea() {
        return productArea;
    }

    /**
     * Get the GUI element that displays the current time
     * @return 
     */
    public Component getTimeArea() {
        return timeArea;
    }
    
    /** 
     * Clear the current transaction (and start a new one). This will update 
     * the GUI in addition to opening a new transaction object
     */
    void clear() {
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
}
