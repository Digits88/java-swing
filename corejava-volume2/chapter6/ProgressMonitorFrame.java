package chapter6;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ProgressMonitorFrame extends JFrame {

    public static final int TEXT_ROWS = 10;
    public static final int TEXT_COLUMNS = 40;
    
    private Timer cancelMonitor;
    private JButton startButton;
    private ProgressMonitor progressDialog;
    private JTextArea textArea;
    private SimulatedActivity activity;
    
    public ProgressMonitorFrame() {
        textArea = new JTextArea(TEXT_ROWS, TEXT_COLUMNS);
        
        JPanel panel = new JPanel();
        startButton = new JButton("Start");
        panel.add(startButton);
        
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
        
        startButton.addActionListener((ActionEvent event)-> {
            startButton.setEnabled(false);
            final int MAX = 200;
            activity = new SimulatedActivity(MAX);
            activity.execute();
            
            progressDialog = new ProgressMonitor(ProgressMonitorFrame.this, "Waiting for Simulated Activity", null, 0, MAX);
            cancelMonitor.start();
        });
        
        cancelMonitor = new Timer(500, new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (progressDialog.isCanceled()) {
                    activity.cancel(true);
                    startButton.setEnabled(true);
                }
                else if (activity.isDone()) {
                    progressDialog.close();
                    startButton.setEnabled(true);
                }
                else {
                    progressDialog.setProgress(activity.getProgress());
                }
            }
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
                    textArea.append(current + "\n");
                    setProgress(current);
                }
            } catch (InterruptedException e) {
                //e.printStackTrace();
                System.out.println("InterruptedException");
            }
            return null;
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> {
            JFrame frame = new ProgressMonitorFrame();
            frame.setTitle("ProgressMonitorFrame");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}