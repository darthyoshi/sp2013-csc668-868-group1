package post.server.controller;


import post.model.ProductCatalog;
import post.model.Receipt;
import post.model.StoreDescription;
import post.model.Transaction;


/**
 * Class StoreImpl
 */
public class StoreImpl implements TransactionReceiver, Store {

  private StoreDescription storeDescription;
  private TransactionLog transactionLog;
  private ProductCatalog catalog;

    public StoreImpl(StoreDescription storeDescription, TransactionLog transactionLog, ProductCatalog catalog) {
        this.storeDescription = storeDescription;
        this.transactionLog = transactionLog;
        this.catalog = catalog;
    }
  


  /**
   * Get the value of storeDescription
   * @return the value of storeDescription
   */
  public StoreDescription getDescription ( ) {
    return storeDescription;
  }


  /**
   * Get the value of catalog
   * @return the value of catalog
   */
  public ProductCatalog getCatalog ( ) {
    return catalog;
  }

  /**
   * @return       Receipt
   * @param        transaction
   */
  public Receipt recordTransaction( Transaction transaction )
  {
      return transactionLog.recordTransaction(transaction);
  }


}
