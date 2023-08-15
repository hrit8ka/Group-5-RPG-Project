package Object;

import Main.GamePanel;
import Character.Character;

public class Candle extends Character {

    public Candle(GamePanel gp) {
        super(gp);
        type = lightType;
        name = "candle";
        down1 = setUp("./src/objects/candle", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nNot very bright, but it's\nbetter than nothing.";
        price = 200;
        lightRadius = 250;

    }

}
