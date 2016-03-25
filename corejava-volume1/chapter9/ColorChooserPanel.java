package chapter9;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ColorChooserPanel extends JPanel {

    public ColorChooserPanel() {
        JButton modalButton = new JButton("Modal");
        modalButton.addActionListener(new ModalListener());
        add(modalButton);
        
        JButton modalessButton = new JButton("Modaless");
        modalessButton.addActionListener(new ModalessListener());
        add(modalessButton);
        
        JButton immediateButton = new JButton("Immediate");
        immediateButton.addActionListener(new ImmediateListener());
        add(immediateButton);
    }
    
    private class ModalListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            Color defaultColor = getBackground();
            Color selected = JColorChooser.showDialog(ColorChooserPanel.this, "Set Background - Modal", defaultColor);
            if (selected != null) setBackground(selected);
        }
    }
    
    private class ModalessListener implements ActionListener {
        private JDialog dialog;
        private JColorChooser chooser;
        
        public ModalessListener() {
            chooser = new JColorChooser();
            dialog = JColorChooser.createDialog(ColorChooserPanel.this, "Set Background - Modaless", 
                false, chooser, new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        setBackground(chooser.getColor());
                    }
                }, null);
        }
        
        public void actionPerformed(ActionEvent event) {
            chooser.setColor(getBackground());
            dialog.setVisible(true);
        }
    }
    
    private class ImmediateListener implements ActionListener {
        private JDialog dialog;
        private JColorChooser chooser;
        
        public ImmediateListener() {
            chooser = new JColorChooser();
            chooser.getSelectionModel().addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent event) {
                    setBackground(chooser.getColor());
                }
            });
            
            dialog = new JDialog((Frame) null, false);
            dialog.add(chooser);
            dialog.setTitle("Set Background - Immediate");
            dialog.pack();
        }
        
        public void actionPerformed(ActionEvent event) {
            chooser.setColor(getBackground());
            dialog.setVisible(true);
        }
    }
}