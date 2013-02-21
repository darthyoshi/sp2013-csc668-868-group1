package post;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import post.client.controller.POST;
import post.client.controller.POSTInitializer;
import post.client.controller.StoreProxy;
import post.client.view.CashierView;
import post.client.view.gui.CashierGUI;
import post.remote.RemoteStore;
import post.remote.RemoteStoreImpl;
import post.store.Store;
import post.server.controller.StoreInitializer;

/**
 * Main point of entry for POST1. Creates a Store and a POST with default
 * values, then connects a file-based cashier to the POST.
 * @author woeltjen
 */
public class POST2Main {
    public static void main (String[] args) {
        try {   
            // Server stuff
            Store store = new StoreInitializer().initialize();
            LocateRegistry.createRegistry(1099); // 1099 is default RMI port
            LocateRegistry.getRegistry().bind("store", new RemoteStoreImpl(store));

            // Client stuff.
            // NOTE: Should use StoreProxy instead of RemoteStoreWrapper,
            //       and CashierGUI instead of FileBasedCashier
            Registry r = LocateRegistry.getRegistry("localhost");
            Store rs = new StoreProxy((RemoteStore) r.lookup("store"));
            POST  post  = new POSTInitializer(rs).initialize();
            CashierView view = new CashierGUI();
            view.connectTo(post);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
