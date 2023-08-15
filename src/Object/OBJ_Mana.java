package Object;

import Character.Character;
import Main.GamePanel;

public class OBJ_Mana extends Character {

    GamePanel gp;

    public OBJ_Mana(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = pickUpType;
        value = 1;
        name = "mana";
        down1 = setUp("./src/objects/mana_full", gp.tileSize, gp.tileSize);
        image = setUp("./src/objects/mana_full", gp.tileSize, gp.tileSize);
        image2 = setUp("./src/objects/mana_blank", gp.tileSize, gp.tileSize);
    }
    public boolean use(Character Character){
        gp.playSE(2);
        gp.ui.addMessage("Mana + " + value);
        Character.life += value;
        return true;
    }
    
}
