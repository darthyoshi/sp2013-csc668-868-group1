/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package post.remote;

import java.rmi.RemoteException;
import post.model.ProductCatalog;
import post.model.Receipt;
import post.model.StoreDescription;
import post.model.Transaction;
import post.server.controller.Store;

/**
 *
 * @author woeltjen
 */
public class RemoteStoreWrapper implements Store {
    private RemoteStore store;

    public RemoteStoreWrapper(RemoteStore store) {
        this.store = store;
    }

    public Receipt recordTransaction(Transaction t) {
        try {
            return store.recordTransaction(t);
        } catch (RemoteException e) {
            return null;
        }
    }

    public StoreDescription getDescription() {
        try {
            return store.getDescription();
        } catch (RemoteException e) {
            return null;
        }
    }

   
    public ProductCatalog getCatalog() {
        try {
            return store.getCatalog();
        } catch (RemoteException e) {
            return null;
        }
    }
    
    
}
