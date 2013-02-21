package post.client.view.gui;

import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * An area where products may be added to a transaction.
 * @author woeltjen
 */
public class ProductArea extends JPanel {
    private JComboBox upcBox;
    private JSpinner  quantitySpinner;
    
    private JButton   addButton = new JButton("Add");    
    
    /**
     * Create a new GUI element to allow adding products to a transaction.
     */
    public ProductArea() {
        super();
        upcBox = new JComboBox(new String[] {} );
        quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 999, 1));
        
        add (new JLabel("UPC: "));
        add (upcBox);
        add (new JLabel("Quantity: "));
        add (quantitySpinner);
        add (addButton);
        setBorder(BorderFactory.createTitledBorder("Product"));
    }    
    
    /**
     * Get the currently selected UPC
     * @return 
     */
    public String getUPC() {
        return upcBox.getSelectedItem().toString();
    }
    
    /**
     * Get the currently chosen quantity
     * @return 
     */
    public int getQuantity() {
        return (Integer) quantitySpinner.getValue();
    }
    
    /**
     * Set all options for available UPCs.
     * @param upcs 
     */
    public void setUPCChoices(String[] upcs) {
        upcBox.setModel(new DefaultComboBoxModel(upcs));
        repaint();
    }
    
    /**
     * Listen for user attempts to add items to a transaction. This corresponds 
     * to the "add" button.
     * @param listener the object which listens for clicks to "Add"
     */
    public void addActionListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }
    
    /**
     * Clear the current user-entered data from this element.
     */
    public void clear() {
        upcBox.setSelectedIndex(0);
        quantitySpinner.setValue(1);
    }
}
