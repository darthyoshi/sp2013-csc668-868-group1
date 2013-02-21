package post.model;

/**
 * Represents payment made by check.
 * @author woeltjen
 */
public class CheckPayment extends Payment {
    public CheckPayment(float amount) {
        super(amount);
    }

    @Override
    public String toColumnOutput() {
        return "Paid by check";
    }
}
