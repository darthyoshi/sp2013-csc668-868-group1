package post.client.view.gui;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import post.client.controller.TransactionBuilder;

/**
 * Displays the line items present in an active transaction.
 * @author woeltjen
 */
public class LineItemArea extends JPanel {
    private LineItemModel model = new LineItemModel();
    private JLabel        totalLabel = new JLabel("Total: ");
    
    /**
     * Create a new GUI element to display line items.
     */
    public LineItemArea() {
        super();        
        JTable table = new JTable(model);

        setLayout(new BorderLayout());
        add(table.getTableHeader(), BorderLayout.NORTH);
        add(table, BorderLayout.CENTER);
        add(totalLabel, BorderLayout.SOUTH);
        setBorder(BorderFactory.createTitledBorder("Invoice"));
    }    
    
    /**
     * Update the table to reflect changes to the active transaction.
     */
    public void updateTable() {
        totalLabel.setText("Total: " +model.getAmountDue());
        repaint();
    }
    
    /**
     * Set the active transaction.
     * @param b the TransactionBuilder object preparing the current transaction
     */
    public void setTransactionBuilder(TransactionBuilder b) {
        model.setTransactionBuilder(b);
    }
}
