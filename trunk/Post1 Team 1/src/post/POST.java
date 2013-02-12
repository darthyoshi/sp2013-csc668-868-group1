/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package post;

import POST.Payment;

/**
 *
 * @author kent
 */
public class POST implements IPOST {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
    }
    
    public void getName(){
       
    }
    
    public void addItem()throws Exception{
        
    }
    public void delItem()throws Exception{
    }
    public void getItem()throws Exception{
        
    }
    
    public void makePayment() throws Exception{
        
    }
    public void paymentMethod()throws Exception{
        
    }
    
    public void displayItem(String item, String upc,int quantity,  double price){
        System.out.print("item name:"+item+ "upc code:"+upc +"Quantity: "+quantity+ "price: "+price);
    }
    public void displayPayment(Payment payment) throws Exception{
        System.out.print("Customer name:"+ payment.getPaymentDetails()+ "");
    }
    
    //public void 
}
