/**
 * The Healer class is a subclass of the Character class and represents a cat character that can heal
 * other characters in a game.
 */
package Character;

import java.awt.image.BufferedImage;
import java.util.Random;

import Main.GamePanel;

//the healer class extends the character class using inheritance
public class Healer extends Character {

    BufferedImage up3, up4, down3, down4, left3, left4, right3, right4;// the healer has 4 images for each direction

    public Healer(GamePanel gp) {// constructor
        super(gp);// call the super constructor

        direction = "down";// the healer starts facing down
        speed = 1;// the healer moves at a speed of 1

        getImage();// get the images for the healer
    }

    // method to get the images for the healer
    public void getImage() {
        // the healer has 4 images for each direction
        up1 = setUp("./src/npc/cat_up_1", gp.tileSize, gp.tileSize);
        up2 = setUp("./src/npc/cat_up_2", gp.tileSize, gp.tileSize);
        up3 = setUp("./src/npc/cat_up_3", gp.tileSize, gp.tileSize);
        up4 = setUp("./src/npc/cat_up_4", gp.tileSize, gp.tileSize);
        down1 = setUp("./src/npc/cat_down_1", gp.tileSize, gp.tileSize);
        down2 = setUp("./src/npc/cat_down_2", gp.tileSize, gp.tileSize);
        down3 = setUp("./src/npc/cat_down_3", gp.tileSize, gp.tileSize);
        down4 = setUp("./src/npc/cat_down_4", gp.tileSize, gp.tileSize);
        left1 = setUp("./src/npc/cat_left_1", gp.tileSize, gp.tileSize);
        left2 = setUp("./src/npc/cat_left_2", gp.tileSize, gp.tileSize);
        left3 = setUp("./src/npc/cat_left_3", gp.tileSize, gp.tileSize);
        left4 = setUp("./src/npc/cat_left_4", gp.tileSize, gp.tileSize);
        right1 = setUp("./src/npc/cat_right_1", gp.tileSize, gp.tileSize);
        right2 = setUp("./src/npc/cat_right_2", gp.tileSize, gp.tileSize);
        right3 = setUp("./src/npc/cat_right_3", gp.tileSize, gp.tileSize);
        right4 = setUp("./src/npc/cat_right_4", gp.tileSize, gp.tileSize);

    }

    // method to set the action of the healer
    public void setAction() {
        // the healer moves around randomly
        actionLockCounter++;// the action lock counter is incremented
        if (actionLockCounter == 120) {// if the action lock counter is 120
            Random random = new Random();// a random object is created
            // healer moves in a random direction
            int i = random.nextInt(100) + 1; // pick a random number between 1 and 100
            if (i <= 25) {// if the number is less than or equal to 25
                direction = "up";// the healer moves up
            }
            if (i > 25 && i <= 50) {// if the number is greater than 25 and less than or equal to 50
                direction = "down";// the healer moves down
            }
            if (i > 50 && i <= 75) {// if the number is greater than 50 and less than or equal to 75
                direction = "left";// the healer moves left
            }
            if (i > 75 && i <= 100) {// if the number is greater than 75 and less than or equal to 100
                direction = "right";// the healer moves right
            }
            actionLockCounter = 0;// the action lock counter is reset
        }
    }

    // method to heal player
    public void catHeal() {
        super.catHeal();// call the super method to heal the player
    }

}
