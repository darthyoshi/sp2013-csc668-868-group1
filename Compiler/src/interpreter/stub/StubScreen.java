package interpreter.stub;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author woeltjen
 */
public class StubScreen extends JFrame {
    private BufferedImage[] images = { 
        new BufferedImage(100, 100, BufferedImage.TYPE_3BYTE_BGR), 
        new BufferedImage(100, 100, BufferedImage.TYPE_3BYTE_BGR)} ;
    private int offscreen = 0;
    private Object swapMutex = new Object();
    
    private JPanel panel = new JPanel() {
        @Override
        public void paintComponent(Graphics g) {
            synchronized(swapMutex) {
                g.drawImage(images[offscreen^1], 0,0, panel.getWidth(), panel.getHeight(), panel);
            }
        }
    };
    
    private Set<Integer> keys = new HashSet<Integer>();
    
    private KeyListener keyListener = new KeyListener() {
        public void keyPressed(KeyEvent ke) {
            keys.add(Integer.valueOf(ke.getKeyCode()));
        }

        public void keyReleased(KeyEvent ke) {
            keys.remove(Integer.valueOf(ke.getKeyCode()));
        }

        public void keyTyped(KeyEvent ke) {
        }        
    };
    
    
    public StubScreen() {
        clear();
        swap();
        addKeyListener(keyListener);
        getContentPane().add(panel);
    }
   
    public void clear() {
        Graphics g = images[offscreen].getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, images[offscreen].getWidth(), images[offscreen].getHeight());
    }
    
    public void draw(int x, int y, int w, int h) {
        Graphics g = images[offscreen].getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(x, y, w, h);        
    }
    
    public void swap() {
        synchronized (swapMutex) {
            offscreen = offscreen ^ 1;
        }
        clear();
        panel.repaint();
    }
    
    public boolean pressed (int keyCode) {
        return keys.contains(Integer.valueOf(keyCode));                
    }    
}
