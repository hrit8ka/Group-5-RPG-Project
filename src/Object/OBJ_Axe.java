package Object;

import Character.Character;
import Main.GamePanel;

public class OBJ_Axe extends Character {
     public OBJ_Axe(GamePanel gp) {
        super(gp);
        type = axeType;
        name = "axe";
        down1= setUp("src/objects/axe", gp.tileSize, gp.tileSize);
        attackValue= 2;
        attackArea.width = 30;
        attackArea.height = 30;
        description = "[" +name+"]\nAn ancient axe.";
        price = 100;
        knockBackPower = 10;
        
    }

}
