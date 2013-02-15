package post.client.view.gui;

import java.lang.Runnable;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import post.client.controller.POST;
import post.client.view.CashierView;

/**
 *
 * @author woeltjen
 */
public class CashierGUI implements CashierView {

    @Override
    public void connectTo(final POST post) {
        // NOTE: Builds GUI on the EDT  
        SwingUtilities.invokeLater(
            new Runnable() {
                @Override
                public void run() {
                    JFrame frame = new CashierFrame(new GUIMediator(post));                    
                    frame.pack();
                    frame.setVisible(true);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setResizable(false);
                }
            }
        );
    }
}
