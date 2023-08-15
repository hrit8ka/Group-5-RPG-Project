package Object;

import Character.Character;
import Main.GamePanel;

/*public class OBJ_Key extends SuperObject {
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
}*/

public class OBJ_Key extends Character {
    GamePanel gp;

    public OBJ_Key(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = consumableType;
        name = "key";
        down1 = setUp("src/objects/key", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nIt opens the door.";

    }

    public boolean use(Character character) {
        gp.gameState = gp.dialogueState;

        int objectIndex = getDetected(character, gp.obj, "door");
        if (objectIndex != 999) {
            gp.ui.currentDialogue = "You used the key to open the door.";
            gp.playSE(3);
            gp.obj[gp.currentMap][objectIndex] = null;
            return true;
        } else {
            gp.ui.currentDialogue = "There is no door to open.";
            return false;
        }
    }
}