package Object;

import Character.Character;
import Main.GamePanel;

public class OBJ_Sword extends Character {
    public OBJ_Sword(GamePanel gp) {
        super(gp);
        type = swordType;
        name = "sword";
        down1= setUp("src/objects/sword_normal", gp.tileSize, gp.tileSize);
        attackValue= 1;
        attackArea.width = 36;
        attackArea.height = 36;
        description = "[" +name+"]\nA magical ancient sword.";
        
    }

}
