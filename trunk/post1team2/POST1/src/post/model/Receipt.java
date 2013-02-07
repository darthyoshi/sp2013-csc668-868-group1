package post.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;



/**
 * Class Receipt
 */
public class Receipt {
    private static final DateFormat DATE_FORMAT = 
            new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    
  //
  // Fields
  //

  private Transaction transaction;
  
  //
  // Constructors
  //
  public Receipt (Transaction t ) {
      transaction = t;
  }

  /**
   * Get the value of transaction
   * @return the value of transaction
   */
  private Transaction getTransaction ( ) {
    return transaction;
  }

  //
  // Other methods
  //

  /**
   * @return       String
   */
  public String toColumnOutput(  )
  {
      Calendar cal = new GregorianCalendar();
      cal.setTimeInMillis(transaction.getTimestamp());      
              
      String output = "";
      output += transaction.getStore().getName() + "\n";
      output += transaction.getStore().getAddress() + "\n";
      output += "\n";
      output += transaction.getCustomer() + "\t";
      output += DATE_FORMAT.format(cal.getTime()) + "\n";
      
      for (LineItem item : transaction.getLineItems()) {
          output += item.getProductSpec().getDescription() + "\t";
          output += item.getQuantity() + " @ "; 
          output += item.getProductSpec().getPrice() + "\t";
          output += item.getQuantity() * item.getProductSpec().getPrice() + "\n";          
      }
      
      output += "------\n";
      output += "Total: " + transaction.getPayment().getAmount() + "\n";
      output += transaction.getPayment().toColumnOutput();
      
      return output;
  }


}
