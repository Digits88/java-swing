package chapter6;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class TableCellRenderFrame extends JFrame {

    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFUALT_HEIGHT = 400;
    
    public TableCellRenderFrame() {
        setTitle("TableCellRenderFrame");
        setSize(DEFAULT_WIDTH, DEFUALT_HEIGHT);
        
        TableModel model = new PlanetTableModel();
        JTable table = new JTable(model);
        table.setRowSelectionAllowed(false);
        
        table.setDefaultRenderer(Color.class, new ColorTableCellRenderer());
        table.setDefaultEditor(Color.class, new ColorTableCellEditor());
        
        JComboBox<Integer> moonCombo = new JComboBox<>();
        for (int i = 0; i <= 20; i++) {
            moonCombo.addItem(i);
        }
        
        TableColumnModel columnModel = table.getColumnModel();
        TableColumn moonColumn = columnModel.getColumn(PlanetTableModel.MOONS_COLUMN);
        moonColumn.setCellEditor(new DefaultCellEditor(moonCombo));
        moonColumn.setHeaderRenderer(table.getDefaultRenderer(ImageIcon.class));
        moonColumn.setHeaderValue(new ImageIcon(getClass().getResource("moon.png")));
        
        table.setRowHeight(100);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new TableCellRenderFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}