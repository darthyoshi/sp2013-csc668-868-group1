package post.server.controller;

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
    private static final String DEFAULT_LOG_NAME = "transaction.txt";
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
        //StoreDescription desc = new StoreDescription("test name", "test address");
        //TransactionLog log = new FileBasedTransactionLog(logName)        
        //ProductCatalog catalog = new ProductReader(productFileName);
        //return new StoreImpl(desc, catalog, log);
        return new StoreImpl(null, null, null);
    }
    
    public static void main(String[] args) {
        Store s = new StoreInitializer().initialize();
    }
}
