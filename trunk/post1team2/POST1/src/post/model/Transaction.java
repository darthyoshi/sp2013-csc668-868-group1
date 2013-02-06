package post.model;


import java.util.*;


/**
 * Class Transaction
 */
public class Transaction {

  //
  // Fields
  //
  private String customer;
  private long timestamp;
  private Payment payment;
  private List<LineItem> lineItems;
  private StoreDescription store;
  
  //
  // Constructors
  //

    public Transaction(String customer, long timestamp, Payment payment, 
            Map<String,Integer> lineItems, StoreDescription store) {
        this.customer = customer;
        this.timestamp = timestamp;
        this.payment = payment;
        this.store = store;
        //TODO line items
    }
  
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of timestamp
   * @param newVar the new value of timestamp
   */
  private void setTimestamp ( long newVar ) {
    timestamp = newVar;
  }

  /**
   * Get the value of timestamp
   * @return the value of timestamp
   */
  private long getTimestamp ( ) {
    return timestamp;
  }

  /**
   * Set the value of payment
   * @param newVar the new value of payment
   */
  private void setPayment ( Payment newVar ) {
    payment = newVar;
  }

  /**
   * Get the value of payment
   * @return the value of payment
   */
  private Payment getPayment ( ) {
    return payment;
  }

  /**
   * Set the value of lineItems
   * @param newVar the new value of lineItems
   */
  private void setLineItems ( List<LineItem> newVar ) {
    lineItems = newVar;
  }

  /**
   * Get the value of lineItems
   * @return the value of lineItems
   */
  private List<LineItem> getLineItems ( ) {
    return lineItems;
  }

  /**
   * Set the value of store
   * @param newVar the new value of store
   */
  private void setStore ( StoreDescription newVar ) {
    store = newVar;
  }

  /**
   * Get the value of store
   * @return the value of store
   */
  private StoreDescription getStore ( ) {
    return store;
  }

  //
  // Other methods
  //

}
