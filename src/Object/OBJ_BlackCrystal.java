/**
 * The OBJ_BlackCrystal class is a subclass of the Character class that represents a black crystal
 * object in a game, which can be used to heal the player character.
 */
package Object;

import Character.Character;
import Main.GamePanel;

public class OBJ_BlackCrystal extends Character {
    GamePanel gp;
    public OBJ_BlackCrystal(GamePanel gp) {
        
        super(gp);
        this.gp = gp;
        type = crystalType;
        name = "black crystal";
        value = 5;
        down1 = setUp("src/objects/black_crystal", gp.tileSize, gp.tileSize);

        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        description = "[" + name + "]\nPowerful black crystal\nIt heals you by " + value + " HP.";
        price = 50;
        stackable = true;
    }
    public boolean use(Character Character){
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You used the " + name + ".\nYou healed by " + value + " HP.";
        Character.life +=  value;
        if(gp.player.life > gp.player.maxLife){
            gp.player.life = gp.player.maxLife;
        }
        gp.playSE(2);
        return true;
    }
}
