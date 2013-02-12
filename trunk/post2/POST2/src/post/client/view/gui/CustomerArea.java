package post.client.view.gui;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author woeltjen
 */
public class CustomerArea extends JPanel {
    public CustomerArea() {
        add(new JLabel("Customer: "));
        add(new JTextField(20));
        setBorder(BorderFactory.createEmptyBorder(32, 32, 32, 32));
    }
}
