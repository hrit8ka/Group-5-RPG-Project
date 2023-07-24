package Object;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class OBJ_Door extends SuperObject{
    GamePanel gp;
     public OBJ_Door(GamePanel gp) {
        this.gp=gp;
        name = "door";
        try {
            File file = new File("src/objects/door.png");
            FileInputStream fis = new FileInputStream(file);
            image = ImageIO.read(fis);
            ut.scaleImage(image, gp.tileSize, gp.tileSize);


        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
