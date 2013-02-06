package post.client.controller;


import post.server.controller.Store;


/**
 *
 * @author Kay Choi
 */
public class POSTInitializer {
    private Store store;

    public POSTInitializer(Store store) {
        this.store = store;
    }

    public POST initialize() {
        return new POSTImpl(store);
    }
}
