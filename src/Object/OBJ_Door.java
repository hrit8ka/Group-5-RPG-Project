package Object;

import Character.Character;
import Main.GamePanel;

public class OBJ_Door extends Character {
    //GamePanel gp;

    public OBJ_Door(GamePanel gp) {
        //this.gp = gp;
        super(gp);
        name = "door";
        down1= setUp("src/objects/door");
        
        collision = true;

        solidArea.x=0;
        solidArea.y=16;
        solidArea.width=48;
        solidArea.height=32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
