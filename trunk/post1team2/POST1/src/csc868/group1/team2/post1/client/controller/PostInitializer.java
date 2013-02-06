package csc868.group1.team2.post1.client.controller;

import csc868.group1.team2.post1.server.controller.Store;

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
