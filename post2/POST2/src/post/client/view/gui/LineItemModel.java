package post.client.view.gui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.AbstractTableModel;
import post.client.controller.TransactionBuilder;
import post.model.LineItem;
import post.model.Payment;

/**
 * The underlying model used by LineItemArea to populate its JTable.
 * @author woeltjen
 */
public class LineItemModel extends AbstractTableModel {
    private TransactionBuilder builder;
    
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

    /**
     * Get the current list of line items from the builder.
     * @return all line items present for the current sale
     */
    private List<LineItem> getLineItems() {
        // Note: Builder doesn't have an appropriate method for this, so 
        //       complete a fake sale to get a Transaction object, which does.
        return builder.completeSale(DUMMY_PAYMENT).getLineItems();
    }
    
    /**
     * Set the active transaction. This shall be the transaction modeled for 
     * this table.
     * @param b the TransactionBuilder object preparing the current transaction
     */
    public void setTransactionBuilder(TransactionBuilder b) {
        builder = b;
    }
    
    /**
     * Get the amount due, from the active transaction.
     * @return 
     */
    float getAmountDue() {
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
