package post.client.view.gui;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;

/**
 * Provides an area for a customer's name to be entered.
 * @author woeltjen
 */
public class CustomerArea extends JPanel {
    private JTextField customerField = new JTextField(20);
    
    /**
     * Create a new name entry area.
     */
    public CustomerArea() {
        add(new JLabel("Customer: "));
        add(customerField);
        setBorder(BorderFactory.createEmptyBorder(32, 32, 32, 32));
    }
    
    /**
     * Get the customer's name, as entered into the GUI
     * @return 
     */
    public String getCustomerName() {
        return customerField.getText();
    }
    
    /**
     * Clear the current name
     */
    public void clear() {
        customerField.setText("");
    }
    
    /**
     * Listen for changes to the customer's name
     * @param documentListener 
     */
    public void addDocumentListener(DocumentListener documentListener) {
        customerField.getDocument().addDocumentListener(documentListener);
    }
}
