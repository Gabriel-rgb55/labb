import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel {

    // Arrays to store car positions and images
    private Point[] carPositions;
    private BufferedImage[] carImages;
    private String statusMessage = "";

    // Workshop position and image
    private Point workshopPosition;
    private BufferedImage workshopImage;

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);


        carImages = new BufferedImage[4];
        // Load images and initialize positions
        try {
            carImages[0] = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
            carImages[1] = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg"));
            carImages[2] = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg"));
            carImages[3] = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
            // Load workshop image
            workshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg"));
            workshopPosition = new Point(300, 300);


        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }


    public void updateCarPositions(Point[] positions) {
        this.carPositions = positions;
        repaint();
    }


    public void setStatusMessage(String message) {
        this.statusMessage = message;
        repaint(); // Trigger a repaint to update the display
    }

    public void removeCar(int index) {
        if (index >= 0 && index < carImages.length) {
            carImages[index] = null; // Remove the image
            repaint(); // Refresh the display
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the workshop

        if (carPositions != null && carImages != null) {
            for (int i = 0; i < carPositions.length; i++) {
                if (carImages[i] != null && carPositions[i] != null) {
                    g.drawImage(carImages[i], carPositions[i].x, carPositions[i].y, null);
                }
            }
        }

        if (workshopImage != null && workshopPosition != null) {
            g.drawImage(workshopImage, workshopPosition.x, workshopPosition.y, null);
        }

        if (!statusMessage.isEmpty()) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString(statusMessage, 10, 30);
        }
    }
}
