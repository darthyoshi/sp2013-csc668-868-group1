package post.server.controller;

import post.model.Receipt;
import post.model.Transaction;



/**
 * Class TransactionLog
 */
public class TransactionLog implements TransactionReceiver {

  //
  // Fields
  //

  
  //
  // Constructors
  //
  public TransactionLog () { };
  
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
   * @return       Receipt
   * @param        transaction
   */
  public Receipt recordTransaction( Transaction transaction )
  {
      return null;
  }


}
