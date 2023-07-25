package Character;

import java.util.Random;

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

    public void setDialogue(){
        dialogues[0] = "Hello, Warrior! I am the sage.";
    }

    public void setAction() {

        actionLockCounter++;
        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1; // pick a random number between 1 and 100
            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }

}
