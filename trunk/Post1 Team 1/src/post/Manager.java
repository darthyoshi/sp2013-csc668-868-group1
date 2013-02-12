/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package post;

/**
 *
 * @author kent
 */
public class Manager {
    
    public Manager(){
        
    }
    
    public static void main(String[] args){
        Manager manager = new Manager();
        manager.start();
        manager.close();
    }
    
    public void start(){
        
        String storeName = "CSC668";
        String fileName ="";
        try{
        ProductCatalog productCatalog = new ProductCatalog(fileName);
        Store store =  new Store(storeName, productCatalog);
        System.out.print("WELCOME to " + store.storeName + "store");
        }catch(Exception e){
            System.err.print("error");
        }
       
    }
    public void close(){
        
    }
    
    //public void login() login system 
}
