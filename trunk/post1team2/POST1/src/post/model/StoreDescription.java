package post.model;

import java.util.*;

/**
 * Class StoreDescription
 */
public class StoreDescription {

    //
    // Fields
    //
    private String address;
    private String name;

    public StoreDescription(String address, String name) {
        this.address = address;
        this.name = name;
    }

    //
    // Methods
    //
    //
    // Accessor methods
    //
    /**
     * Set the value of address
     *
     * @param newVar the new value of address
     */
    private void setAddress(String newVar) {
        address = newVar;
    }

    /**
     * Get the value of address
     *
     * @return the value of address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set the value of name
     *
     * @param newVar the new value of name
     */
    private void setName(String newVar) {
        name = newVar;
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }
    //
    // Other methods
    //
}
