package Character;

import java.awt.image.BufferedImage;
import java.util.Random;

import Main.GamePanel;

public class Healer extends Character{
    //the healer class is a character that can heal other characters
    //in our game, the healer is a cat
    BufferedImage up3, up4, down3, down4, left3, left4, right3, right4;
    public Healer(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;

        getImage();
    }
    public void getImage(){
        //the healer has 4 images for each direction
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

    public void setAction(){
        //the healer moves around randomly
        actionLockCounter++;
        if (actionLockCounter == 120) {
            Random random = new Random();
            //healer moves in a random direction
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

    public void catHeal(){
        super.catHeal();
    }

}
