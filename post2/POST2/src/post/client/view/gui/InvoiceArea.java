package post.client.view.gui;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

/**
 *
 * @author woeltjen
 */
public class InvoiceArea extends JPanel {
    public InvoiceArea() {
        super();
        setLayout(new BorderLayout());
        add(new JTable(12, 4), BorderLayout.CENTER);
        add(new JLabel("Total"), BorderLayout.SOUTH);
        setBorder(BorderFactory.createTitledBorder("Invoice"));
    }
}
