package chapter6;

import java.awt.*;
import javax.swing.*;
import javax.swing.tree.*;

public class SimpleTreeFrame extends JFrame {

    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;
    
    public SimpleTreeFrame() {
        setTitle("SimpleTreeFrame");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("World");
        DefaultMutableTreeNode country = new DefaultMutableTreeNode("USA");
        root.add(country);
        DefaultMutableTreeNode state = new DefaultMutableTreeNode("California");
        country.add(state);
        DefaultMutableTreeNode city = new DefaultMutableTreeNode("San Jose");
        state.add(city);
        city = new DefaultMutableTreeNode("Cupertino");
        state.add(city);
        state = new DefaultMutableTreeNode("Michigan");
        country.add(state);
        city = new DefaultMutableTreeNode("Ann Arbor");
        state.add(city);        
        country = new DefaultMutableTreeNode("Germany");
        root.add(country);
        state = new DefaultMutableTreeNode("Schleswig-Holstein");
        country.add(state);
        city = new DefaultMutableTreeNode("Kiel");
        state.add(city);
        
        //DefaultMutableTreeNode noChildNode = new DefaultMutableTreeNode("No Children");
        //noChildNode.setAllowsChildren(false);
        //root.add(noChildNode);
        
        JTree tree = new JTree(root);
        
        //DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
        //model.setAsksAllowsChildren(true);
        //JTree tree = new JTree(root, true);
        
        //tree.putClientProperty("JTree.lineStyle", "Angled"); //default
        //tree.putClientProperty("JTree.lineStyle", "None");
        //tree.putClientProperty("JTree.lineStyle", "Horizontal");
        //tree.setShowsRootHandles(true); //handle for collapsing the root of the tree
        //tree.setRootVisible(false);
                
        add(new JScrollPane(tree));
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new SimpleTreeFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}