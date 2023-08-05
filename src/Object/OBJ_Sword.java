package Object;

import Character.Character;
import Main.GamePanel;

public class OBJ_Sword extends Character {
    public OBJ_Sword(GamePanel gp) {
        super(gp);

        name = "sword";
        down1= setUp("src/objects/sword_normal", gp.tileSize, gp.tileSize);
        attackValue= 1;
        
    }

}
