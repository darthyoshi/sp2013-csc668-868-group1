package post;

import post.client.controller.POST;
import post.client.controller.POSTInitializer;
import post.client.view.CashierView;
import post.client.view.gui.CashierGUI;
import post.server.controller.Store;
import post.server.controller.StoreInitializer;

/**
 * Main point of entry for POST1. Creates a Store and a POST with default
 * values, then connects a file-based cashier to the POST.
 * @author woeltjen
 */
public class GUIMain {
    public static void main (String[] args) {
        Store store = new StoreInitializer().initialize();
        POST  post  = new POSTInitializer(store).initialize();
        
        try {
            CashierView view = new CashierGUI();
            view.connectTo(post);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
