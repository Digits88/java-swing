package chapter6;

import java.awt.*;
import java.lang.reflect.*;
import javax.swing.*;
import javax.swing.tree.*;

public class ClassNameTreeCellRenderer extends DefaultTreeCellRenderer {

    private Font plainFont = null;
    private Font italicFont = null;
    
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        Class<?> c = (Class<?>) node.getUserObject();
        
        if (plainFont == null) {
            plainFont = getFont();
            if (plainFont != null) italicFont = plainFont.deriveFont(Font.ITALIC);
        }
        
        if ((c.getModifiers() & Modifier.ABSTRACT) == 0) { 
            setFont(plainFont);
        } else {
            setFont(italicFont);
        }
        return this;
    }
}