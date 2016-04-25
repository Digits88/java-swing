package chapter6;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;

public class ClassTreeFrame extends JFrame {

    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 300;
    
    private DefaultMutableTreeNode root;
    private DefaultTreeModel model;
    private JTree tree;
    private JTextField textField;
    private JTextArea textArea;
    
    public ClassTreeFrame() {
        setTitle("ClassTreeFrame");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        
        root = new DefaultMutableTreeNode(java.lang.Object.class);
        model = new DefaultTreeModel(root);
        tree = new JTree(model);
        
        addClass(getClass());
        
        ClassNameTreeCellRenderer renderer = new ClassNameTreeCellRenderer();
        renderer.setClosedIcon(new ImageIcon(getClass().getResource("red-ball.png")));
        renderer.setOpenIcon(new ImageIcon(getClass().getResource("yellow-ball.png")));
        renderer.setLeafIcon(new ImageIcon(getClass().getResource("blue-ball.png")));
        tree.setCellRenderer(renderer);
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent event) {
                TreePath path = tree.getSelectionPath();
                if (path == null) return;
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
                Class<?> c = (Class<?>) selectedNode.getUserObject();
                String description = getFieldDescription(c);
                textArea.setText(description);
            }
        });
        
        int mode = TreeSelectionModel.SINGLE_TREE_SELECTION;
        tree.getSelectionModel().setSelectionMode(mode);
        
        textArea = new JTextArea();
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        panel.add(new JScrollPane(tree));
        panel.add(new JScrollPane(textArea));
        
        add(panel, BorderLayout.CENTER);
        
        addTextField();
    }
    
    public void addTextField() {
        JPanel panel = new JPanel();
        ActionListener addListener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try {
                    String text = textField.getText();
                    addClass(Class.forName(text));
                    textField.setText("");
                } catch (ClassNotFoundException e) {
                    JOptionPane.showMessageDialog(null, "Class not found");
                }
                
            }
        };
        
        textField = new JTextField(20);
        textField.addActionListener(addListener);
        panel.add(textField);
        
        JButton addButton = new JButton("Add");
        addButton.addActionListener(addListener);
        panel.add(addButton);
        
        add(panel, BorderLayout.SOUTH);
    }
    
    @SuppressWarnings("unchecked")
    public DefaultMutableTreeNode findUserObject(Object obj) {
        Enumeration<TreeNode> e = (Enumeration<TreeNode>) root.breadthFirstEnumeration();
        while(e.hasMoreElements()) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.nextElement();
            if (node.getUserObject().equals(obj)) return node;
        }
        return null;
    }
    
    public DefaultMutableTreeNode addClass(Class<?> c) {
        if (c.isInterface() || c.isPrimitive()) return null;
        
        DefaultMutableTreeNode node = findUserObject(c);
        if (node != null) return node;
        
        Class<?> s = c.getSuperclass();
        DefaultMutableTreeNode parent;
        if (s == null) { 
            parent = root; 
        } else { 
            parent = addClass(s);
        }
        
        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(c);
        model.insertNodeInto(newNode, parent, parent.getChildCount());
        
        TreePath path = new TreePath(model.getPathToRoot(newNode));
        tree.makeVisible(path);
        
        return newNode;
    }
    
    public static String getFieldDescription(Class<?> c) {
        StringBuilder r = new StringBuilder();
        Field[] fields = c.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field f = fields[i];
            if ((f.getModifiers() & Modifier.STATIC) != 0) {
                r.append("static ");
            }
            r.append(f.getType().getName());
            r.append(" ");
            r.append(f.getName());
            r.append("\n");
        }
        return r.toString();
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new ClassTreeFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}