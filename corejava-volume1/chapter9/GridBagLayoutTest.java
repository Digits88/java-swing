package chapter9;

import java.awt.*;
import javax.swing.*;

public class GridBagLayoutTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new FontFrame();
                frame.setTitle(frame.getTitle() + " - GridBagLayoutTest");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}