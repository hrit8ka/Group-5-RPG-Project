package Monster;

import Main.GamePanel;

import java.util.Random;

import Character.Character;

public class Slime extends Character {

    public Slime(GamePanel gp) {
        super(gp);

        name = "slime";
        speed = 1;
        maxLife = 4;
        life = maxLife;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {
       /*  up1 = setUp("src/monsters/greenslime_down_1");
        up2 = setUp("src/monsters/greenslime_down_2");
        down1 = setUp("src/monsters/greenslime_down_1");
        down2 = setUp("src/monsters/greenslime_down_2");
        left1 = setUp("src/monsters/greenslime_down_1");
        left2 = setUp("src/monsters/greenslime_down_2");
        right1 = setUp("src/monsters/greenslime_down_1");
        right2 = setUp("src/monsters/greenslime_down_2"); */

        up1= setUp("src/monsters/blueslime");
        up2= setUp("src/monsters/blueslime2");
        down1= setUp("src/monsters/blueslime");
        down2= setUp("src/monsters/blueslime2");
        left1= setUp("src/monsters/blueslime");
        left2= setUp("src/monsters/blueslime2");
        right1= setUp("src/monsters/blueslime");
        right2= setUp("src/monsters/blueslime2");


    }

    public void setAction(){
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
