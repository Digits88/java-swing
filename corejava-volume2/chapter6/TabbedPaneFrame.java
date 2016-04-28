package chapter6;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class TabbedPaneFrame extends JFrame {
    
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 300;
    
    private JTabbedPane tabbedPane;

    public TabbedPaneFrame() {
        setTitle("TabbedPaneFrame");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        
        ImageIcon icon = new ImageIcon(getClass().getResource("yellow-ball.png"));
        
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Mercury", icon, null);
        tabbedPane.addTab("Venus", icon, null);
        tabbedPane.addTab("Earth", icon, null);
        tabbedPane.addTab("Mars", icon, null);
        tabbedPane.addTab("Jupiter", icon, null);
        tabbedPane.addTab("Saturn", icon, null);
        tabbedPane.addTab("Uranus", icon, null);
        tabbedPane.addTab("Neptune", icon, null);
        tabbedPane.addTab("Pluto", null, null);
        
        final int plutoIndex = tabbedPane.indexOfTab("Pluto");
        JPanel plutoPanel = new JPanel();
        plutoPanel.add(new JLabel("Pluto", icon, SwingConstants.LEADING));
        JToggleButton plutoCheckBox = new JCheckBox();
        plutoCheckBox.addActionListener((ActionEvent event) -> {
            tabbedPane.remove(plutoIndex);
        });
        plutoPanel.add(plutoCheckBox);
        tabbedPane.setTabComponentAt(plutoIndex, plutoPanel);
        
        add(tabbedPane, "Center");
        
        tabbedPane.addChangeListener((ChangeEvent event) -> {
            if (tabbedPane.getSelectedComponent() == null) {
                int n = tabbedPane.getSelectedIndex();
                loadTab(n);
            }
        });
        
        loadTab(0);
        
        JPanel buttonPanel = new JPanel();
        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton wrapButton = new JRadioButton("Wrap tabs");
        wrapButton.addActionListener((ActionEvent event) -> {
            tabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
        });
        buttonPanel.add(wrapButton);
        buttonGroup.add(wrapButton);
        wrapButton.setSelected(true);
        JRadioButton scrollButton = new JRadioButton("Scroll tabs");
        scrollButton.addActionListener((ActionEvent event) -> {
            tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        });
        buttonPanel.add(scrollButton);
        buttonGroup.add(scrollButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void loadTab(int n) {
        String title = tabbedPane.getTitleAt(n);
        ImageIcon planetIcon = new ImageIcon(getClass().getResource(title + ".png"));
        tabbedPane.setComponentAt(n, new JLabel(planetIcon));
        tabbedPane.setIconAt(n, new ImageIcon(getClass().getResource("red-ball.png")));
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> {
            JFrame frame = new TabbedPaneFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}