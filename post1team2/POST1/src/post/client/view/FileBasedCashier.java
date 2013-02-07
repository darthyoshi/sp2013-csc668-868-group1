package post.client.view;

import java.io.File;
import java.io.IOException;
import post.client.controller.POST;

/**
 * Class FileBasedCashier
 */
public class FileBasedCashier implements CashierView {
    private TransactionReader reader;

    /**
     * Create a new "Cashier" that reads from a file. This no-argument form 
     * will read from the default file name, "transaction.txt"
     * @throws IOException 
     */
    public FileBasedCashier() throws IOException {
    }

    public FileBasedCashier(File f) throws IOException {
    }

    public FileBasedCashier(TransactionReader reader) {
    }

    /**
     * Connect this CashierView to a POST. For file-based cashier, this 
     * will initiate reading from the transaction file via the transaction 
     * reader, and delivering results back to the POST.
     * @param post the POST we are connected to
     */
    public void connectTo(POST post) {
    }
}
