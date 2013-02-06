package post.model;



/**
 * Class Payment
 */
public abstract class Payment {

  //
  // Fields
  //

  private double amount;
  
  //
  // Constructors
  //
  public Payment () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of amount
   * @param newVar the new value of amount
   */
  protected void setAmount ( double newVar ) {
    amount = newVar;
  }

  /**
   * Get the value of amount
   * @return the value of amount
   */
  public double getAmount ( ) {
    return amount;
  }

  //
  // Other methods
  //

  public String toColumnOutput() {
      return "";
  }
}
