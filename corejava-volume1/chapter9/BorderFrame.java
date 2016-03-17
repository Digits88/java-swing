package chapter9;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class BorderFrame extends JFrame {
    private JPanel demoPanel;
    private JPanel buttonPanel;
    private ButtonGroup group;
    
    public BorderFrame() {
        setTitle("BorderFrame");
        
        demoPanel = new JPanel();
        buttonPanel = new JPanel();
        group = new ButtonGroup();
        
        addRadioButton("Lowered bevel", BorderFactory.createLoweredBevelBorder());
        addRadioButton("Raised bevel", BorderFactory.createRaisedBevelBorder());
        addRadioButton("Etched", BorderFactory.createEtchedBorder());
        addRadioButton("Line", BorderFactory.createLineBorder(Color.BLUE));
        addRadioButton("Matte", BorderFactory.createMatteBorder(10, 10, 10, 10, Color.RED));
        addRadioButton("Empty", BorderFactory.createEmptyBorder());
        
        addRadioButton("Rounded", new LineBorder(Color.BLUE, 5, true));
        addRadioButton("SoftBevel", new SoftBevelBorder(SoftBevelBorder.RAISED, Color.GREEN, Color.PINK));
        
        Border etched = BorderFactory.createEtchedBorder();
        Border titled = BorderFactory.createTitledBorder(etched, "Border types");
        
        buttonPanel.setBorder(titled);
        
        setLayout(new GridLayout(2, 1));
        add(buttonPanel);
        add(demoPanel);
        pack();
    }
    
    public void addRadioButton(String buttonName, final Border b) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event) {
                demoPanel.setBorder(b);
            }
        });
        group.add(button);
        buttonPanel.add(button);
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            public void run() {
                JFrame frame = new BorderFrame();
                frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
