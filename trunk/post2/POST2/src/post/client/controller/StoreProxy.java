/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package post.client.controller;

import java.rmi.RemoteException;
import java.util.Queue;
import post.model.ProductCatalog;
import post.model.Receipt;
import post.model.StoreDescription;
import post.model.Transaction;
import post.remote.RemoteStore;
import post.server.controller.Store;

/**
 *
 * @author kent
 */
public class StoreProxy implements Store {
    ProductCatalog productcatalog;
    Queue transactionQue;
    RemoteStore RemoteStore;
    StoreDescription storeDescri;
    
    public StoreProxy(RemoteStore remotestore )throws RemoteException{
        this.RemoteStore = RemoteStore;   
        
        productcatalog = RemoteStore.getCatalog();
        storeDescri = RemoteStore.getDescription(); 
            
       
    }
    
    @Override
    public Receipt recordTransaction(Transaction transaction){
        Transaction trans;
        trans = transaction;
        //record the new transaction
        transactionQue.add(trans);
        
        while(!transactionQue.isEmpty()){
            System.out.print("Retrieving last transaction");
           // retrieve and remove from the transaction queue 
            trans =(Transaction) transactionQue.poll();
                
            try{
                Receipt receipt = RemoteStore.recordTransaction(trans);
                return receipt;
            }catch(RemoteException recordTransErr){
                //if connection is lost , put the trans (back) into the transaction queue
                System.err.printf("Lost Connectin CANNOT retrieve RECEIPT, PLEASE TRY AGAIN");
                transactionQue.add(trans);
                
            }
        }
        return new Receipt(trans);
    } 
    
    public StoreDescription getDescription(){
         try{
            storeDescri = RemoteStore.getDescription(); 
            return storeDescri;
        }catch(RemoteException descriptionErr){
            System.err.printf("Lost Connectin, CANNOT retrieve STORE DESCRIPTION, PLEASE TRY AGAIN");
            return storeDescri ;
        }
         
       
    }
    
    public ProductCatalog getCatalog(){
        try{
            productcatalog = RemoteStore.getCatalog();
            return productcatalog;
        }catch(RemoteException productcatalogErr){
            System.err.printf("Lost Connectin, CANNOT retrieve PRODUCTCATALOG, PLEASE TRY AGAIN");
            return productcatalog;
        }
       
     };
    
}
