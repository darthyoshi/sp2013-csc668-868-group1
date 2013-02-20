package post.model;

/**
 * PaymentOption enumerates all known payment types, and provides convenience 
 * methods for creating new payment objects.
 * @author woeltjen
 */
public enum PaymentOption {
    CASH() {

        @Override
        public Payment createPayment(float amount, String description) {
            try {
                float tendered = Float.parseFloat(description);
                return tendered>=amount ? new CashPayment(tendered, amount) : 
                        null;
            } catch (Exception e) {
                return null;
            }
        }
        
    },
    CREDIT() {

        @Override
        public Payment createPayment(float amount, String description) {
            try {
                if (description.length() != 16) return null;
                return new CreditPayment(amount, Long.parseLong(description));
            } catch (Exception e) {
                return null;
            }
        }
        
    },
    CHECK() {

        @Override
        public Payment createPayment(float amount, String description) {
            return new CheckPayment(amount);
        }
        
    }
    
    ;
    
    /**
     * Create a payment object of the specified type, using a given 
     * description. This returns null if the payment is not valid for some 
     * reason.
     * 
     * @param amount the amount of the payment
     * @param description a description of the payment
     * @return an object describing the payment
     */
    public abstract Payment createPayment(float amount, String description);
    
}
