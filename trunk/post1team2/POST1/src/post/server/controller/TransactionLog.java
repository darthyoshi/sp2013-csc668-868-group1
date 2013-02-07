package post.server.controller;

import java.io.*;
import java.util.List;
import post.model.*;

/**
 * Class TransactionLog
 */
public class TransactionLog implements TransactionReceiver {

    private Writer log;

    public TransactionLog(File f) throws IOException {
        this(new FileWriter(f, true));
    }

    public TransactionLog(Writer w) {
        log = w;
    }
    /**
     * @return Receipt
     * @param transaction
     */
    @Override
    public Receipt recordTransaction(Transaction transaction) {
        try {
            Receipt r = new Receipt (transaction);
            log.write(r.toColumnOutput());
            log.write("\n");
            //log.write(formatLogEntry(transaction)); // TODO: Format as receipt?
            log.flush();
            return r;
        } catch (Exception e) {
            // TODO: How to handle write errors? Return receipt anyway?
            return null;
        }
    }

//    private String formatLogEntry(Transaction t) {
//        // TODO: If ever worried about efficiency, use StringBuilder...
//        String entry = "";
//        entry += "Identifying information: " + t.getCustomer() + "\n";
//        for (LineItem item : t.getLineItems()) {
//            entry += "Item: " + item.getProductSpec().getUpc() + " ";
//            entry += item.getQuantity() == 1 ? "" : ("" + item.getQuantity());
//            entry += "\n";
//        }
//        entry += "Payment: " + t.getPayment().toLogOutput() + "\n";
//        entry += "\n"; // Blank line to separate transactions.
//        return entry;
//    }
    
    public static void main (String[] args) {
        try {
            TransactionLog log = 
                    new TransactionLog(new OutputStreamWriter(System.out));
            final Store store = new StoreImpl(new StoreDescription("T1" , "T2"), log, null);
            Transaction t = new Transaction(null, System.currentTimeMillis(), null, null, store) {

                @Override
                public String getCustomer() {
                    return "Test customer";
                }

                @Override
                public List<LineItem> getLineItems() {
                    return super.getLineItems();
                }

                @Override
                public Payment getPayment() {
                    return new CreditPayment(14.99f, 1234);
                }

                @Override
                public StoreDescription getStore() {
                    return super.getStore();
                }

                @Override
                public long getTimestamp() {
                    return super.getTimestamp();
                }
                
            };
            log.recordTransaction(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
