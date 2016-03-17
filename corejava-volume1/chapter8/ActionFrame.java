package chapter8;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ActionFrame extends JFrame {
    private JPanel buttonPanel;
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 200;
    
    public ActionFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        
        buttonPanel = new JPanel();
        Color defaultColor = buttonPanel.getBackground();
        
        ImageIcon yicon = new ImageIcon(getClass().getResource("yellow-ball.png"));
        ImageIcon bicon = new ImageIcon(getClass().getResource("blue-ball.png"));
        ImageIcon ricon = new ImageIcon(getClass().getResource("red-ball.png"));
        
        // using javax.swing.Action and AbstractAction API, alternative to ActionListener pattern
        Action yellowAction = new ColorAction("Yellow", yicon, Color.YELLOW);
        Action blueAction = new ColorAction("Blue", bicon, Color.BLUE);
        Action redAction = new ColorAction("Red", ricon, Color.RED);
        Action defaultAction = new ColorAction("Default", null, defaultColor);
        
        buttonPanel.add(new JButton(yellowAction));
        buttonPanel.add(new JButton(blueAction));
        buttonPanel.add(new JButton(redAction));
        buttonPanel.add(new JButton(defaultAction));
        
        add(buttonPanel);
        
        // doing key stroke shortcut Ctrl+Y to set yellow, etc.
        InputMap imap = buttonPanel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        imap.put(KeyStroke.getKeyStroke("ctrl Y"), "panel.yellow");
        imap.put(KeyStroke.getKeyStroke("ctrl B"), "panel.blue");
        imap.put(KeyStroke.getKeyStroke("ctrl R"), "panel.red");
        imap.put(KeyStroke.getKeyStroke("ctrl D"), "panel.default");
        
        ActionMap amap = buttonPanel.getActionMap();
        amap.put("panel.yellow", yellowAction);
        amap.put("panel.blue", blueAction);
        amap.put("panel.red", redAction);
        amap.put("panel.default", defaultAction);
    }
    
    public class ColorAction extends AbstractAction {
        public ColorAction(String name, Icon icon, Color c) {
            putValue(Action.NAME, name);
            putValue(Action.SMALL_ICON, icon);
            putValue(Action.SHORT_DESCRIPTION, "Set panel color to " + name.toLowerCase());
            putValue("color", c); // key string 'color' can be arbitrary. later, it is used in tandem at actionPerformed below
        }
        
        public void actionPerformed(ActionEvent event) {
            Color c = (Color) getValue("color");
            buttonPanel.setBackground(c);
        }
    }
}