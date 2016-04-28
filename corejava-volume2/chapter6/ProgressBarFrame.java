package chapter6;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

public class ProgressBarFrame extends JFrame {

    public static final int TEXT_ROWS = 10;
    public static final int TEXT_COLUMNS = 40;
    
    private JButton startButton;
    private JProgressBar progressBar;
    private JCheckBox checkBox;
    private JTextArea textArea;
    private SimulatedActivity activity;
    
    public ProgressBarFrame() {
        textArea = new JTextArea(TEXT_ROWS, TEXT_COLUMNS);
        final int MAX = 200;
        JPanel panel = new JPanel();
        startButton = new JButton("Start");
        progressBar = new JProgressBar(0, MAX);
        progressBar.setStringPainted(true);
        panel.add(startButton);
        panel.add(progressBar);
        
        checkBox = new JCheckBox("indeterminate");
        checkBox.addActionListener((ActionEvent event)-> {
            progressBar.setIndeterminate(checkBox.isSelected());
            progressBar.setStringPainted(!progressBar.isIndeterminate());
        });
        
        panel.add(checkBox);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
        
        startButton.addActionListener((ActionEvent event)-> {
            startButton.setEnabled(false);
            activity = new SimulatedActivity(MAX);
            activity.execute();
        });
        
        pack();
    }
    
    class SimulatedActivity extends SwingWorker<Void, Integer> {
        private int current;
        private int target;
        
        public SimulatedActivity(int t) {
            current = 0;
            target = t;
        }
        
        protected Void doInBackground() throws Exception {
            try {
                while (current < target) {
                    Thread.sleep(100);
                    current++;
                    publish(current);
                }
            } catch (InterruptedException e) {
                //e.printStackTrace();
                System.out.println("InterruptedException");
            }
            return null;
        }
        
        protected void process(List<Integer> chunks) {
            for (Integer chunk : chunks) {
                textArea.append(chunk + "\n");
                progressBar.setValue(chunk);
            }
        }
        
        protected void done() {
            startButton.setEnabled(true);
        }
    }
    
    private static void createAndShowGUI() {
        JFrame frame = new ProgressBarFrame();
        frame.setTitle("ProgressBarFrame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> {
            createAndShowGUI();
        });
    }
}