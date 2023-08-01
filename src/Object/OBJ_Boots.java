package Object;

import Character.Character;

import Main.GamePanel;

public class OBJ_Boots extends Character {
    //GamePanel gp;

    public OBJ_Boots(GamePanel gp) {
        //this.gp = gp;
        super(gp);
        name = "boots";
        down1 = setUp("src/objects/boots");
        
    }

}
