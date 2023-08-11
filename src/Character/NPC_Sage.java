package Character;

import java.util.Random;

import Main.GamePanel;

public class NPC_Sage extends Character {

    public NPC_Sage(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;

        getImage();
        setDialogue();
    }

    public void getImage() {

        up1 = setUp("./src/npc/sage_up_1", gp.tileSize, gp.tileSize);
        up2 = setUp("./src/npc/sage_up_2", gp.tileSize, gp.tileSize);
        down1 = setUp("./src/npc/sage_down_1", gp.tileSize, gp.tileSize);
        down2 = setUp("./src/npc/sage_down_2", gp.tileSize, gp.tileSize);
        left1 = setUp("./src/npc/sage_left_1", gp.tileSize, gp.tileSize);
        left2 = setUp("./src/npc/sage_left_2", gp.tileSize, gp.tileSize);
        right1 = setUp("./src/npc/sage_right_1", gp.tileSize, gp.tileSize);
        right2 = setUp("./src/npc/sage_right_2", gp.tileSize, gp.tileSize);

    }

    public void setDialogue() {
        dialogues[0] = "Hello, Warrior! I am the sage.";
        dialogues[1] = "I am here to guide you on \nyour journey.";
        dialogues[2] = "You must defeat the evil wizard.";
        dialogues[3] = "He is hiding in the cave to the \nnorth.";
        dialogues[4] = "Good luck!";
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

    // the sage speaks to the player when the player is in front of him
    public void speak() {
        super.speak();
    }
}