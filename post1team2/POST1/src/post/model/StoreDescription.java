package post.model;

/**
 * Provides a basic description of a specific store.
 * @author woeltjen
 */
public class StoreDescription {
    private String address;
    private String name;

    /**
     * Create a new description for a store.
     * @param name the name of the store
     * @param address the store's address
     */
    public StoreDescription(String name, String address) {
        this.address = address;
        this.name = name;
    }

    /**
     * Get the store's address
     *
     * @return the store's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Get the store's name
     *
     * @return the store's name
     */
    public String getName() {
        return name;
    }
}
