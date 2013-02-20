package post.server.controller;

import java.util.List;
import post.model.LineItem;
import post.model.Payment;
import post.model.Receipt;
import post.model.Transaction;

/**
 *
 * @author bia
 */
public class PaymentProcessor implements TransactionReceiver
{   
    /**
     * 
     * @param transaction
     * @return A Receipt object will be returned if the payment is accepted; else
     * null if payment is declined or incorrect amount.
     */
    @Override
    public Receipt recordTransaction(Transaction transaction) 
    {
        double amountPaid = transaction.getPayment().getAmount();
        Payment pay = transaction.getPayment();
        
        if(pay.toColumnOutput().contains("Paid by Credit Card"))
        {
            if(!processCredit()) return null;
        }
        if(amountPaid < getAmountDue(transaction.getLineItems())) 
            return null;
        
        return (new Receipt(transaction));
    }
    
    
    private double getAmountDue(List<LineItem> items)
    {
        double total = 0.0;
       
        for(int i = 0; i < items.size()-1; i++)
        {
            LineItem currentItem = items.get(i);
            total += (currentItem.getProductSpec().getPrice() * currentItem.getQuantity());
        }
        return total;
    }
    
    /**
     * 
     * @return False if credit card is declined; else true.
     */
    private boolean processCredit()
    {
        double random = Math.random() * 100;
        if(random < 10.0)
            return false;
        else
            return true;
    }
}
