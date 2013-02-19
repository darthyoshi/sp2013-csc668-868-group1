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
public class RemoteStoreImpl implements RemoteStore {
    private Store store;

    public RemoteStoreImpl(Store store) {
        this.store = store;
    }
    
    public RemoteStoreImpl(RemoteStore store) {
        
    }

    @Override
    public Receipt recordTransaction(Transaction transaction)  {
        return store.recordTransaction(transaction);
    }

    @Override
    public StoreDescription getDescription() {
        return store.getDescription();
    }

    @Override
    public ProductCatalog getCatalog() {
        return store.getCatalog();
    }
    
}
