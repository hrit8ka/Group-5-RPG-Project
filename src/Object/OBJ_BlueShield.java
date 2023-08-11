package Object;

import Character.Character;
import Main.GamePanel;

public class OBJ_BlueShield extends Character {
    public OBJ_BlueShield(GamePanel gp) {
        super(gp);
        type = armorType;
        name = "Eldoria shield";
        down1= setUp("src/objects/blue_shield", gp.tileSize, gp.tileSize);
        defenseValue= 1;
        description = "[" +name+"]\nA magical shield.";
        
    }

}