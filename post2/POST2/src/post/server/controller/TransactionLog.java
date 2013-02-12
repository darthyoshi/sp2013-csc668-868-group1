package post.server.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import post.model.Receipt;
import post.model.Transaction;


/**
 * Logs transaction information to a file.
 * @author woeltjen
 */
public class TransactionLog implements TransactionReceiver {
    private Writer log;

    /**
     * Create a new transaction log. Transaction information will be recorded
     * to the specified file.
     * @param f
     * @throws IOException
     */
    public TransactionLog(File f) throws IOException {
        this(new FileWriter(f, true));
    }

    /**
     * Create a new transation log. Transaction information will be written
     * to the specified stream.
     * @param w
     */
    public TransactionLog(Writer w) {
        log = w;
    }

    @Override
    public Receipt recordTransaction(Transaction transaction) {
        try {
            Receipt r = new Receipt(transaction);
            log.write(r.toColumnOutput() + "\n");
            log.flush();
            return r;
        } catch (Exception e) {
            // TODO: How to handle write errors? Return receipt anyway?
            return null;
        }
    }

//    public static void main(String[] args) {
//        // Test method
//        try {
//            TransactionLog log =
//                    new TransactionLog(new OutputStreamWriter(System.out));
//            final Store store = new StoreImpl(new StoreDescription("T1", "T2"), log, null);
//            Transaction t = new Transaction(null, System.currentTimeMillis(), null, null, store) {
//
//                @Override
//                public String getCustomer() {
//                    return "Test customer";
//                }
//
//                @Override
//                public List<LineItem> getLineItems() {
//                    return super.getLineItems();
//                }
//
//                @Override
//                public Payment getPayment() {
//                    return new CreditPayment(14.99f, 1234);
//                }
//
//                @Override
//                public StoreDescription getStore() {
//                    return super.getStore();
//                }
//
//                @Override
//                public long getTimestamp() {
//                    return super.getTimestamp();
//                }
//            };
//            log.recordTransaction(t);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
