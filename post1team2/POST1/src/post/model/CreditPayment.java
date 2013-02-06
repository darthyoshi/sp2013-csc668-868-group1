package post.model;



/**
 * Class CreditPayment
 */
public class CreditPayment extends Payment {

  //
  // Fields
  //

  private int ccNumber;
  
  //
  // Constructors
  //
  public CreditPayment () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of ccNumber
   * @param newVar the new value of ccNumber
   */
  private void setCcNumber ( int newVar ) {
    ccNumber = newVar;
  }

  /**
   * Get the value of ccNumber
   * @return the value of ccNumber
   */
  private int getCcNumber ( ) {
    return ccNumber;
  }

  //
  // Other methods
  //

}
