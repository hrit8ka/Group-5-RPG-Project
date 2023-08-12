package Monster;

import Main.GamePanel;
import Object.Rock;

import java.util.Random;

import Character.Character;

// Slime class for the slime monster
public class Slime extends Character {
    GamePanel gp;

    public Slime(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = monsterType;
        name = "slime";
        speed = 1;
        maxLife = 5;
        life = maxLife;
        attack = 5;
        defense = 0;
        xp=2;
        projectile = new Rock(gp);

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

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
        int i = new Random().nextInt(100) + 1; // pick a random number between 1 and 100
        if(i> 99 && projectile.alive == false && projectileCounter == 30){
            projectile.set(worldX, worldY, direction, true, this);
            gp.projectileList.add(projectile);
            projectileCounter = 0;
        }
    }

    public void monsterDamageReaction() {
        // when attacking, monster moves away from player
        actionLockCounter = 0;
        direction = gp.player.direction;

    }
}