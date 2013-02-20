/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package post.client.view.gui;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import post.model.Receipt;

/**
 * Provides a window to display a Receipt. This serves as a substitute for 
 * a receipt printer, which is not present in the system.
 * @author woeltjen
 */
public class ReceiptView extends JFrame {
    
    public ReceiptView(Receipt r) {
        JTextArea textArea = new JTextArea();
        textArea.setText(r.toColumnOutput());
        textArea.setEditable(false);
        textArea.setBorder(BorderFactory.createTitledBorder("Receipt"));
        getContentPane().add(textArea);
        pack();
    }
    
}
