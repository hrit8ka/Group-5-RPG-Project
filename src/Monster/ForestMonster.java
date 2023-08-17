/**
 * The ForestMonster class is a subclass of the Character class and represents a monster in a game.
 */
package Monster;

import Main.GamePanel;
import Object.Coin;
import Object.Fireball;
import Object.OBJ_BlackCrystal;
import Object.OBJ_Heart;

import java.util.Random;

import Character.Character;
public class ForestMonster extends Character{
    GamePanel gp;
    public ForestMonster(GamePanel gp){
        super(gp);
        this.gp = gp;

        type = monsterType;
        name = "Forest Monster";
        defaultSpeed = 2;
        speed = defaultSpeed;
        maxLife = 8;
        life = maxLife;
        attack = 4;
        defense = 2;
        xp = 6;
        projectile = new Fireball(gp);
        
        solidArea.x = 3;// set solid area x to 3
        solidArea.y = 18;// set solid area y to 18
        solidArea.width = 42;// set solid area width to 42
        solidArea.height = 30;// set solid area height to 30
        solidAreaDefaultX = solidArea.x;// set solid area default x to solid area x
        solidAreaDefaultY = solidArea.y;// set solid area default y to solid area y
        attackArea.width = 48;
        attackArea.height = 48;
        getImage();
        //getAttackImage();
        
    }

    public void getImage(){
       /*  up1 = setUp("src/monsters/walk_1",gp.tileSize, gp.tileSize);
        up2 = setUp("src/monsters/walk_2",gp.tileSize, gp.tileSize);
        down1 = setUp("src/monsters/walk_3",gp.tileSize, gp.tileSize);
        down2 = setUp("src/monsters/walk_4",gp.tileSize, gp.tileSize);
        left1 = setUp("src/monsters/walk_5",gp.tileSize, gp.tileSize);
        left2 = setUp("src/monsters/walk_6",gp.tileSize, gp.tileSize);
        right1 = setUp("src/monsters/walk_1",gp.tileSize, gp.tileSize);
        right2 = setUp("src/monsters/walk_2",gp.tileSize, gp.tileSize); */

        up1 = setUp("src/monsters/forestmonster_1", gp.tileSize, gp.tileSize);
        up2 = setUp("src/monsters/forestmonster_2", gp.tileSize, gp.tileSize);
        down1 = setUp("src/monsters/forestmonster_3", gp.tileSize, gp.tileSize);
        down2 = setUp("src/monsters/forestmonster_4", gp.tileSize, gp.tileSize);
        left1 = setUp("src/monsters/forestmonster_5", gp.tileSize, gp.tileSize);
        left2 = setUp("src/monsters/forestmonster_6", gp.tileSize, gp.tileSize);
        right1 = setUp("src/monsters/forestmonster_1", gp.tileSize, gp.tileSize);
        right2 = setUp("src/monsters/forestmonster_2", gp.tileSize, gp.tileSize);
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
        //check if the monster is attacking
        if(attacking == false){
            checkAttack(30, gp.tileSize*4, gp.tileSize);
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
