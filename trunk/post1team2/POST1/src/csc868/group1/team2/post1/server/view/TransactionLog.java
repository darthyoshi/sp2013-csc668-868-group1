package csc868.group1.team2.post1.server.view;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

/**
 * The transaction log is responsible for recording all transactions issued 
 * by POSTs. 
 * 
 * TODO: Move to server.model???
 * 
 * @author woeltjen
 */
public class TransactionLog {
    private File logFile; 
    
    public TransactionLog(String filename) {
        logFile = new File(filename);
    }
    
    /**
     * Record a transaction to persistent storage.
     * @param t (the transaction) 
     */
    public boolean recordTransaction(Object t) {
        try {
            Writer appender = new FileWriter(logFile, true);
            appender.write(t.toString()); // TODO: Format as receipt?
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
