package post.model;

/**
 * A CashPayment represents payment given by cash, which should equal or 
 * exceed the amount due.
 * @auther woeltjen
 */
public class CashPayment extends Payment {
    private float fullAmount;

    /**
     * Create a new cash payment. This includes both the amount offered 
     * by the customer, and the amount due (the change received is implicitly 
     * the difference between the two.)
     * @param tendered
     * @param due 
     */
    public CashPayment(float tendered, float due) {
        super(due);
        fullAmount = tendered;
    }

    @Override
    public String toColumnOutput() {
        return "Amount Tendered: " + fullAmount + "\n"
                + "Amount Returned: " + (fullAmount - getAmount());

    }

}
