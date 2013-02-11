package post.model;

/**
 * Represents payment made by credit card.
 * @author woeltjen
 */
public class CreditPayment extends Payment {
    private long ccNumber;

    /**
     * Create a new object representing a payment of
     * the specified amount, using the specified credit card number.
     * @param amount
     * @param ccNumber
     */
    public CreditPayment(float amount, long ccNumber) {
        super(amount);
        this.ccNumber = ccNumber;
    }

    /**
     * Get the value of ccNumber
     *
     * @return the value of ccNumber
     */
    public long getCcNumber() {
        return ccNumber;
    }

    @Override
    public String toColumnOutput() {
        return "Paid by Credit Card " + ccNumber;
    }
}
