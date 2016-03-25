package chapter9;

import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.filechooser.FileFilter;

public class FileIconView extends FileView {
    
    private FileFilter filter;
    private Icon icon;
    
    public FileIconView(FileFilter aFilter, Icon aIcon) {
        filter = aFilter;
        icon = aIcon;
    }
    
    public Icon getIcon(File f) {
        if (!f.isDirectory() && filter.accept(f)) return icon;
        else return null;
    }
}