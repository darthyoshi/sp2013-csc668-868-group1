package post.client.view.gui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.AbstractTableModel;
import post.client.controller.TransactionBuilder;
import post.model.LineItem;
import post.model.Payment;

/**
 *
 * @author woeltjen
 */
public class InvoiceModel extends AbstractTableModel {
    private Map <String, Integer> upcIndexes = new HashMap<String, Integer>();
    private TransactionBuilder builder;
    //private List<LineItem> lineItems = new ArrayList<LineItem>();
    
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
        return row < getLineItems().size() ? 
                InvoiceColumn.values()[column].interpret(getLineItems().get(row)) :
                "";
    }

    @Override
    public String getColumnName(int column) {
        return InvoiceColumn.values()[column].toString();
    }
    
    public void addLineItem(String upc, int quantity) {
        builder.addLineItem(upc, quantity);
    }
    
    private List<LineItem> getLineItems() {
        return builder.completeSale(DUMMY_PAYMENT).getLineItems();
    }
    
    public void setTransactionBuilder(TransactionBuilder b) {
        builder = b;
    }
    
    public float getAmountDue() {
        return builder.getAmountDue();
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
    
    private static final Payment DUMMY_PAYMENT = 
            new Payment(Float.POSITIVE_INFINITY) {        
        @Override
        public String toColumnOutput() {
            return "";
        }        
    };
}
