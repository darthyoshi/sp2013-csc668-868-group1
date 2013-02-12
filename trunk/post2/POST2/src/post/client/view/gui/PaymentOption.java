/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package post.client.view.gui;

import post.model.CashPayment;
import post.model.CheckPayment;
import post.model.CreditPayment;
import post.model.Payment;

/**
 *
 * @author woeltjen
 */
public enum PaymentOption {
    CASH() {

        @Override
        public Payment createPayment(float amount, String description) {
            return new CashPayment(Float.parseFloat(description), amount);
        }
        
    },
    CREDIT() {

        @Override
        public Payment createPayment(float amount, String description) {
            return new CreditPayment(amount, Long.parseLong(description));
        }
        
    },
    CHECK() {

        @Override
        public Payment createPayment(float amount, String description) {
            return new CheckPayment(amount);
        }
        
    }
    
    ;
    
    public abstract Payment createPayment(float amount, String description);
}
