package post.client.controller;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.Queue;
import post.model.ProductCatalog;
import post.model.Receipt;
import post.model.StoreDescription;
import post.model.Transaction;
import post.remote.RemoteStore;
import post.store.Store;

/**
 *
 * @author kent
 */
public class StoreProxy implements Store {
    ProductCatalog productcatalog;
    Queue transactionQue = new LinkedList();
    RemoteStore remoteStore;
    StoreDescription storeDescri;

    public StoreProxy(RemoteStore remotestore )throws RemoteException{
        remoteStore = remotestore;

        productcatalog = remoteStore.getCatalog();
        storeDescri = remoteStore.getDescription();


    }

    @Override
    public Receipt recordTransaction(Transaction transaction){
        // First, flush any pending transactions
        while (!transactionQue.isEmpty()) {
            Transaction t = (Transaction) transactionQue.peek();
            try {
                remoteStore.recordTransaction(t);
                transactionQue.poll(); // Remove from queue
            } catch (RemoteException re) {
                break; // Don't keep trying if disconnected
            }
        }
        
        // Submit the current transaction
        try {
            return remoteStore.recordTransaction(transaction);
        } catch (RemoteException re) { 
            // Something went wrong - queue for later
            transactionQue.add(transaction);
            // ...but report back as though the transaction has succeeded
            return new Receipt(transaction);
        }
    }

    public StoreDescription getDescription(){
         try{
            storeDescri = remoteStore.getDescription();
            return storeDescri;
        }catch(RemoteException descriptionErr){
            return storeDescri ;
        }


    }

    public ProductCatalog getCatalog(){
        try{
            productcatalog = remoteStore.getCatalog();
            return productcatalog;
        }catch(RemoteException productcatalogErr){
            return productcatalog;
        }

     };

}
