package Object;

import Character.Character;

import Main.GamePanel;

//Heart class for the heart object
public class OBJ_Heart extends Character {
    // GamePanel gp;

    // constructor for heart
    public OBJ_Heart(GamePanel gp) {
        // this.gp = gp;
        super(gp);
        name = "Heart";
        // import images
        image = setUp("src/objects/heart_full", gp.tileSize, gp.tileSize);
        image2 = setUp("src/objects/heart_half", gp.tileSize, gp.tileSize);
        image3 = setUp("src/objects/heart_blank", gp.tileSize, gp.tileSize);
    }
}
