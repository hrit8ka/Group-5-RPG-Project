package Object;

import Main.GamePanel;
import Character.Character;

public class CampingTent extends Character {
    GamePanel gp;

    public CampingTent(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = consumableType;
        name = "tent";
        down1 = setUp("./src/objects/tent", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\n\nA tent. You can rest here.";

    }

    public boolean use(Character character) {
        gp.gameState = gp.sleepState;
        gp.playSE(15);
        gp.player.life = gp.player.maxLife;
        gp.player.mana = gp.player.maxMana;
        gp.player.getSleepingImage(down1);
        return false;
        // return false; if you want to keep the item after use
    }

}
