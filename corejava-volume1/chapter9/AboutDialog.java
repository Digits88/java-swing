package chapter9;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AboutDialog extends JDialog {

    public AboutDialog(JFrame owner) {
        super(owner, "About Dialog", true);
        add(new JLabel("<html><h1><i>Core Java</i></h1><hr/> By Cay Horstmann and Gary Cornell</html>")
            , BorderLayout.CENTER);
       
        JButton ok = new JButton("OK");
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                setVisible(false);
            }
        });
        
        JPanel panel = new JPanel();
        panel.add(ok);
        add(panel, BorderLayout.SOUTH);
        setSize(250, 150);
        pack();
    }
}