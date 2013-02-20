/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package post.client;

import java.rmi.registry.*;
import post.client.controller.*;
import post.client.view.*;
import post.client.view.gui.CashierGUI;
import post.remote.RemoteStore;
import post.server.controller.Store;

/**
 *
 * @author Kay Choi
 */
public class Client {

    /**
     * Main method. Attempts to initiate a connection to a POST server, and
     * starts a GUI if successful.
     * @param args the set of command line arguments
     */
    public static void main(String args[]) {
        if(System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {
            Registry r = LocateRegistry.getRegistry("localhost");
            String name = args.length > 0 ? args[0] : "store";
            Store rs = new StoreProxy((RemoteStore) r.lookup(name));
            POST  post  = new POSTInitializer(rs).initialize();
            CashierView view = new CashierGUI();

            view.connectTo(post);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
