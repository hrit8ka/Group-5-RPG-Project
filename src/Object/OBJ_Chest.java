package Object;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class OBJ_Chest extends SuperObject{
    GamePanel gp;

      public OBJ_Chest(GamePanel gp) {
        this.gp=gp;
        name = "chest";
        try {
            File file = new File("src/objects/chest.png");
            FileInputStream fis = new FileInputStream(file);
            image = ImageIO.read(fis);
            ut.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
