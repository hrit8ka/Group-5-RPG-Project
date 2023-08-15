package Monster;

import Main.GamePanel;
import Object.Coin;
import Object.OBJ_BlackCrystal;
import Object.OBJ_Heart;
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
        defaultSpeed = 1;// set default speed to 1
        speed = defaultSpeed;// set speed to default speed
        maxLife = 5;// set max life to 5
        life = maxLife;// set life to max life
        attack = 2;// set attack to 5
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

    public void update() {
        super.update();

        int xDistance = Math.abs(worldX - gp.player.worldX);
        int yDistance = Math.abs(worldY - gp.player.worldY);
        int tileDistance = (xDistance + yDistance) / gp.tileSize;

        if (onPath == false && tileDistance < 5) {
            int i = new Random().nextInt(100) + 1; // pick a random number between 1 and 100
            if (i > 50) {
                onPath = true;
            }
        }
        if (onPath == true && tileDistance > 20) {
            onPath = false;
        }
    }

    // method to set the action of the slime monster
    public void setAction() {
        if (onPath == true) {
            // int goalCol=12;
            // int goalRow=9;
            int goalCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;

            searchPath(goalCol, goalRow);
            int i = new Random().nextInt(100) + 1; // pick a random number between 1 and 100
            if (i > 99 && projectile.alive == false && projectileCounter == 30) {// if i is greater than 99 and
                                                                                 // projectile
                                                                                 // is not alive and projectile counter
                                                                                 // is
                                                                                 // 30
                projectile.set(worldX, worldY, direction, true, this);// set projectile to the slime monster's world x
                                                                      // and
                                                                      // y, direction, alive to true, and owner to this
                // gp.projectileList.add(projectile);// add projectile to the projectile list
                // check if projectile array is full
                for (int j = 0; j < gp.projectile[1].length; j++) {
                    if (gp.projectile[1][j] == null) {
                        gp.projectile[1][j] = projectile;
                        break;
                    }
                }
                projectileCounter = 0;// set projectile counter to 0
            }

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

    // method to set the reaction of the slime monster when it is damaged
    public void monsterDamageReaction() {
        // when attacking, monster moves away from player
        actionLockCounter = 0;// set action lock counter to 0
        // direction = gp.player.direction;// set direction to player direction
        onPath = true;// set on path to true

    }

    // method to check the drop of the monster
    public void checkDrop() {
        // cast a random number between 1 and 100
        int i = new Random().nextInt(100) + 1;
        // set the monster drop
        if (i <= 50) {// if i is less than or equal to 50
            dropItem(new Coin(gp));// drop a coin
        }
        if (i > 50 && i <= 75) {// if i is greater than 50 and less than or equal to 75
            dropItem(new OBJ_Heart(gp));// drop a heart
        }
        if (i > 75 && i <= 100) {// if i is greater than 75 and less than or equal to 100
            dropItem(new OBJ_BlackCrystal(gp));// drop a black crystal
        }

    }
}