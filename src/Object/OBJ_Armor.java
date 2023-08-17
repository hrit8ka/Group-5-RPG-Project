/**
 * The OBJ_Armor class is a subclass of the Character class and represents a type of armor in a game.
 */
package Object;

import Character.Character;
import Main.GamePanel;

public class OBJ_Armor extends Character {
    public OBJ_Armor(GamePanel gp) {
        super(gp);
        type = armorType;
        name = "armor";
        down1 = setUp("src/objects/shield_wood", gp.tileSize, gp.tileSize);
        defenseValue = 1;
        description = "[" +name+"]\nKnight's armor.";

        price = 10;

    }

}
