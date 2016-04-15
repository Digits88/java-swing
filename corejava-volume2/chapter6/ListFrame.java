package chapter6;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ListFrame extends JFrame {

    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 300;
    
    private JPanel listPanel;
    private JList<String> wordList;
    private JLabel label;
    private JPanel buttonPanel;
    private ButtonGroup group;
    private String prefix = "The ";
    private String suffix = "fox jumps over the lazy dog.";
    
    public ListFrame() {
        setTitle("ListFrame");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        
        String[] words = {"quick", "brown", "hungry", "wild", "silent", "huge", "private", "abstract", "static", "final"};
        
        wordList = new JList<>(words);
        wordList.setVisibleRowCount(4);
        JScrollPane scrollPane = new JScrollPane(wordList);
        
        listPanel = new JPanel();
        listPanel.add(scrollPane);
        wordList.addListSelectionListener(
            new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent event) {
                    StringBuilder text = new StringBuilder(prefix);
                    for (String value : wordList.getSelectedValuesList()) {
                        text.append(value);
                        text.append(" ");
                    }
                    text.append(suffix);
                    label.setText(text.toString());
                }
            }
        );
        
        buttonPanel = new JPanel();
        group = new ButtonGroup();
        makeButton("Vertical", JList.VERTICAL);
        makeButton("Vertical Wrap", JList.VERTICAL_WRAP);
        makeButton("Horizontal Wrap", JList.HORIZONTAL_WRAP);
        
        add(listPanel, BorderLayout.NORTH);
        label = new JLabel(prefix + suffix);
        add(label, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void makeButton(String label, final int orientation) {
        JRadioButton button = new JRadioButton(label);
        buttonPanel.add(button);
        if (group.getButtonCount() == 0) {
            button.setSelected(true);
        }
        group.add(button);
        button.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    wordList.setLayoutOrientation(orientation);
                    listPanel.revalidate();
                }
            }
        );
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new ListFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}