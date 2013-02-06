package post.model;


import java.util.*;


/**
 * Class ProductSpecification
 */
public class ProductSpecification {

  //
  // Fields
  //

  private String upc;
  private String description;
  private float price;
  
  //
  // Constructors
  //
  public ProductSpecification () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of upc
   * @param newVar the new value of upc
   */
  private void setUpc ( String newVar ) {
    upc = newVar;
  }

  /**
   * Get the value of upc
   * @return the value of upc
   */
  private String getUpc ( ) {
    return upc;
  }

  /**
   * Set the value of description
   * @param newVar the new value of description
   */
  private void setDescription ( String newVar ) {
    description = newVar;
  }

  /**
   * Get the value of description
   * @return the value of description
   */
  private String getDescription ( ) {
    return description;
  }

  /**
   * Set the value of price
   * @param newVar the new value of price
   */
  private void setPrice ( float newVar ) {
    price = newVar;
  }

  /**
   * Get the value of price
   * @return the value of price
   */
  private float getPrice ( ) {
    return price;
  }

  //
  // Other methods
  //

}
