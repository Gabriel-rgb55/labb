import java.awt.*;
import java.awt.image.BufferedImage;

public interface CarObserver {
    void update(Point[] positions, BufferedImage[] images, String status);
}
