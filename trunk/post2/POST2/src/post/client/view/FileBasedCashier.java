package post.client.view;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import post.client.controller.POST;
import post.model.Receipt;
import post.model.Transaction;

/**
 * A file-based cashier simulates the cashier's activity by reading
 * transactions from a file and issuing them back to the POST.
 *
 * @author woeltjen
 */
public class FileBasedCashier implements CashierView {
    private static final String DEFAULT_TRANSACTIONS = "transaction.txt";
    private TransactionReader reader;

    /**
     * Create a new "Cashier" that reads from a file. This no-argument form 
     * will read from the default file name, "transaction.txt"
     * @throws IOException 
     */
    public FileBasedCashier() throws IOException {
        this(new File(DEFAULT_TRANSACTIONS));
    }

    /**
     * Create a new "Cashier" that reads from a specified file"
     * @param f the file containing formatted transaction information
     * @throws IOException
     */
    public FileBasedCashier(File f) throws IOException {
        this(new TransactionReader(new FileReader(f)));
    }

    /**
     * Create a new "Cashier" that reads transactions from the
     * specified TransactionReader
     * @param reader the source for reading transactions
     */
    public FileBasedCashier(TransactionReader reader) {
        this.reader = reader;
    }

    /**
     * Connect this CashierView to a POST. For file-based cashier, this 
     * will initiate reading from the transaction file via the transaction 
     * reader, and delivering results back to the POST.
     * @param post the POST we are connected to
     */
    public void connectTo(POST post) {
        while (reader.hasMoreTransactions()) {
            Transaction t = reader.getNextTransaction(post.startTransaction());
            Receipt     r = post.recordTransaction(t);
            System.out.println(r.toColumnOutput() + "\n");
        }
    }
}
