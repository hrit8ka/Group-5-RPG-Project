package Object;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Boots extends SuperObject{
    public OBJ_Boots() {
        name = "boots";
        try {
            File file = new File("src/objects/boots.png");
            FileInputStream fis = new FileInputStream(file);
            image = ImageIO.read(fis);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
