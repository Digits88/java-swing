package chapter6;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ListRenderingFrame extends JFrame {

    private final static int DEFUALT_WIDTH = 400;
    private final static int DEFUALT_HEIGHT = 300;
    
    private JTextArea text;
    private JList<Font> fontList;
    
    public ListRenderingFrame() {
        setTitle("ListRenderingFrame");
        setSize(DEFUALT_WIDTH, DEFUALT_HEIGHT);
        
        final int SIZE = 24;
        
        DefaultListModel<Font> fonts = new DefaultListModel<Font>();
        fonts.addElement(new Font("Serif", Font.PLAIN, SIZE));
        fonts.addElement(new Font("SansSerif", Font.PLAIN, SIZE));
        fonts.addElement(new Font("Monospaced", Font.PLAIN, SIZE));
        fonts.addElement(new Font("Dialog", Font.PLAIN, SIZE));
        fonts.addElement(new Font("DialogInput", Font.PLAIN, SIZE));
        
        fontList = new JList<Font>(fonts);
        fontList.setVisibleRowCount(4);
        fontList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        fontList.setCellRenderer(new FontCellRenderer());
        JScrollPane scrollPane = new JScrollPane(fontList);
        
        JPanel p = new JPanel();
        p.add(scrollPane);
        fontList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                Font font = (Font) fontList.getSelectedValue();
                text.setFont(font);
            }
        });
        
        Container contentPane = getContentPane();
        contentPane.add(p, BorderLayout.SOUTH);
        text = new JTextArea("The quick brown fox jumps over the lazy dog");
        text.setFont((Font) fonts.get(0));
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        contentPane.add(text, BorderLayout.CENTER);
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new ListRenderingFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}