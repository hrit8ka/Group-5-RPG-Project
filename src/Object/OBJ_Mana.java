package Object;

import Main.GamePanel;
import Character.Character;

//Mana class for the mana object
public class OBJ_Mana extends Character {

    GamePanel gp;// gamepanel

    // constructor for mana
    public OBJ_Mana(GamePanel gp) {
        super(gp);// super
        this.gp = gp;

        name = "mana";
        // import images
        image = setUp("src/objects/mana_full", gp.tileSize, gp.tileSize);
        image2 = setUp("src/objects/mana_blank", gp.tileSize, gp.tileSize);
    }

}
