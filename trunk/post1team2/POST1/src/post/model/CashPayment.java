package post.model;



/**
 * Class CashPayment
 */
public class CashPayment extends Payment {

  //
  // Fields
  //

  private float fullAmount;
  
  //
  // Constructors
  //
  public CashPayment () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of fullAmount
   * @param newVar the new value of fullAmount
   */
  private void setFullAmount ( float newVar ) {
    fullAmount = newVar;
  }

  /**
   * Get the value of fullAmount
   * @return the value of fullAmount
   */
  private float getFullAmount ( ) {
    return fullAmount;
  }

  //
  // Other methods
  //

}
