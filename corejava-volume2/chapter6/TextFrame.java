package chapter6;

import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import javax.swing.*;

public class TextFrame extends JFrame {

    public static final int TEXT_ROWS = 10;
    public static final int TEXT_COLUMNS = 40;
    
    private JMenuItem openItem;
    private JMenuItem exitItem;
    private JTextArea textArea;
    private JFileChooser chooser;
    
    public TextFrame() {
        setTitle("TextFrame");
        
        textArea = new JTextArea(TEXT_ROWS, TEXT_COLUMNS);
        add(new JScrollPane(textArea));
        
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        openItem = new JMenuItem("Open");
        openItem.addActionListener((ActionEvent event)-> {
            try {
                openFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        
        fileMenu.add(openItem);
        exitItem = new JMenuItem("Exit");
        exitItem.addActionListener((ActionEvent event)-> {
            System.exit(0);
        });
        
        fileMenu.add(exitItem);
        pack();
    }
    
    public void openFile() throws IOException {
        int r = chooser.showOpenDialog(this);
        if (r != JFileChooser.APPROVE_OPTION) return;
        final File f = chooser.getSelectedFile();
        
        InputStream fileIn = Files.newInputStream(f.toPath());
        final ProgressMonitorInputStream progressIn = new ProgressMonitorInputStream(this, "Reading " + f.getName(), fileIn);
        textArea.setText("");
        
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            protected Void doInBackground() throws Exception {
                try (Scanner in = new Scanner(progressIn)) {
                    while (in.hasNextLine()) {
                        String line = in.nextLine();
                        textArea.append(line);
                        textArea.append("\n");
                    }
                }
                return null;
            }
        };
        worker.execute();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> {
            JFrame frame = new TextFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

/**
 * For Testing:
 * Try to open *.txt file format, at least 8MB in size or, contain 100,000 or more lines.
 * Server log files are good candidate.
 */