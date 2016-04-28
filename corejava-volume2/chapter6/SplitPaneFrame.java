package chapter6;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class SplitPaneFrame extends JFrame {

    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 300;
    
    private Planet[] planets = { 
        new Planet("Mercury", 2440, 0),
        new Planet("Venus", 6052, 0),
        new Planet("Earth", 6378, 1),
        new Planet("Mars", 3397, 2),
        new Planet("Jupiter", 71492, 16),
        new Planet("Saturn", 60268, 18),
        new Planet("Uranus", 25559, 17),
        new Planet("Neptune", 24766, 8),
        new Planet("Pluto", 1137, 1)
    };
    
    public SplitPaneFrame() {
        setTitle("SplitPaneFrame");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        
        final JList<Planet> planetList = new JList<>(planets);
        final JLabel planetImage = new JLabel();
        final JTextArea planetDescription = new JTextArea();
        
        planetList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                Planet value = (Planet) planetList.getSelectedValue();
                planetImage.setIcon(value.getImage());
                planetDescription.setText(value.getDescription());
            }
        });
        
        JSplitPane innerPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, planetList, planetImage);
        
        innerPane.setContinuousLayout(true); //false
        innerPane.setOneTouchExpandable(true); //false
        
        JSplitPane outerPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, innerPane, planetDescription);
        add(outerPane, BorderLayout.CENTER);
    }
    
    class Planet {
        private String name;
        private double radius;
        private int moons;
        private ImageIcon image;
        
        public Planet(String name, double radius, int moons) {
            this.name = name;
            this.radius = radius;
            this.moons = moons;
            this.image = new ImageIcon(getClass().getResource(name.toLowerCase() + ".png"));
        }
        
        public String getDescription() {
            return "Radius: " + radius + "\n" + "Moons: " + moons;
        }
        
        public ImageIcon getImage() {
            return this.image;
        }
        
        public String toString() {
            return this.name;
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> {
            JFrame frame = new SplitPaneFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}