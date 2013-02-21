package post.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import post.model.ProductCatalog;
import post.model.Receipt;
import post.model.StoreDescription;
import post.model.Transaction;
import post.store.Store;

/**
 * Provides an RMI-ready implementation of RemoteStore. This simply wraps 
 * around a regular Store object and delegates methods to it. It is a 
 * UnicastRemoteObject, so the wrapped Store object may remain server-side 
 * while this class receives messages from clients.
 * 
 * @author woeltjen
 */
public class RemoteStoreImpl extends UnicastRemoteObject implements RemoteStore {
    private Store store;

    public RemoteStoreImpl(Store store) throws RemoteException {
        super();
        this.store = store;
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
