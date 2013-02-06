package post.client.view;

import post.client.controller.POST;



/**
 * Class FileBasedCashier
 */
public class FileBasedCashier implements CashierView {

  //
  // Fields
  //

  private TransactionReader reader;
  
  //
  // Constructors
  //
  public FileBasedCashier () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of reader
   * @param newVar the new value of reader
   */
  private void setReader ( TransactionReader newVar ) {
    reader = newVar;
  }

  /**
   * Get the value of reader
   * @return the value of reader
   */
  private TransactionReader getReader ( ) {
    return reader;
  }

  //
  // Other methods
  //

  /**
   * @param        post
   */
  public void connectTo( POST post )
  {
  }


}
