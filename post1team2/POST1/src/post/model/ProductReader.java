package post.model;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Class ProductReader
 */
public class ProductReader {
    private Reader reader;

    /**
     * Create a new ProductReader. This will look at the supplied Reader 
     * object in order to find product information.
     * @param r 
     */
    public ProductReader(Reader r) {
    }

    /**
     * Read in the product catalog. This produces a ProductCatalog containing 
     * ProductSpecifications, which can be used to look up products by UPC 
     * and so forth.
     * @return ProductCatalog
     */
    public ProductCatalog readCatalog() throws IOException {
        return null;
    }
    
    public static void main (String[] args) {        
        // Test ProductReader
        Reader r;
        
        try {
            r = new FileReader("products.txt");
            ProductCatalog cat = new ProductReader(r).readCatalog();
            System.out.println(cat.lookup("XBHH").getDescription());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
