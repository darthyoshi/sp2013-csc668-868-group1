package post.server.controller;

import java.io.File;
import java.io.FileReader;
import post.model.ProductCatalog;
import post.model.ProductReader;
import post.model.StoreDescription;

/**
 * The StoreInitializer is responsible for setting up the Store.
 * 
 * During launch, the StoreInitializer is responsible for generating the 
 * Store object that will be used for future interactions. This may include 
 * initializing a product catalog and connecting to the transaction log.
 * 
 * @author woeltjen
 */
public class StoreInitializer {
    private static final String DEFAULT_STORE_NAME = "STORE NAME";
    private static final String DEFAULT_ADDRESS    = "123 Address St.";

    private static final String DEFAULT_LOG_NAME = "log.txt";
    private static final String DEFAULT_CATALOG_NAME = "products.txt";
    
    private String logName;
    private String productFileName;
    
    public StoreInitializer() {
        this(DEFAULT_LOG_NAME, DEFAULT_CATALOG_NAME);
    }

    public StoreInitializer(String logName, String productFileName) {
        this.logName = logName;
        this.productFileName = productFileName;
    }
    
    public Store initialize() {
        try {
            StoreDescription desc = 
                    new StoreDescription(DEFAULT_STORE_NAME, DEFAULT_ADDRESS);
            TransactionLog log = new TransactionLog(new File(logName));
            ProductCatalog catalog = new ProductReader(new FileReader(productFileName)).readCatalog();
        return new StoreImpl(desc, log, catalog);
        } catch (Exception e) {
            // TODO: How to log this?
            return null;
        }
    }
    
    public static void main(String[] args) {
        Store s = new StoreInitializer().initialize();
        System.out.println(s.getDescription().getName());
        System.out.println(s.getCatalog().getProducts().size());
        
    }
}
