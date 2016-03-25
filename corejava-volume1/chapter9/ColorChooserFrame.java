package chapter9;

import java.awt.*;
import javax.swing.*;

public class ColorChooserFrame extends JFrame {
    
    public static final int DEFAULT_WIDTH = 500;
    public static final int DEFAULT_HEIGHT = 300;
    
    public ColorChooserFrame() {
        setTitle("ColorChooserFrame");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        ColorChooserPanel panel = new ColorChooserPanel();
        add(panel);
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new ColorChooserFrame();
                frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}