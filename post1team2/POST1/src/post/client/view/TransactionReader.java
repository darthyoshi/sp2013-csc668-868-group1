package post.client.view;

import java.io.BufferedReader;
import java.io.Reader;
import post.client.controller.TransactionBuilder;
import post.model.Transaction;


/**
 * Class TransactionReader
 */
public class TransactionReader {
    private BufferedReader reader;

    /**
     * Construct a new TransactionReader. This will read transactions from the 
     * specified stream. 
     * @param r 
     */
    public TransactionReader(Reader r) {
    }

    /**
     * Check whether or not any transactions are remaining.
     * @return boolean true if more transactions are available; otherwise false
     */
    public boolean hasMoreTransactions() {
        return false;
    }

    /**
     * Get the next transaction from the stream. A TransactionBuilder is 
     * provided to assist in assembling the transaction (this builder may 
     * connect back to a POST in order to perform validation, for instance to
     * confirm that UPCs are valid.)
     * @return Transaction
     * @param builder
     */
    public Transaction getNextTransaction(TransactionBuilder builder) {
        return null;
    }
}
