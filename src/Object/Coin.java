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
        value = 1;

        down1 = setUp("./src/objects/goldcoin", gp.tileSize, gp.tileSize);
    }

    public boolean use(Character Character) {
        gp.playSE(1);
        gp.ui.addMessage("Gold + " + value);
        gp.player.gold += value;
        return true;

    }

}
