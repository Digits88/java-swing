package chapter9;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Excerpt:
 * When you construct a JDialog object, you need to specify the owner frame.
 * However, quite often you want to show the same dialog with different owner frames.
 * It is better to pick the owner frame when you are ready to show the dialog, not when
 * you construct the PasswordChooser object. The trick is to have the PasswordChooser
 * extend JPanel instead of JDialog. Build a JDialog object on the fly in the showDialog method.
 */
public class PasswordChooser extends JPanel {
    
    private JTextField username;
    private JPasswordField password;
    private JButton okButton;
    private boolean ok;
    private JDialog dialog;
    
    public PasswordChooser() {
        setLayout(new BorderLayout());
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,2));
        panel.add(new JLabel("User name:"));
        panel.add(username = new JTextField(""));
        panel.add(new JLabel("Password:"));
        panel.add(password = new JPasswordField(""));
        add(panel, BorderLayout.CENTER);
        
        okButton = new JButton("Ok");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                ok = true;
                dialog.setVisible(false);
            }
        });
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                dialog.setVisible(false);
            }
        });
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    public void setUser(User u) {
        username.setText(u.getName());
    }
    
    public User getUser() {
        return new User(username.getText(), password.getPassword());
    }
    
    public boolean showDialog(Component parent, String title) {
        ok = false;
        
        // locate the owner frame
        Frame owner = null; // it is safe to have owner equal to null.
        if (parent instanceof Frame) { 
            owner = (Frame) parent; 
        } else {
            // sometime the owner frame is not readily available. 
            // then compute it from any parent component.
            // JOptionPane class also use this mechanism.
            owner = (Frame) SwingUtilities.getAncestorOfClass(Frame.class, parent);
        }
        
        if (dialog == null || dialog.getOwner() != owner) {
            dialog = new JDialog(owner, true);
            dialog.add(this);
            dialog.getRootPane().setDefaultButton(okButton);
            dialog.pack();
        }
        
        dialog.setTitle(title);
        dialog.setVisible(true);
        return ok;
    }
}