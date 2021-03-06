package chapter6;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class LongListFrame extends JFrame {

    private JList<String> wordList;
    private JLabel label;
    private String prefix = "The quick brown ";
    private String suffix = " jumps over the lazy dog.";
    
    public LongListFrame() {
        setTitle("LongListFrame");
        wordList = new JList<String>(new WordListModel(3));
        wordList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        wordList.setPrototypeCellValue("www");
        JScrollPane scrollPane = new JScrollPane(wordList);
        
        JPanel p = new JPanel();
        p.add(scrollPane);
        wordList.addListSelectionListener(
            new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent event) {
                    setSubject(wordList.getSelectedValue());
                }
            }
        );
        
        Container contentPane = getContentPane();
        contentPane.add(p, BorderLayout.NORTH);
        label = new JLabel(prefix + suffix);
        contentPane.add(label, BorderLayout.CENTER);
        setSubject("fox");
        pack();
    }
    
    public void setSubject(String word) {
        StringBuilder text = new StringBuilder(prefix);
        text.append(word);
        text.append(suffix);
        label.setText(text.toString());
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new LongListFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}