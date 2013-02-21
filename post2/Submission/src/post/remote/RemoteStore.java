package post.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import post.model.ProductCatalog;
import post.model.Receipt;
import post.model.StoreDescription;
import post.model.Transaction;

/**
 * A RemoteStore offers an interface parallel to a regular Store, 
 * but may throw RemoteExceptions.
 * 
 * This acts as an adapter to allow a Store to be transmitted over RMI
 * without requiring all Store users to be cognizant of RemoteExceptions.
 * 
 * @author woeltjen
 */
public interface RemoteStore extends Remote {
    
    /**
     * Get a description of this store
     * @return
     */
    public StoreDescription getDescription() throws RemoteException;

    /**
     * Get a catalog of products available through this store.
     * @return       ProductCatalog
     */
    public ProductCatalog getCatalog() throws RemoteException;
    
    /**
     * Record the specified transaction. Note that this method is responsible
     * for issuing a Receipt object (this may be null in cases where errors
     * have occurred.)
     * @return       Receipt a receipt for the transaction
     * @param        transaction the transaction to record
     */
    public Receipt recordTransaction(Transaction t) throws RemoteException;
}
