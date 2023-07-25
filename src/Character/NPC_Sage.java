package Character;

import Main.GamePanel;

public class NPC_Sage extends Character {
    
    public NPC_Sage(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;

        getImage();
    }

    public void getImage() {

        up1 = setUp("./src/npc/sage_up_1");
        up2 = setUp("./src/npc/sage_up_2");
        down1 = setUp("./src/npc/sage_down_1");
        down2 = setUp("./src/npc/sage_down_2");
        left1 = setUp("./src/npc/sage_left_1");
        left2 = setUp("./src/npc/sage_left_2");
        right1 = setUp("./src/npc/sage_right_1");
        right2 = setUp("./src/npc/sage_right_2");

    }
    public void setAction(){
        
    }

}
