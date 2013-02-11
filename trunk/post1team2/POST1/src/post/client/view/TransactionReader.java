package post.client.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import post.client.controller.TransactionBuilder;
import post.model.*;

/**
 * Class TransactionReader
 */
public class TransactionReader {

    private BufferedReader reader;
    private List<String> nextBlock;

    //
    // Constructors
    //
    public TransactionReader(Reader r) {
        reader = new BufferedReader(r);
        prepareBlock();
    }

    /**
     * @return boolean
     */
    public boolean hasMoreTransactions() {
        return !nextBlock.isEmpty() && nextBlock.size() > 2;
    }

    /**
     * @return Transaction
     * @param builder
     */
    public Transaction getNextTransaction(TransactionBuilder builder) {
        builder.setCustomer(nextBlock.get(0).trim());
        for (int i = 1; i < nextBlock.size() - 1; i++) {
            String lineItem = nextBlock.get(i);
            String upc = lineItem.substring(0, 4);
            int quantity = 1;
            if (lineItem.length() > 10) { // ...contains explicit quantity
                quantity = Integer.parseInt(lineItem.substring(10));
            }
            builder.addLineItem(upc, quantity);
        }
        Payment p = parsePayment(
                nextBlock.get(nextBlock.size()-1), builder.getAmountDue());
        prepareBlock();
        return builder.completeSale(p);
    }

    private Payment parsePayment(String line, float amountDue) {
        
        int x = 1;
        
        do{
        try{
        if (line.startsWith("CASH")) {
            float amount = Float.parseFloat(line.substring(line.indexOf('$') + 1));
            return new CashPayment(amount, amountDue);
        } else if (line.startsWith("CREDIT")) {
            long ccNumber = Long.parseLong(line.substring(line.indexOf(' ') + 1));
            return new CreditPayment(amountDue, ccNumber);
        } else if (line.startsWith("CHECK")) {
            return new CheckPayment(amountDue);
        }
        }
        
        catch(Exception e){
               System.out.println("Error: Specify payment type.");
        }
        }while(x==1);
        
        return null; 
    }
    
    private void prepareBlock() {
        nextBlock = new ArrayList<String>();
        String nextLine;
        try {
            while ((nextLine = reader.readLine()) != null) {
                if (nextLine.trim().isEmpty()) return; // Stop after newline
                nextBlock.add(nextLine);
            }
        } catch (IOException ioe) {
        }
    }
}
