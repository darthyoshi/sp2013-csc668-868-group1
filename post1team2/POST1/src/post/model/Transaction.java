package post.model;


import java.util.*;
import java.util.Map.Entry;
import post.server.controller.Store;


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
            Map<String,Integer> lineItems, Store store) {
        this.customer = customer;
        this.timestamp = timestamp;
        this.payment = payment;
        this.store = store.getDescription();
        this.lineItems = new ArrayList<LineItem>();
        
        for (Entry<String, Integer> entry : lineItems.entrySet()) {
            this.lineItems.add(new LineItem(store.getCatalog().lookup(entry.getKey()), entry.getValue()));
        }
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
  public long getTimestamp ( ) {
    return timestamp;
  }


  /**
   * Get the value of payment
   * @return the value of payment
   */
  public Payment getPayment ( ) {
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
  public List<LineItem> getLineItems ( ) {
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
  public StoreDescription getStore ( ) {
    return store;
  }

  //
  // Other methods
  //

  public String getCustomer() {
      return customer;
  }
  
}
