package chapter9;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RadioButtonFrame extends JFrame {
    private JPanel buttonPanel;
    private ButtonGroup group;
    private JLabel label;
    private static final int DEFAULT_SIZE = 36;
    
    public RadioButtonFrame() {
        setTitle("RadioButtonFrame");
        
        label = new JLabel("The quick brown fox jumps over the lazy dog.");
        label.setFont(new Font("Serif", Font.PLAIN, DEFAULT_SIZE));
        add(label, BorderLayout.CENTER);
        
        buttonPanel = new JPanel();
        group = new ButtonGroup();
        
        addRadioButton("Small", 8);
        addRadioButton("Medium", 12);
        addRadioButton("Large", 18);
        addRadioButton("Extra Large", 36);
        
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
    }
    
    public void addRadioButton(String name, final int size) {
        boolean selected = size == DEFAULT_SIZE;
        JRadioButton button = new JRadioButton(name, selected);
        group.add(button);
        buttonPanel.add(button);
        
        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                label.setFont(new Font("Serif", Font.PLAIN, size));
            }
        };
        
        button.addActionListener(listener);
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            public void run() {
                JFrame frame = new RadioButtonFrame();
                frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}