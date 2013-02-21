package post.server.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import post.model.Receipt;


/**
 * Logs transaction information to a file.
 * @author woeltjen
 */
public class SalesLog {
    private Writer log;

    /**
     * Create a new transaction log. Transaction information will be recorded
     * to the specified file.
     * @param f
     * @throws IOException
     */
    public SalesLog(File f) throws IOException {
        this(new FileWriter(f, true));
    }

    /**
     * Create a new sales log. Transaction information will be written
     * to the specified stream.
     * @param w
     */
    public SalesLog(Writer w) {
        log = w;
    }

    /**
     * Write a record of a sale to the sales log.
     * @param r the receipt to log
     */
    public void recordSale(Receipt r) {
        try {
            log.write(r.toColumnOutput() + "\n");
            log.flush();
        } catch (Exception e) {
            // As a last resort, use System.err to log the failure
            System.err.println("Error writing to sales log.");
            System.err.println("The following sale was not recorded:");
            System.err.println(r.toColumnOutput() + "\n" );
        }
    }
}
