package post.model;


import java.util.*;


/**
 * Class LineItem
 */
public class LineItem {

  //
  // Fields
  //

  private ProductSpecification productSpec;
  private int quantity;
  
  //
  // Constructors
  //
  public LineItem () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of productSpec
   * @param newVar the new value of productSpec
   */
  private void setProductSpec ( ProductSpecification newVar ) {
    productSpec = newVar;
  }

  /**
   * Get the value of productSpec
   * @return the value of productSpec
   */
  private ProductSpecification getProductSpec ( ) {
    return productSpec;
  }

  /**
   * Set the value of quantity
   * @param newVar the new value of quantity
   */
  private void setQuantity ( int newVar ) {
    quantity = newVar;
  }

  /**
   * Get the value of quantity
   * @return the value of quantity
   */
  private int getQuantity ( ) {
    return quantity;
  }

  //
  // Other methods
  //

}
