package Object;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class OBJ_Heart extends SuperObject {
    GamePanel gp;

    public OBJ_Heart(GamePanel gp) {
        this.gp = gp;
        name = "Heart";
        try {
            File file = new File("src/objects/heart_full.png");
            FileInputStream fis = new FileInputStream(file);
            image = ImageIO.read(fis);
            // import image
            file = new File("src/objects/heart_half.png");
            fis = new FileInputStream(file);
            image2 = ImageIO.read(fis);
            // import image
            file = new File("src/objects/heart_blank.png");
            fis = new FileInputStream(file);
            image3 = ImageIO.read(fis);

            image = ut.scaleImage(image, gp.tileSize, gp.tileSize);
            image2 = ut.scaleImage(image2, gp.tileSize, gp.tileSize);
            image3 = ut.scaleImage(image3, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
