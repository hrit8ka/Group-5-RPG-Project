package Object;

import Character.Character;

import Main.GamePanel;

public class OBJ_Chest extends Character {
    //GamePanel gp;

    public OBJ_Chest(GamePanel gp) {
        //this.gp = gp;
        super(gp);
        name = "chest";
        down1 = setUp("src/objects/chest");

    }
}
