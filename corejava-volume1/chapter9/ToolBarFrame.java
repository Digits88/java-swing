package chapter9;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ToolBarFrame extends JFrame {
    
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;
    
    private JPanel panel;
    
    public ToolBarFrame() {
        setTitle("ToolBarFrame");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        
        panel = new JPanel();
        add(panel, BorderLayout.CENTER);
        
        Action blueAction = new ColorAction("Blue", new ImageIcon(getClass().getResource("blue-ball.png")), Color.BLUE);
        Action yellowAction = new ColorAction("Yellow", new ImageIcon(getClass().getResource("yellow-ball.png")), Color.YELLOW);
        Action redAction = new ColorAction("Red", new ImageIcon(getClass().getResource("red-ball.png")), Color.RED);
        
        Action exitAction = new AbstractAction("Exit", new ImageIcon(getClass().getResource("exit.png"))) {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        };
        exitAction.putValue(Action.SHORT_DESCRIPTION, "Exit");
        
        JToolBar bar = new JToolBar();
        bar.add(blueAction);
        bar.add(yellowAction);
        bar.add(redAction);
        bar.addSeparator();
        bar.add(exitAction);
        add(bar, BorderLayout.NORTH);
        
        JMenu menu = new JMenu("Color");
        menu.add(yellowAction);
        menu.add(blueAction);
        menu.add(redAction);
        menu.add(exitAction);
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }
    
    class ColorAction extends AbstractAction {
        public ColorAction(String name, Icon icon, Color c) {
            putValue(Action.NAME, name);
            putValue(Action.SMALL_ICON, icon);
            putValue(Action.SHORT_DESCRIPTION, name + " background");
            putValue("Color", c);
        }
        
        public void actionPerformed(ActionEvent event) {
            Color c = (Color) getValue("Color");
            panel.setBackground(c);
        }
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(
            new Runnable() {
                public void run() {
                    JFrame frame = new ToolBarFrame();
                    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    frame.setVisible(true);
                }
            }
        );
    }
}