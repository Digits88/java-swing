package chapter6;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class ColorTableCellEditor extends AbstractCellEditor implements TableCellEditor {

    private JColorChooser colorChooser;
    private JDialog colorDialog;
    private JPanel panel;
    
    public ColorTableCellEditor() {
        panel = new JPanel();
        colorChooser = new JColorChooser();
        colorDialog = JColorChooser.createDialog(null, "Planet Color", false, colorChooser,
            EventHandler.create(ActionListener.class, this, "stopCellEditing"),
            EventHandler.create(ActionListener.class, this, "cancelCellEditing"));
    }
    
    public Component getTableCellEditorComponent(JTable table, Object value, 
        boolean isSelected, int row, int column) {
        colorChooser.setColor((Color) value);
        return panel;
    }
    
    public boolean shouldSelectCell(EventObject anEvent) {
        colorDialog.setVisible(true);
        return true;
    }
    
    public void cancelCellEditing() {
        colorDialog.setVisible(false);
        super.cancelCellEditing();
    }
    
    public boolean stopCellEditing() {
        colorDialog.setVisible(false);
        super.stopCellEditing();
        return true;
    }
    
    public Object getCellEditorValue() {
        return colorChooser.getColor();
    }
}