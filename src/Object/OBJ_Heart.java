package Object;

import Character.Character;

import Main.GamePanel;

public class OBJ_Heart extends Character {
    //GamePanel gp;

    public OBJ_Heart(GamePanel gp) {
        //this.gp = gp;
        super(gp);
        name = "Heart";
        image = setUp("src/objects/heart_full", gp.tileSize, gp.tileSize);
        image2 = setUp("src/objects/heart_half", gp.tileSize, gp.tileSize);
        image3 = setUp("src/objects/heart_blank", gp.tileSize, gp.tileSize);
    }
}
