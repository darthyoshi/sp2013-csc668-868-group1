package post.client.view.gui;

import java.awt.event.ActionListener;
import javax.swing.*;
import post.model.Payment;



/**
 *
 * @author woeltjen
 */
public class PaymentArea extends JPanel {
    private JComboBox  paymentType = new JComboBox(PaymentOption.values());
    private JTextField detailField = new JTextField(16);
    private JButton    payButton   = new JButton("Pay");
    
    public PaymentArea() {
        super();
        add (new JLabel("Payment type: "));
        add (paymentType);
        add (new JLabel("Amount: "));
        add (detailField);
        add (payButton);
        setBorder(BorderFactory.createTitledBorder("Payment"));
    }    
    
    public Payment getPayment(float amount) {
        Object option = paymentType.getSelectedItem();
        if (option instanceof PaymentOption) {
            PaymentOption p = (PaymentOption) option;
            return p.createPayment(amount, detailField.getText());
        }
        return null;
    }
    
    public void clear() {
        paymentType.setSelectedIndex(0);
        detailField.setText("");
        repaint();
    }
    
    public void addActionListener(ActionListener listener) {
        payButton.addActionListener(listener);
    }
}
