package chapter6;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import javax.swing.*;

public class PlanetTableFrame extends JFrame {

    private String[] columnNames = {"Planet", "Radius", "Moons", "Gaseous", "Color"};
    private Object[][] cells = {
        {"Mercury", 2440.0, 0, false, Color.YELLOW},
        {"Venus", 6052.0, 0, false, Color.YELLOW},
        {"Earth", 6378.0, 1, false, Color.BLUE},
        {"Mars", 3397.0, 2, false, Color.RED},
        {"Jupiter", 71492.0, 16, true, Color.ORANGE},
        {"Saturn", 60268.0, 18, true, Color.ORANGE},
        {"Uranus", 25559.0, 17, true, Color.BLUE},
        {"Neptune", 24766.0, 8, true, Color.BLUE},
        {"Pluto", 1137.0, 1, false, Color.BLACK}
    };
    
    public PlanetTableFrame() {
        setTitle("PlanetTableFrame");
        final JTable table = new JTable(cells, columnNames);
        table.setAutoCreateRowSorter(true);
        JScrollPane pane = new JScrollPane(table);
        add(pane, BorderLayout.CENTER);
        JButton printButton = new JButton("Print");
        printButton.addActionListener(EventHandler.create(ActionListener.class, table, "print"));
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(printButton);
        add(buttonPanel, BorderLayout.SOUTH);
        //pack();
        setSize(600, 250);
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new PlanetTableFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}