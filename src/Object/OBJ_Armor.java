package Object;

import Character.Character;
import Main.GamePanel;

public class OBJ_Armor extends Character {
    public OBJ_Armor(GamePanel gp) {
        super(gp);

        name = "armor";
        down1 = setUp("/objects/shield_wood.png", gp.tileSize, gp.tileSize);
        defenseValue = 1;

    }

}
