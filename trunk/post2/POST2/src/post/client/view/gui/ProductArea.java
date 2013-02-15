package post.client.view.gui;

import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author woeltjen
 */
public class ProductArea extends JPanel {
    private JComboBox upcBox;
    private JSpinner  quantitySpinner;
    
    private JButton   addButton = new JButton("Add");    
    
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
    
    public String getUPC() {
        return upcBox.getSelectedItem().toString();
    }
    
    public int getQuantity() {
        return (Integer) quantitySpinner.getValue();
    }
    
    public void setUPCChoices(String[] upcs) {
        upcBox.setModel(new DefaultComboBoxModel(upcs));
        repaint();
    }
    
    public void addActionListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }
    
    public void clear() {
        upcBox.setSelectedIndex(0);
        quantitySpinner.setValue(1);
    }
}
