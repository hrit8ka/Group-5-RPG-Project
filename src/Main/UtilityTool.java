package Main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

// UtilityTool class is used to scale images
public class UtilityTool {
    // scaleImage method is used to scale images
    public BufferedImage scaleImage(BufferedImage original, int width, int height) {
        // BufferedImage scaledImage = new BufferedImage(width, height
        // original.getType());
        BufferedImage scaledImage = new BufferedImage(width, height, 2);// 2 is the type for buffered image
        Graphics2D g2 = scaledImage.createGraphics();// create graphics
        g2.drawImage(original, 0, 0, width, height, null);// draw image
        g2.dispose();// dispose graphics
        return scaledImage;// return scaled image
    }
}
