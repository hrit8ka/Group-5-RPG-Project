package Object;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Door extends SuperObject{
     public OBJ_Door() {
        name = "door";
        try {
            File file = new File("src/objects/door.png");
            FileInputStream fis = new FileInputStream(file);
            image = ImageIO.read(fis);

        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
