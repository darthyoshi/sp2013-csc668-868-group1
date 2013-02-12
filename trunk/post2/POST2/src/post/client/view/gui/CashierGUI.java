package post.client.view.gui;

import javax.swing.JFrame;
import post.client.controller.POST;
import post.client.view.CashierView;

/**
 *
 * @author woeltjen
 */
public class CashierGUI implements CashierView {

    @Override
    public void connectTo(POST post) {
        JFrame frame = new CashierFrame(new GUIMediator(post));
        frame.setVisible(true);
        frame.pack();        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
