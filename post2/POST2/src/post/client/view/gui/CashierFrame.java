package post.client.view.gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import post.client.controller.POST;

/**
 *
 * @author woeltjen
 */
public class CashierFrame extends JFrame {
    private static final String TITLE = "Cashier window";
    
    public CashierFrame(GUIMediator m) {
        super(TITLE + " - " + m.getStoreName());
        
        JPanel full  = new JPanel(new BorderLayout());        
        JPanel north = new JPanel(new BorderLayout());
        JPanel south = new JPanel(new BorderLayout());
        
        north.add(m.getCustomerArea(), BorderLayout.WEST);
        north.add(m.getProductArea(), BorderLayout.EAST);
        
        south.add(m.getTimeArea(), BorderLayout.WEST);
        south.add(m.getPaymentArea(), BorderLayout.EAST);
        
        full.add(north, BorderLayout.NORTH);
        full.add(south, BorderLayout.SOUTH);
        full.add(m.getInvoiceArea(), BorderLayout.CENTER);
        
        getContentPane().add(full);
    }
    
    
}