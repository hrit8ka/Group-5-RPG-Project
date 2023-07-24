package Object;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Chest extends SuperObject{
      public OBJ_Chest() {
        name = "chest";
        try {
            File file = new File("src/objects/chest.png");
            FileInputStream fis = new FileInputStream(file);
            image = ImageIO.read(fis);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
