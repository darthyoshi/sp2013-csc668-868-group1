package post.model;

/**
 * Class CheckPayment
 */
public class CheckPayment extends Payment {

    //
    // Fields
    //
    //
    // Constructors
    //
    public CheckPayment(float amount) {
        super(amount);
    }

    @Override
    public String toColumnOutput() {
        return "Paid by check";

    }

}
