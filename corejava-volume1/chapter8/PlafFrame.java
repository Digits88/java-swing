package chapter8;

import java.awt.event.*;
import javax.swing.*;

// Java Swing Look and Feels
public class PlafFrame extends JFrame {
    private JPanel buttonPanel;
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    public PlafFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT); //comment out pack() to effect the set size
        
        buttonPanel = new JPanel();
        
        UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
        for (UIManager.LookAndFeelInfo info : infos) {
            makeButton(info.getName(), info.getClassName());
        }
        add(buttonPanel);
        pack();
    }
    
    void makeButton(String name, final String plafName) {
        JButton button = new JButton(name);
        buttonPanel.add(button);
        
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try {
                    UIManager.setLookAndFeel(plafName);
                    SwingUtilities.updateComponentTreeUI(PlafFrame.this);
                    pack();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}