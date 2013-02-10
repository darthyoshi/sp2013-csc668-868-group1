package post;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProductReader 
{
    private BufferedReader reader;
    
    ProductReader(String fileName) throws IOException
    {
        reader = new BufferedReader(new FileReader(fileName));
    }
    
    public boolean hasMoreProduct() throws IOException
    {
        return reader.ready();
    }
    
    public Item getNextProduct() throws IOException
    {
        Item item = new Item();
        String productRecordLine = reader.readLine();
        StringBuilder buff = new StringBuilder(productRecordLine);
        
        item.setUPC(buff.substring(0, 4));
        item.setItemName(buff.substring(10, 30));
        item.setPrice(Float.parseFloat(buff.substring(35)));
        
        return item;
    }
  
}
