package post.client.view.gui;

import javax.swing.*;



/**
 *
 * @author woeltjen
 */
public class PaymentArea extends JPanel {
    public PaymentArea() {
        super();
        add (new JLabel("Payment type: "));
        add (new JComboBox(PaymentOption.values()));
        add (new JLabel("Amount: "));
        add (new JTextField(16));
        add (new JButton("Pay"));
        setBorder(BorderFactory.createTitledBorder("Payment"));
    }    
}
