package Object;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Key extends SuperObject {
    public OBJ_Key() {
        name = "key";
        try {
            File file = new File("src/objects/key.png");
            FileInputStream fis = new FileInputStream(file);
            image = ImageIO.read(fis);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
