package chapter6;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class ColorTableCellRenderer extends JPanel implements TableCellRenderer {

    public Component getTableCellRendererComponent(JTable table, Object value, 
        boolean isSelected, boolean hasFocus, int row, int column) {
        setBackground((Color) value);
        if (hasFocus) {
            setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
        } else {
            setBorder(null);
        }
        return this;
    }
}