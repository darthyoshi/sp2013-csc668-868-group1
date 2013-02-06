package post.model;



/**
 * Class Receipt
 */
public class Receipt {

  //
  // Fields
  //

  private Transaction transaction;
  
  //
  // Constructors
  //
  public Receipt () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of transaction
   * @param newVar the new value of transaction
   */
  private void setTransaction ( Transaction newVar ) {
    transaction = newVar;
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
      return "";
  }


}
