/**
 * The OBJ_Key class is a subclass of the Character class and represents a key object in a game, which
 * can be used to open doors.
 */
package Object;

import Character.Character;
import Main.GamePanel;

public class OBJ_Key extends Character {
    GamePanel gp;

    public OBJ_Key(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = consumableType;
        name = "key";
        down1 = setUp("src/objects/key", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nIt opens the door.";
        price = 100;
        stackable = true;

    }

    public boolean use(Character character) {
        gp.gameState = gp.dialogueState;

        int objectIndex = getDetected(character, gp.obj, "door");
        if (objectIndex != 999) {
            gp.ui.currentDialogue = "You used the key to open the door.";
            gp.playSE(3);
            gp.obj[gp.currentMap][objectIndex] = null; // remove the door
            return true;
        } else {
            gp.ui.currentDialogue = "There is no door to open.";
            return false;
        }
    }
}