package post.model;


import java.util.Collections;
import java.util.List;




/**
 * Class ProductCatalog
 */
public class ProductCatalog {

  //
  // Fields
  //

  
  //
  // Constructors
  //
  public ProductCatalog () { };
  
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
   * @return       ProductSpecification
   * @param        upc
   */
  public ProductSpecification lookup( String upc )
  {
      return null;
  }


  /**
   * @return       List<ProductSpecification>
   */
  public List<ProductSpecification> getProducts(  )
  {
      return Collections.emptyList();
  }


}
