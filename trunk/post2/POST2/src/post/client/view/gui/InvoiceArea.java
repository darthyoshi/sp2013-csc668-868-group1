package post.client.view.gui;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import post.model.LineItem;

/**
 *
 * @author woeltjen
 */
public class InvoiceArea extends JPanel {
    private InvoiceModel model = new InvoiceModel();
    private JLabel       totalLabel = new JLabel("Total: ");
    
    public InvoiceArea() {
        super();        
        JTable table = new JTable(model);

        setLayout(new BorderLayout());
        add(table.getTableHeader(), BorderLayout.NORTH);
        add(table, BorderLayout.CENTER);
        add(totalLabel, BorderLayout.SOUTH);
        setBorder(BorderFactory.createTitledBorder("Invoice"));
    }
    
    public void addLineItem(LineItem item) {
        model.addLineItem(item);
        totalLabel.setText("Total: " + getAmountDue());
        repaint();
    }
    
    public List<LineItem> getLineItems() {
        return model.getLineItems();
    }
    
    public float getAmountDue() {
        float due = 0f;
        for (LineItem i : model.getLineItems()) {
            due += i.getQuantity() * i.getProductSpec().getPrice();
        }
        return due;
    }
}
