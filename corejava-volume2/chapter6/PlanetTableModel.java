package chapter6;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class PlanetTableModel extends AbstractTableModel {

    public static final int PLANET_COLUMN = 0;
    public static final int MOONS_COLUMN = 2;
    public static final int GASEOUS_COLUMN = 3;
    public static final int COLOR_COLUMN = 4;
    
    private String[] columnNames = {"Planet", "Radius", "Moons", "Gaseous", "Color", "Image"};
    
    //the planet images credit to http://space-facts.com/
    private Object[][] cells = {
        {"Mercury", 2440.0, 0, false, Color.YELLOW, new ImageIcon(getClass().getResource("mercury.png"))},
        {"Venus", 6052.0, 0, false, Color.YELLOW, new ImageIcon(getClass().getResource("venus.png"))},
        {"Earth", 6378.0, 1, false, Color.BLUE, new ImageIcon(getClass().getResource("earth.png"))},
        {"Mars", 3397.0, 2, false, Color.RED, new ImageIcon(getClass().getResource("mars.png"))},
        {"Jupiter", 71492.0, 16, true, Color.ORANGE, new ImageIcon(getClass().getResource("jupiter.png"))},
        {"Saturn", 60268.0, 18, true, Color.ORANGE, new ImageIcon(getClass().getResource("saturn.png"))},
        {"Uranus", 25559.0, 17, true, Color.BLUE, new ImageIcon(getClass().getResource("uranus.png"))},
        {"Neptune", 24766.0, 8, true, Color.BLUE, new ImageIcon(getClass().getResource("neptune.png"))},
        {"Pluto", 1137.0, 1, false, Color.BLACK, new ImageIcon(getClass().getResource("pluto.png"))}
    };
    
    public String getColumnName(int c) {
        return columnNames[c];
    }
    
    public Class<?> getColumnClass(int c) {
        return cells[0][c].getClass();
    }
    
    public int getColumnCount() {
        return cells[0].length;
    }
    
    public int getRowCount() {
        return cells.length;
    }
    
    public Object getValueAt(int r, int c) {
        return cells[r][c];
    }
    
    public void setValueAt(Object obj, int r, int c) {
        cells[r][c] = obj;
    }
    
    public boolean isCellEditable(int r, int c) {
        return c == PLANET_COLUMN || c == MOONS_COLUMN || c == GASEOUS_COLUMN || c == COLOR_COLUMN;
    }
}