package chapter6;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import javax.swing.*;

public class DesktopFrame extends JFrame {

    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 400;
    private static final String[] planets = { 
        "Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn",
        "Uranus", "Neptune", "Pluto"
    };
    
    private JDesktopPane desktop;
    private int nextFrameX;
    private int nextFrameY;
    private int frameDistance;
    private int counter;
    
    public DesktopFrame() {
        setTitle("DesktopFrame");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        
        desktop = new JDesktopPane();
        add(desktop, BorderLayout.CENTER);
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        JMenuItem openItem = new JMenuItem("New");
        openItem.addActionListener((ActionEvent event) -> {
            createInternalFrame(new JLabel(new ImageIcon(getClass().getResource(planets[counter].toLowerCase() + ".png"))), planets[counter]);
            counter = (counter + 1) % planets.length;
        });
        fileMenu.add(openItem);
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener((ActionEvent event) -> {
            System.exit(0);
        });
        fileMenu.add(exitItem);
        JMenuItem windowMenu = new JMenu("Window");
        menuBar.add(windowMenu);
        JMenuItem nextItem = new JMenuItem("Next");
        nextItem.addActionListener((ActionEvent event) -> {
            selectNextWindow();
        });
        windowMenu.add(nextItem);
        JMenuItem cascadeItem = new JMenuItem("Cascade");
        cascadeItem.addActionListener((ActionEvent event) -> {
            cascadeWindows();
        });
        windowMenu.add(cascadeItem);
        JMenuItem tileItem = new JMenuItem("Tile");
        tileItem.addActionListener((ActionEvent event) -> {
            tileWindows();
        });
        windowMenu.add(tileItem);
        final JCheckBoxMenuItem dragOutlineItem = new JCheckBoxMenuItem("Drag Outline");
        dragOutlineItem.addActionListener((ActionEvent event) -> {
            desktop.setDragMode(dragOutlineItem.isSelected() ? JDesktopPane.OUTLINE_DRAG_MODE : JDesktopPane.LIVE_DRAG_MODE);
        });
        windowMenu.add(dragOutlineItem);
    }
    
    public void createInternalFrame(Component c, String t) {
        final JInternalFrame iframe = new JInternalFrame(t, true, true, true, true);
        iframe.add(c, BorderLayout.CENTER);
        desktop.add(iframe);
        
        iframe.setFrameIcon(new ImageIcon(getClass().getResource("yellow-ball.png")));
        
        iframe.addVetoableChangeListener(new VetoableChangeListener() {
            public void vetoableChange(PropertyChangeEvent event) throws PropertyVetoException {
                String name = event.getPropertyName();
                Object value = event.getNewValue();
                if (name.equals("closed") && value.equals(true)) {
                    int result = JOptionPane.showInternalConfirmDialog(iframe, "OK to close?", "Select an Option", JOptionPane.YES_NO_OPTION);
                    if (result != JOptionPane.YES_OPTION) {
                        throw new PropertyVetoException("User canceld close", event);
                    }
                }
            }
        });
        
        int width = desktop.getWidth() / 2;
        int height = desktop.getHeight() / 2;
        iframe.reshape(nextFrameX, nextFrameY, width, height);
        iframe.show();
        
        try {
            iframe.setSelected(true);
        } catch (PropertyVetoException ex) {
            //ex.printStackTrace();
            System.out.println("PropertyVetoException At Line 98");
        }
        
        frameDistance = iframe.getHeight() - iframe.getContentPane().getHeight();
        
        nextFrameX += frameDistance;
        nextFrameY += frameDistance;
        if (nextFrameX + width > desktop.getWidth()) nextFrameX = 0;
        if (nextFrameY + height > desktop.getHeight()) nextFrameY = 0;
    }
    
    public void cascadeWindows() {
        int x = 0;
        int y = 0;
        int width = desktop.getWidth() / 2;
        int height = desktop.getHeight() / 2;
        
        for (JInternalFrame frame : desktop.getAllFrames()) {
            if (!frame.isIcon()) {
                try {
                    frame.setMaximum(false);
                    frame.reshape(x, y, width, height);
                    x += frameDistance;
                    y += frameDistance;
                    if (x + width > desktop.getWidth()) x = 0;
                    if (y + height > desktop.getHeight()) y = 0;
                } catch (PropertyVetoException ex) {
                    System.out.println("PropertyVetoException At Line 121");
                }
            }
        }
    }
    
    public void tileWindows() {
        int frameCount = 0;
        for (JInternalFrame frame : desktop.getAllFrames()) {
            if (!frame.isIcon()) frameCount++;
        }
        if (frameCount == 0) return;
        int rows = (int) Math.sqrt(frameCount);
        int cols = frameCount / rows;
        int extra = frameCount % rows;
        int width = desktop.getWidth() / cols;
        int height = desktop.getHeight() / rows;
        int r = 0;
        int c = 0;
        for (JInternalFrame frame : desktop.getAllFrames()) {
            if (!frame.isIcon()) {
                try {
                    frame.setMaximum(false);
                    frame.reshape(c * width, r * height, width, height);
                    r++;
                    if (r == rows) {
                        r = 0;
                        c++;
                        if (c == cols - extra) {
                            rows++;
                            height = desktop.getHeight() / rows;
                        }
                    }
                } catch (PropertyVetoException ex) {
                    System.out.println("PropertyVetoException At Line 159");
                }
            }
        }
    }
    
    public void selectNextWindow() {
        JInternalFrame[] frames = desktop.getAllFrames();
        for (int i = 0; i < frames.length; i++) {
            int next = (i + 1) % frames.length;
            while (next != i) {
                if (!frames[next].isIcon()) {
                    try {
                        frames[next].setSelected(true);
                        frames[next].toFront();
                        frames[i].toBack();
                        return;
                    } catch (PropertyVetoException ex) {
                        System.out.println("PropertyVetoException At Line 177");
                    }
                }
                next = (next + 1) % frames.length;
            }
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> {
            JFrame frame = new DesktopFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}