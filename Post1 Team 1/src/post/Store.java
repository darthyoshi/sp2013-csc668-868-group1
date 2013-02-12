/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package post;

/**
 *
 * @author kent
 */
public class Store {
    String storeName;
    ProductCatalog productCatalog;
    
    
    public Store(String storeName, ProductCatalog productCatalog){
        this.storeName = storeName;
        this.productCatalog= productCatalog;
    }
    
    public String getStoreName(){
        return storeName;
    }
}
