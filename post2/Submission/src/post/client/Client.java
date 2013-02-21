package post.client;

import java.rmi.registry.*;
import post.client.controller.*;
import post.client.view.*;
import post.client.view.gui.CashierGUI;
import post.remote.RemoteStore;
import post.store.Store;

/**
 * Class Client is an RMI client that connects to the POST server. The class is
 * the client-side interface between the front-end customer interaction and the
 * back-end server operations.
 * @author Kay Choi
 */
public class Client {

    /**
     * Main method. Attempts to initiate a connection to the server, and starts
     * a GUI if successful.
     * @param args the set of command line arguments
     */
    public static void main(String args[]) {

        try {
            Registry r = LocateRegistry.getRegistry("localhost");
            String name = args.length > 0 ? args[0] : "store";
            Store rs = new StoreProxy((RemoteStore) r.lookup(name));
            POST  post  = new POSTInitializer(rs).initialize();
            CashierView view = new CashierGUI();

            view.connectTo(post);

            System.out.println("The client has connected to store: " + name);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
