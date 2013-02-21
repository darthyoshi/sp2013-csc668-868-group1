package post.server;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import post.remote.RemoteStoreImpl;
import post.server.controller.*;
import post.store.Store;

/**
 * Class Server is an RMI server for POST operations. The class is the server-side
 * interface between the back-end server operations and the front-end customer
 * interaction.
 * @author Kay Choi
 */
public class Server implements Remote {

    /**
     * Main method. Initializes the server to listen for connections.
     * @param args the set of command line arguments
     */
    public static void main (String args[]) {
        try {
            Store store = new StoreInitializer().initialize();
            String name = args.length > 0 ? args[0] : "store";

            LocateRegistry.createRegistry(1099); // 1099 is default RMI port
            LocateRegistry.getRegistry().bind(name, new RemoteStoreImpl(store));

            System.out.println("Store: " + name + " has been initialized.");
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }
}
