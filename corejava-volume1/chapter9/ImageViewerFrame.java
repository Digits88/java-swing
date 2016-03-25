package chapter9;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.*;

public class ImageViewerFrame extends JFrame {

    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 400;
    private JLabel label;
    private JFileChooser chooser;
    private JScrollPane scrollPane;
    
    public ImageViewerFrame() {
        setTitle("ImageViewerFrame");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu menu = new JMenu("File");
        menuBar.add(menu);
        
        JMenuItem openItem = new JMenuItem("Open");
        menu.add(openItem);
        openItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                chooser.setCurrentDirectory(new File("."));
                int result = chooser.showOpenDialog(ImageViewerFrame.this);
                
                if (result == JFileChooser.APPROVE_OPTION) {
                    String name = chooser.getSelectedFile().getPath();
                    //label.setText(name);
                    label.setIcon(new ImageIcon(name));
                    pack();
                }
            }
        });
        
        JMenuItem exitItem = new JMenuItem("Exit");
        menu.add(exitItem);
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        
        label = new JLabel();
        //add(label);
        scrollPane = new JScrollPane(label);
        scrollPane.setPreferredSize(new Dimension(800, 600));
        add(scrollPane);
        
        chooser = new JFileChooser();
        
        // accept all image files ending with .jpg, .jpeg, .gif
        /*
        final ExtensionFileFilter filter = new ExtensionFileFilter();
        filter.addExtension("jpg");
        filter.addExtension("jpeg");
        filter.addExtension("gif");
        filter.setDescription("Image files");
        */
       
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "jpg", "jpeg", "gif", "png");
        chooser.setFileFilter(filter);
        chooser.setAccessory(new ImagePreviewer(chooser));
        chooser.setFileView(new FileIconView(filter, new ImageIcon("palette.gif")));
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new ImageViewerFrame();
                frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}