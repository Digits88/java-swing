package chapter9;

import java.awt.*;
import javax.swing.*;

public class CircleLayoutFrame extends JFrame {
    public CircleLayoutFrame() {
        setTitle("CircleLayoutFrame");
        setLayout(new CircleLayout());
        add(new JButton("Yellow"));
        add(new JButton("Blue"));
        add(new JButton("Red"));
        add(new JButton("Green"));
        add(new JButton("Orange"));
        add(new JButton("Fuchsia"));
        add(new JButton("Indigo"));
        pack();
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new CircleLayoutFrame();
                frame.setVisible(true);
            }
        });
    }
}