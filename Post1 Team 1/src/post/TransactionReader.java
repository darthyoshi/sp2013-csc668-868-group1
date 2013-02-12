
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package post;

import java.io.*;

/**
 *Reads one transaction from the transaction file
 * @author steven
 */
public class TransactionReader {

    private FileInputStream fstream;
    private DataInputStream in;
    private BufferedReader br;
    private String strLine;

    /**
     * initializes BufferedReader
     * @param transactionFile contains filename to open
     * @throws Exception 
     */
    public TransactionReader(String transactionFile) throws Exception {
        fstream = new FileInputStream(transactionFile);
        in = new DataInputStream(fstream);
        br = new BufferedReader(new InputStreamReader(in));
        //Read File Line By Line
    }
//TransactionReader(Store store, String transactionFile){}

    /**
     * Checks if a file has more transactions
     * @return true if file has more transactions
     */
    public boolean hasMoreTransactions() {
        boolean hasMore = false;
        try {
            if ((strLine = br.readLine()) != null) {
                hasMore = true;
            } else {
                hasMore = false;
            }
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
        return hasMore;
    }

    /**
     * Prints transaction information
     */
    void readTransaction() {
        try {
            do {
                // If it is a blank line, transaction is finished
                if (strLine.length() == 0) {
                    System.out.println();
                    break;
                } else {
                    System.out.println(strLine);
                }
            } while ((strLine = br.readLine()) != null);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

    }

    /**
     * Gets the next transaction in the file and prints it
     * @return It should return a transaction object
     */
    Transaction getNextTransaction() {
        if (this.hasMoreTransactions()) {
            this.readTransaction();
        }
        return null;
    }
    

    public static void main(String[] args) throws Exception {
        TransactionReader reader = new TransactionReader("transaction.txt");
        reader.getNextTransaction();
        reader.getNextTransaction();
    }
}
