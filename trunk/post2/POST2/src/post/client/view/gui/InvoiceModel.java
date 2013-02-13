package post.client.view.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.AbstractTableModel;
import post.model.LineItem;

/**
 *
 * @author woeltjen
 */
public class InvoiceModel extends AbstractTableModel {
    private Map <String, Integer> upcIndexes = new HashMap<String, Integer>();
    private List<LineItem> lineItems = new ArrayList<LineItem>();
    
    @Override
    public int getColumnCount() {
        return InvoiceColumn.values().length;
    }

    @Override
    public int getRowCount() {
        return 12;
    }

    @Override
    public Object getValueAt(int row, int column) {
        return row < lineItems.size() ? 
                InvoiceColumn.values()[column].interpret(lineItems.get(row)) :
                "";
    }

    @Override
    public String getColumnName(int column) {
        return InvoiceColumn.values()[column].toString();
    }
    
    public void addLineItem(LineItem item) {
        String upc = item.getProductSpec().getUpc();
        if (upcIndexes.containsKey(upc)) {
            lineItems.remove((int) upcIndexes.get(upc));
        }
        upcIndexes.put(upc, lineItems.size());
        lineItems.add(item);
    }
    
    public List<LineItem> getLineItems() {
        return lineItems;
    }
    
    private static enum InvoiceColumn {
        ITEM() {
            @Override
            public String interpret(LineItem item) {
                return item.getProductSpec().getDescription();
            }            
        },
        QUANTITY() {
            @Override
            public String interpret(LineItem item) {
                return "" + item.getQuantity();
            }            
        },
        UNIT_PRICE() {
            @Override
            public String interpret(LineItem item) {
                return  "" + item.getProductSpec().getPrice();
            }            
        },
        TOTAL_PRICE() {
            @Override
            public String interpret(LineItem item) {
                return "" + item.getProductSpec().getPrice() * item.getQuantity();
            }            
        }
        ;        
        public abstract String interpret(LineItem item);
    }
}
