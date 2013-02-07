/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package post;

import post.client.controller.POST;
import post.client.controller.POSTInitializer;
import post.client.view.CashierView;
import post.client.view.FileBasedCashier;
import post.server.controller.Store;
import post.server.controller.StoreInitializer;

/**
 *
 * @author woeltjen
 */
public class Main {
    public static void main (String[] args) {
        Store store = new StoreInitializer().initialize();
        POST  post  = new POSTInitializer(store).initialize();
        
        try {
            CashierView view = new FileBasedCashier();
            view.connectTo(post);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
