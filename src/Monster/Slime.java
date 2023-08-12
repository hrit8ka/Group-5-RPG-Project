package Monster;

import Main.GamePanel;
import Object.Rock;

import java.util.Random;

import Character.Character;

// Slime class for the slime monster
public class Slime extends Character {
    GamePanel gp;// game panel

    // constructor for Slime
    public Slime(GamePanel gp) {
        super(gp);// call super constructor

        this.gp = gp;// set game panel

        type = monsterType;// set type to monsterType
        name = "slime";// set name to slime
        speed = 1;// set speed to 1
        maxLife = 5;// set max life to 5
        life = maxLife;// set life to max life
        attack = 5;// set attack to 5
        defense = 0;// set defense to 0
        xp = 2;// set xp to 2
        projectile = new Rock(gp);// set projectile to a new rock

        solidArea.x = 3;// set solid area x to 3
        solidArea.y = 18;// set solid area y to 18
        solidArea.width = 42;// set solid area width to 42
        solidArea.height = 30;// set solid area height to 30
        solidAreaDefaultX = solidArea.x;// set solid area default x to solid area x
        solidAreaDefaultY = solidArea.y;// set solid area default y to solid area y

        getImage();// get image
    }

    // method to get the image of the slime monster
    public void getImage() {

        up1 = setUp("src/monsters/blueslime", gp.tileSize, gp.tileSize);
        up2 = setUp("src/monsters/blueslime2", gp.tileSize, gp.tileSize);
        down1 = setUp("src/monsters/blueslime", gp.tileSize, gp.tileSize);
        down2 = setUp("src/monsters/blueslime2", gp.tileSize, gp.tileSize);
        left1 = setUp("src/monsters/blueslime", gp.tileSize, gp.tileSize);
        left2 = setUp("src/monsters/blueslime2", gp.tileSize, gp.tileSize);
        right1 = setUp("src/monsters/blueslime", gp.tileSize, gp.tileSize);
        right2 = setUp("src/monsters/blueslime2", gp.tileSize, gp.tileSize);

    }

    // method to set the action of the slime monster
    public void setAction() {
        actionLockCounter++;// increment action lock counter
        if (actionLockCounter == 120) {// if action lock counter is 120
            Random random = new Random();// create a new random
            int i = random.nextInt(100) + 1; // pick a random number between 1 and 100
            if (i <= 25) {
                direction = "up";// if i is less than or equal to 25, set direction to up
            }
            if (i > 25 && i <= 50) {
                direction = "down";// if i is greater than 25 and less than or equal to 50, set direction to down
            }
            if (i > 50 && i <= 75) {
                direction = "left";// if i is greater than 50 and less than or equal to 75, set direction to left
            }
            if (i > 75 && i <= 100) {
                direction = "right";// if i is greater than 75 and less than or equal to 100, set direction to right
            }
            actionLockCounter = 0;// set action lock counter to 0
        }
        int i = new Random().nextInt(100) + 1; // pick a random number between 1 and 100
        if (i > 99 && projectile.alive == false && projectileCounter == 30) {// if i is greater than 99 and projectile
                                                                             // is not alive and projectile counter is
                                                                             // 30
            projectile.set(worldX, worldY, direction, true, this);// set projectile to the slime monster's world x and
                                                                  // y, direction, alive to true, and owner to this
            gp.projectileList.add(projectile);// add projectile to the projectile list
            projectileCounter = 0;// set projectile counter to 0
        }
    }

    // method to set the reaction of the slime monster when it is damaged
    public void monsterDamageReaction() {
        // when attacking, monster moves away from player
        actionLockCounter = 0;// set action lock counter to 0
        direction = gp.player.direction;// set direction to player direction

    }
}