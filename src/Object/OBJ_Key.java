package Object;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class OBJ_Key extends SuperObject {
    GamePanel gp;

    public OBJ_Key(GamePanel gp) {
        this.gp = gp;
        name = "key";
        try {
            File file = new File("src/objects/key.png");
            FileInputStream fis = new FileInputStream(file);
            image = ImageIO.read(fis);
            ut.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
