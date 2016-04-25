package chapter6;

import java.awt.*;
import javax.swing.*;

public class ObjectInspectorFrame extends JFrame {

    private JTree tree;
    
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEfAULT_HEIGHT = 300;
    
    public ObjectInspectorFrame() {
        setTitle("ObjectInspectorFrame");
        setSize(DEFAULT_WIDTH, DEfAULT_HEIGHT);
        
        Variable v = new Variable(getClass(), "this", this);
        ObjectTreeModel model = new ObjectTreeModel();
        model.setRoot(v);
        
        tree = new JTree(model);
        add(new JScrollPane(tree), BorderLayout.CENTER);
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new ObjectInspectorFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}