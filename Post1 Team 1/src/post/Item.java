/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package post;

public class Item {
    private String  name;
    private float   price;
    private String  upc;
    
    public void setItemName(String name)
    {
        this.name = name;
    }
    
    public void setPrice(float price)
    {
        this.price = price;
    }
    
    public void setUPC(String upc)
    {
        this.upc = upc;
    }
    
    public String getItemName()
    {
        return name;
    }
    
    public float getPrice()
    {
        return price;
    }
    
    public String getUPC()
    {
        return upc;
    }
}
