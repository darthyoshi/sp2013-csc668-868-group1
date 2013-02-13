package post.client.view.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import post.client.controller.POST;
import post.client.controller.TransactionBuilder;
import post.model.LineItem;
import post.model.ProductSpecification;
import post.model.Receipt;
import post.model.Transaction;

/**
 *
 * @author woeltjen
 */
public class GUIMediator {
    private POST         post;
    private CustomerArea customerArea = new CustomerArea();
    private ProductArea  productArea  = new ProductArea();
    private InvoiceArea  invoiceArea  = new InvoiceArea();
    private TimeArea     timeArea     = new TimeArea();
    private PaymentArea  paymentArea  = new PaymentArea();
    
    public GUIMediator(POST post) {
        this.post = post;
        productArea.setUPCChoices(getUPCs(post.getCatalog().getProducts()));
        
        productArea.addActionListener(productAction); 
        paymentArea.addActionListener(payAction);
    }
       
    private ActionListener payAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            TransactionBuilder b = post.startTransaction();
            b.setCustomer(customerArea.getCustomerName());
            for (LineItem item : invoiceArea.getLineItems()) {
                b.addLineItem(item.getProductSpec().getUpc(), 
                        item.getQuantity());                
            }
            Transaction t = b.completeSale(
                    paymentArea.getPayment(invoiceArea.getAmountDue()));
            Receipt r = post.recordTransaction(t);
            // System.out.println(r.toColumnOutput());
        }        
    };
    
    private ActionListener productAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            String upc = productArea.getUPC();
            int    quantity = productArea.getQuantity();
            
            invoiceArea.addLineItem(
                    new LineItem(post.getCatalog().lookup(upc), quantity));            
        }        
    };
    
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
    
    private static String[] getUPCs (List<ProductSpecification> productSpec) {
        String[] upcs = new String[productSpec.size()];
        for (int i = 0; i < productSpec.size(); i++) {
            upcs[i] = productSpec.get(i).getUpc(); 
        }
        Arrays.sort(upcs); // Alphabetize for presentation
        return upcs;
    }
}
