/**
 * The NPC_Sage class is a character in a game that provides information and guidance to the player.
 */
package Character;

import java.util.Random;

import Main.GamePanel;


public class NPC_Sage extends Character {

    // constructor for the sage class
    public NPC_Sage(GamePanel gp) {
        super(gp);// call the super constructor

        direction = "down";// the sage starts facing down
        speed = 2;// the sage moves at a speed of 1

        getImage();// get the images for the sage
        setDialogue();// set the dialogue for the sage
    }

    // get the images for the sage
    public void getImage() {
        // the sage has 2 images for each direction
        up1 = setUp("./src/npc/sage_up_1", gp.tileSize, gp.tileSize);
        up2 = setUp("./src/npc/sage_up_2", gp.tileSize, gp.tileSize);
        down1 = setUp("./src/npc/sage_down_1", gp.tileSize, gp.tileSize);
        down2 = setUp("./src/npc/sage_down_2", gp.tileSize, gp.tileSize);
        left1 = setUp("./src/npc/sage_left_1", gp.tileSize, gp.tileSize);
        left2 = setUp("./src/npc/sage_left_2", gp.tileSize, gp.tileSize);
        right1 = setUp("./src/npc/sage_right_1", gp.tileSize, gp.tileSize);
        right2 = setUp("./src/npc/sage_right_2", gp.tileSize, gp.tileSize);

    }

    // set the dialogue for the sage
    public void setDialogue() {
        // the sage has 5 dialogues
        dialogues[0] = "Hello, Warrior! I am the sage.";
        dialogues[1] = "I am here to guide you on \nyour journey.";
        dialogues[2] = "You must defeat the evil forest monster \nto find the hidden gem.";
        dialogues[3] = "You will then save Minish Haven \nand become a hero!";
        dialogues[4] = "Good luck!";
    }

    // set the action for the sage
    public void setAction() {
        if (onPath == true) {
            // int goalCol=12;
            // int goalRow=9;
            int goalCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;

            searchPath(goalCol, goalRow);

        } else {
            // if the sage is not on the path, the sage will move randomly
            actionLockCounter++;// increment the action lock counter
            if (actionLockCounter == 120) {// if the action lock counter is 120
                Random random = new Random();// create a new random object
                int i = random.nextInt(100) + 1; // pick a random number between 1 and 100
                if (i <= 25) {// if the number is less than or equal to 25
                    direction = "up";// set the direction to up
                }
                if (i > 25 && i <= 50) {// if the number is greater than 25 and less than or equal to 50
                    direction = "down";// set the direction to down
                }
                if (i > 50 && i <= 75) {// if the number is greater than 50 and less than or equal to 75
                    direction = "left";// set the direction to left
                }
                if (i > 75 && i <= 100) {// if the number is greater than 75 and less than or equal to 100
                    direction = "right";// set the direction to right
                }
                actionLockCounter = 0;// reset the action lock counter
            }
        }
    }

    // method speak for the sage to speak to the player when the player is in front
    // of him
    public void speak() {
        super.speak();// call the super speak method to speak to the player
        onPath = true;
    }
}