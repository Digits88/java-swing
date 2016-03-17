package chapter8;

import java.awt.*;
import javax.swing.*;

public class PlafTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(
            new Runnable() {
                public void run() {
                    JFrame frame = new PlafFrame();
                    frame.setTitle("PlafTest");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);
                }
            }
        );
    }
}