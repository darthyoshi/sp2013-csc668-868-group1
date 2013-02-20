package post.client.view.gui;

import java.awt.event.ActionEvent;
import post.model.PaymentOption;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import post.model.Payment;



/**
 *
 * @author woeltjen
 */
public class PaymentArea extends JPanel {
    private JComboBox  paymentType = new JComboBox(PaymentOption.values());
    private JTextField detailField = new JTextField(16);
    private JButton    payButton   = new JButton("Pay");
    private boolean    enabled     = true;
    private float      amountDue   = 0f;    
    
    public PaymentArea() {
        super();
        add (new JLabel("Payment type: "));
        add (paymentType);
        add (new JLabel("Amount: "));
        add (detailField);
        add (payButton);
        setBorder(BorderFactory.createTitledBorder("Payment"));
        
        paymentType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                updatePayButton();
            }            
        });
        
        detailField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent de) {
                updatePayButton();
            }

            @Override
            public void insertUpdate(DocumentEvent de) {
                updatePayButton();
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                updatePayButton();
            }
            
        });
        
        updatePayButton();
    }    
    
    public Payment getPayment() {
        Object option = paymentType.getSelectedItem();
        if (option instanceof PaymentOption) {
            PaymentOption p = (PaymentOption) option;
            return p.createPayment(amountDue, detailField.getText());
        }
        return null;
    }
    
    public void clear() {
        setAmountDue(0);
        paymentType.setSelectedIndex(0);
        detailField.setText("");        
        updatePayButton();
        repaint();
    }
    
    public void setAmountDue(float amount) {
        this.amountDue = amount;
        updatePayButton();
    }
    
    public void addActionListener(ActionListener listener) {
        payButton.addActionListener(listener);
    }
    
    public void setPayEnabled (boolean enabled) {
        this.enabled = enabled;
        updatePayButton();
    }
    
    private void updatePayButton() {
        boolean amountSufficient = getPayment() != null && amountDue > 0;
        payButton.setEnabled(enabled && amountSufficient);
        payButton.repaint();
    }
}
