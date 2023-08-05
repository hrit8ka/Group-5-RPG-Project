package Object;

import Character.Character;
import Main.GamePanel;

public class OBJ_Sword extends Character {
    public OBJ_Sword(GamePanel gp) {
        super(gp);

        name = "sword";
        down1= setUp("/objects/sword_normal.png", gp.tileSize, gp.tileSize);
        attackValue= 1;
        
    }

}
