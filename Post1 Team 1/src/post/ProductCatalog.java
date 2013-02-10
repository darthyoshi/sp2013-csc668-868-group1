package post;

import java.util.*;
import java.io.IOException;

public class ProductCatalog 
{
    private HashMap<String, Item> productCatalog;
    private ProductReader productReader;
    
    ProductCatalog(String fileName) throws IOException
    {
        productCatalog = productCatalog = new HashMap<>();
        init(fileName);
    }
    
    /*
     * If the item is in the Catalog, nothing will happen.
     * Each item is required to have a unique UPC.
     */
    public void addItem(Item item)
    {
        productCatalog.put(item.getUPC(), item);
    }
    
    public boolean checkUPC(String upc)
    {
        return productCatalog.containsKey(upc);
    }
    
    public float getPrice(String upc)
    {
	return productCatalog.get(upc).getPrice();
    }
    
    public String getItemName(String upc)
    {
        return productCatalog.get(upc).getItemName();
    }
    
    Collection<Item> getCatalog()
    {
        return productCatalog.values();
    }

    private void init(String fileName) throws IOException
    {
        productReader = new ProductReader(fileName);
        while(productReader.hasMoreProduct())
        {
            Item tmp = productReader.getNextProduct();
            productCatalog.put(tmp.getUPC(), tmp);
        }
    }
    
    /* TESTING
    public static void main(String[] args) 
    {
        try
        {
            ProductCatalog productCat = new ProductCatalog("src/product.txt");
            System.out.println("Catalog has upc 1234: " + productCat.checkUPC("1234"));
            System.out.println("Catalog has upc 2345: " + productCat.checkUPC("2345"));
            System.out.println("Catalog has upc 6943: " + productCat.checkUPC("6943"));
            
            System.out.println("Price of UPC 1234: $" + productCat.getPrice("1234"));
            System.out.println("Item Name of UPC 1234: " + productCat.getItemName("1234"));
        }
        catch(IOException io)
        {
            System.out.print("Product Catalog File cannot be read.");
        }
        
       
    }*/
}
