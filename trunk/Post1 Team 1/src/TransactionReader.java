/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;

/**
 *
 * @author academic
 */
public class TransactionReader {

    private FileInputStream fstream;
    private DataInputStream in;
    private BufferedReader br;
    private String strLine;
    private String customerName;

    public TransactionReader(String transactionFile) throws Exception {
        fstream = new FileInputStream(transactionFile);
        in = new DataInputStream(fstream);
        br = new BufferedReader(new InputStreamReader(in));
        //Read File Line By Line
    }
//TransactionReader(Store store, String transactionFile){}

    public boolean hasMoreTransactions() {
        boolean hasMore = false;
        try {
            if ((strLine = br.readLine()) != null) {
                customerName = strLine;
                hasMore = true;
            } else {
                hasMore = false;
            }
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
        return hasMore;
    }

    void readTransaction() {
        try {
            do {
                // Print the content on the console
                if (strLine.length() == 0) {
                    System.out.println();
                    break;
                } else {
                    System.out.println(strLine);
                }
            } while ((strLine = br.readLine()) != null);
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }

    }

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
