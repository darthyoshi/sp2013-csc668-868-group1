package post.client.view.gui;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;

/**
 *
 * @author woeltjen
 */
public class CustomerArea extends JPanel {
    private JTextField customerField = new JTextField(20);
    
    public CustomerArea() {
        add(new JLabel("Customer: "));
        add(customerField);
        setBorder(BorderFactory.createEmptyBorder(32, 32, 32, 32));
    }
    
    public String getCustomerName() {
        return customerField.getText();
    }
    
    public void clear() {
        customerField.setText("");
    }
    
    public void addDocumentListener(DocumentListener documentListener) {
        customerField.getDocument().addDocumentListener(documentListener);
    }
}
