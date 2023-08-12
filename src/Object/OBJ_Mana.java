package Object;

import Character.Character;
import Main.GamePanel;

public class OBJ_Mana extends Character {

    GamePanel gp;

    public OBJ_Mana(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "mana";
        image = setUp("./src/objects/mana_full", gp.tileSize, gp.tileSize);
        image2 = setUp("./src/objects/mana_blank", gp.tileSize, gp.tileSize);
    }
    
}
