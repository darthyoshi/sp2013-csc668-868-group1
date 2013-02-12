package post.model;

/**
 * A Payment represents monetary value exchanged for products.
 *
 * Payment is an abstract class. Specific payment forms are represented as
 * subclasses.
 */
public abstract class Payment {

    private float amount;

    /**
     * Create a payment for the specified amount due.
     *
     * @param amount
     */
    public Payment(float amount) {
        this.amount = amount;
    }

    /**
     * Get the amount paid.
     *
     * @return the amount paid
     */
    public float getAmount() {
        return amount;
    }

    /**
     * Describe this payment in a manner appropriate for display on a
     * receipt or in a transaction log.
     * @return 
     */
    public abstract String toColumnOutput();
}
