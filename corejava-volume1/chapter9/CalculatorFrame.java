package chapter9;

import javax.swing.*;

public class CalculatorFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 250;
    private static final int DEFAULT_HEIGHT = 200;
    
    public CalculatorFrame() {
        setTitle("Calculator");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        
        add(new CalculatorPanel());
        //pack();
    }
}