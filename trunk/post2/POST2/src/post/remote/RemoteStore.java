/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package post.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import post.model.ProductCatalog;
import post.model.Receipt;
import post.model.StoreDescription;
import post.model.Transaction;

/**
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
    
    public Receipt recordTransaction(Transaction t) throws RemoteException;
}
