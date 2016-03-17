package chapter7;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;

public class ImageTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(
            new Runnable() {
                public void run() {
                    JFrame frame = new ImageFrame();
                    frame.setTitle("ImageFrame");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);
                }
            }
        );
    }
}

class ImageFrame extends JFrame {
    public ImageFrame() {
        add(new ImageComponent());
        pack();
    }
}

class ImageComponent extends JComponent {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;
    
    private Image image;

    public ImageComponent() {
        //image = new ImageIcon("blue-ball.gif").getImage();
        //image = new ImageIcon(this.getClass().getResource("blue-ball.jpg")).getImage();
        
        try {
            //image = ImageIO.read(new File("blue-ball.jpg"));
            image = ImageIO.read(getClass().getResource("blue-ball.jpg"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
    public void paintComponent(Graphics g) {
        if (image == null) return;
        
        int imageWidth = image.getWidth(this);
        int imageHeight = image.getHeight(this);
        
        // draw the image in the upper-left corner
        g.drawImage(image, 0, 0, null);
        
        // tile the image across the component
        for (int i=0; i*imageWidth <= getWidth(); i++) {
            for (int j=0; j*imageHeight <= getHeight(); j++) {
                if (i+j > 0) {
                    g.copyArea(0, 0, imageWidth, imageHeight, i*imageWidth, j*imageHeight);
                }
            }
        }
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}