package post.client.view.gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import post.client.controller.POST;

/**
 *
 * @author woeltjen
 */
public class CashierFrame extends JFrame {
    private static final String TITLE = "Cashier window";
    private POST post;
    
    public CashierFrame(POST post) {
        super(TITLE + " - " + post.getDescription().getName());
        this.post = post;
        
        JPanel full  = new JPanel(new BorderLayout());        
        JPanel north = new JPanel(new BorderLayout());
        JPanel south = new JPanel(new BorderLayout());
        
        north.add(new CustomerArea(), BorderLayout.WEST);
        north.add(new ProductArea(), BorderLayout.EAST);
        
        south.add(new TimeArea(), BorderLayout.WEST);
        south.add(new PaymentArea(), BorderLayout.EAST);
        
        full.add(north, BorderLayout.NORTH);
        full.add(south, BorderLayout.SOUTH);
        full.add(new InvoiceArea(), BorderLayout.CENTER);
        
        getContentPane().add(full);
    }
    
    
}
