/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package post.server;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import post.remote.RemoteStoreImpl;
import post.server.controller.*;

/**
 *
 * @author Kay Choi
 */
public class Server implements Remote {

    /**
     * Main method. Initializes an RMI server for POST operations.
     * @param args the set of command line arguments
     */
    public static void main (String args[]) {
        try {
            Store store = new StoreInitializer().initialize();
            String name = args.length > 0 ? args[0] : "store";

            LocateRegistry.createRegistry(1099); // 1099 is default RMI port
            LocateRegistry.getRegistry().bind(name, new RemoteStoreImpl(store));
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
        }

    }
}
