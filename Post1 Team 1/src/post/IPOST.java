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
public interface IPOST {
    public void addItem()throws Exception;
    public void delItem()throws Exception;
    public void getItem()throws Exception;
    public void makePayment() throws Exception;
    public void paymentMethod()throws Exception;
    public void displayPayment(Payment payment) throws Exception;
}
