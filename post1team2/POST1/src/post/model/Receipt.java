package post.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Create a receipt for the specified transaction. This is primarily to
 * provide formatted text output for the transaction.
 *
 * @author woeltjen
 */
public class Receipt {
    private static final DateFormat DATE_FORMAT =
            new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    
    private Transaction transaction;

    /**
     * Create a new receipt for the specified transaction.
     * @param t
     */
    public Receipt(Transaction t) {
        transaction = t;
    }

    /**
     * Get a formatted text representation of the transaction described by
     * this receipt.
     * @return       String
     */
    public String toColumnOutput() {
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(transaction.getTimestamp());

        String output = "";
        output += transaction.getStore().getName() + "\n";
        output += transaction.getStore().getAddress() + "\n";
        output += "\n";
        output += transaction.getCustomer() + "\t";
        output += DATE_FORMAT.format(cal.getTime()) + "\n";

        for (LineItem item : transaction.getLineItems()) {
            float itemPrice = item.getProductSpec().getPrice();
            float totalPrice = item.getQuantity() * itemPrice;
            output += item.getProductSpec().getDescription() + "\t";
            output += item.getQuantity() + " @ ";
            output += formatPrice(itemPrice) + "\t";
            output += formatPrice(totalPrice) + "\n";
        }

        output += "------\n";
        output += "Total: " + formatPrice(transaction.getPayment().getAmount());
        output += "\n";
        output += transaction.getPayment().toColumnOutput();

        return output;
    }

    private String formatPrice(float price) {
        return String.format("$%.2f", price);
    }
}
