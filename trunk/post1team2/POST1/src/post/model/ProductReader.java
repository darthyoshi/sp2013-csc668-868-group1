package post.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Class ProductReader
 * 
 * @author woeltjen
 */
public class ProductReader {
    private Reader reader;

    /**
     * Create a new ProductReader. This will look at the supplied Reader 
     * object in order to find product information.
     * @param r 
     */
    public ProductReader(Reader r) {
            this.reader = r;
    }

    /**
     * Read in the product catalog. This produces a ProductCatalog containing 
     * ProductSpecifications, which can be used to look up products by UPC 
     * and so forth.
     * @return ProductCatalog
     */
    public ProductCatalog readCatalog() throws IOException {
        BufferedReader r = new BufferedReader(reader);
        List<ProductSpecification> products = 
            new ArrayList<ProductSpecification>();

        String nextLine;
        while ( (nextLine = r.readLine()) != null) {
            String upc   = nextLine.substring(0, 4);
            String desc  = nextLine.substring(9, 29);
            float  price = Float.parseFloat(nextLine.substring(34));
            products.add(new ProductSpecification(upc, desc, price));
        }

        return new ProductCatalog(products);
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
