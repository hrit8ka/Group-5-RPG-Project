/**
 * The Coin class extends the Character class and represents a gold coin object in a game, which can be
 * picked up by the player character to increase their gold count.
 */
package Object;

import Character.Character;
import Main.GamePanel;

public class Coin extends Character {

    GamePanel gp;

    public Coin(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = pickUpType;
        name = "gold coin";
        value = 10;

        down1 = setUp("./src/objects/goldcoin", gp.tileSize, gp.tileSize);
    }

    public boolean use(Character Character) {
        gp.playSE(1);
        gp.ui.addMessage("Gold + " + value);
        gp.player.gold += value;
        return true;

    }

}
