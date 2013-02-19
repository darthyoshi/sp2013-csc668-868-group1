package post;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import post.client.controller.POST;
import post.client.controller.POSTInitializer;
import post.client.view.CashierView;
import post.client.view.FileBasedCashier;
import post.remote.RemoteStore;
import post.remote.RemoteStoreWrapper;
import post.server.controller.Store;
import post.server.controller.StoreInitializer;

/**
 * Main point of entry for POST1. Creates a Store and a POST with default
 * values, then connects a file-based cashier to the POST.
 * @author woeltjen
 */
public class POST2Main {
    public static void main (String[] args) {
        Store store = new StoreInitializer().initialize();
        
        
        try {
            //new Server().registerStore(store, "store");
            
            Registry r = LocateRegistry.getRegistry("localhost");

            Store rs = new RemoteStoreWrapper((RemoteStore) r.lookup("store"));
            POST  post  = new POSTInitializer(rs).initialize();
            CashierView view = new FileBasedCashier();
            view.connectTo(post);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
