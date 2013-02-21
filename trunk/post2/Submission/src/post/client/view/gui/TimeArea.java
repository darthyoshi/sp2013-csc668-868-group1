package post.client.view.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Provides an area that displays the current system time.
 * @author woeltjen
 */
public class TimeArea extends JPanel {
    private static final DateFormat DATE_FORMATTER = 
            new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private JLabel timeLabel = new JLabel();
    
    public TimeArea() {
        setLayout(new BorderLayout());
        add(timeLabel, BorderLayout.SOUTH);
        timeUpdater.actionPerformed(null);
        new Timer(1000, timeUpdater).start(); // Update every second
        setBorder(BorderFactory.createEmptyBorder(32, 32, 32, 32));
    }    
    
    private final ActionListener timeUpdater = new ActionListener() {        
        @Override
        public void actionPerformed(ActionEvent ae) {            
            timeLabel.setText(DATE_FORMATTER.format(new Date()));
            repaint();
        }        
    };
}
