/*
 * Customer class :
 * Used to interact with the Post class, start the sale and allow register to
 * enter customer information.
 */
package post;

import POST.Payment;
import java.util.Scanner;

/**
 *
 * @author kent
 */
public class Customer {
    String name;
    int[] quantity;
    String[] upc;
    int numItem;
    POST post;
    
    public Customer(String name){
        this.name = name;
        this.post = new POST();
    }
    
    public void addItem(String upc, int quantity){
    }
    
    public void delItem(){       
    }
       
    public void updateItem(){      
    }
    
    public void purchaseItem(ProductCatalog productCatalog, TransactionItem item)  {
        //this.upc = item.getUPC();
        //this.quantity = item.getQuantity();
        
        //Validate each item with its upc code and call for POST class to display each item
        for(int i=0; i<100 ;i++){
       
        
        upc  = new String[100];
        upc[0] = item.getUPC();
        if(upc.length < 4){
            System.out.print("invalid upc code");
           
        }else if( productCatalog.getItemName(upc[0]).isEmpty()){
            System.out.print("empty upc code");
        }
        else {
            post.displayItem(productCatalog.getItemName(upc[0]), upc[0],item.getQuantity(), productCatalog.getPrice(upc[0]));
           
        }
        }  //end of for loop 
        
        
    }
    public void payTransaction(Payment payment) throws Exception{
        
        post.displayPayment(payment);
    }
    
    // start the customer 
    public void start(){
        String Cname = "";
        Customer customer =  new Customer(Cname);
    }   
        
    // testing
    public static void main(String[] args){
       // TransactionItem item =  new TransactionItem();
        //Payment payment = new Payment();
       // Customer testCustomer = new Customer();
       // testCustomer.purchaseItem(item);
        //testCustomer.payTransaction(payment);
        
    } 
}
