package csc868.group1.team2.post1.client.controller;

/**
 *
 * @author Kay Choi
 */
public class PostInitializer {
    private Store store;

    public PostInitializer(Store store) {
        this.store = store;
    }

    public Post initialize() {
        return new Post(store);
    }
}
