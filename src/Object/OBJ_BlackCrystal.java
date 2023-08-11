package Object;

import Character.Character;
import Main.GamePanel;

public class OBJ_BlackCrystal extends Character {
    // GamePanel gp;

    public OBJ_BlackCrystal(GamePanel gp) {
        // this.gp = gp;
        super(gp);
        type = crystalType;
        name = "black crystal";
        down1 = setUp("src/objects/black_crystal", gp.tileSize, gp.tileSize);

        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        description = "[" + name + "]\nA powerful black crystal.";
    }
}
