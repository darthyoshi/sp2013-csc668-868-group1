package post.client.view;


import post.client.controller.TransactionBuilder;
import post.model.Transaction;



/**
 * Class TransactionReader
 */
public class TransactionReader {

  //
  // Fields
  //

  
  //
  // Constructors
  //
  public TransactionReader () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  //
  // Other methods
  //

  /**
   * @return       boolean
   */
  public boolean hasMoreTransactions(  )
  {
      return false;
  }


  /**
   * @return       Transaction
   * @param        builder
   */
  public Transaction getNextTransaction( TransactionBuilder builder )
  {
      return null;
  }


}
