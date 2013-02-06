package post.server.controller;

import post.model.ProductCatalog;
import post.model.StoreDescription;





/**
 * Interface Store
 */
public interface Store extends TransactionReceiver {
  public StoreDescription getDescription() ;

  /**
   * @return       ProductCatalog
   */
  public ProductCatalog getCatalog(  );


}
