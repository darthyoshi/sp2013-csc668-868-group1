package post.server.controller;

import post.model.Receipt;
import post.model.Transaction;



/**
 * Interface TransactionReceiver
 */
public interface TransactionReceiver {

  //
  // Fields
  //

  
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
  public Receipt recordTransaction( Transaction transaction );


}
