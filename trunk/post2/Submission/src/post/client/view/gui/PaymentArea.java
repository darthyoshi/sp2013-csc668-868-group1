package post.client.view.gui;

import java.awt.event.ActionEvent;
import post.model.PaymentOption;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import post.model.Payment;

/**
 * Provides an area where payment type can be selected and payment details may
 * be entered.
 *
 * @author woeltjen
 */
public class PaymentArea extends JPanel {
    private Map<PaymentOption, String> detailNames = 
            new HashMap<PaymentOption, String>();
    
    private JComboBox paymentType = new JComboBox(PaymentOption.values());
    private JTextField detailField = new JTextField(16);
    private JLabel detailLabel = new JLabel();
    private JButton payButton = new JButton("Pay");
    private boolean enabled = true;
    private float amountDue = 0f;

    /**
     * Create a new GUI element to enter payment info.
     */
    public PaymentArea() {
        super();
        add(new JLabel("Payment type: "));
        add(paymentType);
        add(detailLabel);
        add(detailField);
        add(payButton);
        setBorder(BorderFactory.createTitledBorder("Payment"));

        detailNames.put(PaymentOption.CASH, "Tendered:");
        detailNames.put(PaymentOption.CREDIT, "CC#:");
        
        // Changes to payment type should also clear detail field
        paymentType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                detailField.setText("");
                updateDetailField();
                updatePayButton();
            }
        });

        // Payment button needs to disable when detail field is bad, so listen
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
        updateDetailField();
    }

    /**
     * Get a payment object, as entered into GUI area.
     *
     * @return the entered payment
     */
    public Payment getPayment() {
        Object option = paymentType.getSelectedItem();
        if (option instanceof PaymentOption) {
            PaymentOption p = (PaymentOption) option;
            return p.createPayment(amountDue, detailField.getText());
        }
        return null;
    }

    /**
     * Clear the current payment info.
     */
    public void clear() {
        setAmountDue(0);
        paymentType.setSelectedIndex(0);
        detailField.setText("");
        updatePayButton();
        repaint();
    }

    /**
     * Set the current amount due. This is used to generate appropriate Payment
     * objects and to ensure that the Pay button is not available when
     * insufficient payment is entered.
     *
     * @param amount the amount due, in dollars
     */
    public void setAmountDue(float amount) {
        this.amountDue = amount;
        updatePayButton();
    }

    /**
     * Add an action listener which shall be notified when the Pay button is
     * clicked.
     *
     * @param listener
     */
    public void addActionListener(ActionListener listener) {
        payButton.addActionListener(listener);
    }

    /**
     * Set whether or not the Pay button should be enabled. This is used when
     * some external factor, such as customer name, should be used to suppress
     * payment. Note that disabling payment is definitive, but enabling is not
     * (the payment area may still choose to suppress payment if it cannot
     * construct a valid payment object)
     *
     * @param enabled true if payment may be enabled, false if it may not
     */
    public void setPayEnabled(boolean enabled) {
        this.enabled = enabled;
        updatePayButton();
    }

    private void updatePayButton() {
        boolean amountSufficient = getPayment() != null && amountDue > 0;
        payButton.setEnabled(enabled && amountSufficient);
        payButton.repaint();
    }
    
    private void updateDetailField() {
        String name = 
                detailNames.get((PaymentOption) paymentType.getSelectedItem());
        if (name != null) {
            detailLabel.setText(name);
            detailField.setEnabled(true);
        } else {
            detailLabel.setText("");
            detailField.setEnabled(false);
        }
    }
}
