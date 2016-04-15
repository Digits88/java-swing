package chapter6;

import javax.swing.table.*;

public class InvestmentTableModel extends AbstractTableModel {

    private static double INITIAL_BALANCE = 100000.0;
    
    private int years;
    private int minRate;
    private int maxRate;
    
    public InvestmentTableModel(int y, int r1, int r2) {
        years = y;
        minRate = r1;
        maxRate = r2;
    }
    
    public int getRowCount() {
        return years;
    }
    
    public int getColumnCount() {
        return maxRate - minRate + 1;
    }
    
    public Object getValueAt(int r, int c) {
        /*
        try {
            rowSet.absolute(r + 1);
            return rowSet.getObject(c + 1);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        */
        double rate = (c + minRate) / 100.0;
        int nperiods = r;
        double futureBalance = INITIAL_BALANCE * Math.pow(1 + rate, nperiods);
        return String.format("%.2f", futureBalance);
    }
    
    public String getColumnName(int c) {
        return (c + minRate) + "%";
    }
}