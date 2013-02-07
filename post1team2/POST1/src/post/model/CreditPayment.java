package post.model;

/**
 * Class CreditPayment
 */
public class CreditPayment extends Payment {

    //
    // Fields
    //
    private long ccNumber;

    //
    // Constructors
    //
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
