package chapter6;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class InvestmentTableFrame extends JFrame {

    public InvestmentTableFrame() {
        setTitle("InvestmentTableFrame");
        TableModel model = new InvestmentTableModel(30, 5, 10);
        JTable table  = new JTable(model);
        add(new JScrollPane(table));
        pack();
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new InvestmentTableFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}