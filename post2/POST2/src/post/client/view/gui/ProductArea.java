package post.client.view.gui;

import javax.swing.*;

/**
 *
 * @author woeltjen
 */
public class ProductArea extends JPanel {
    public ProductArea() {
        super();
        add (new JLabel("UPC: "));
        add (new JComboBox(new String[]{"AAAA", "BBBB"}));
        add (new JLabel("Quantity: "));
        add (new JSpinner());
        add (new JButton("Add"));
        setBorder(BorderFactory.createTitledBorder("Product"));
    }    
}
