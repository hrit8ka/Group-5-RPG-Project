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
    }

    // method to set the action of the slime monster
    public void setAction() {

        if (onPath == true) {
            checkStopChasing(gp.player, 15, 100);
            // search the path to the player
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
            checkShoot(200, 30); 
        } else {
            checkStartChasing(gp.player, 5, 100);
            getRandomDirection();

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