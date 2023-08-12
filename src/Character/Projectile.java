package Character;

import Main.GamePanel;

// Projectile class for the projectiles
public class Projectile extends Character {
    Character user;

    // Constructor
    public Projectile(GamePanel gp) {
        super(gp);// call super constructor to set gp

    }

    // Set projectile's characteristics
    public void set(int worldX, int worldY, String direction, boolean alive, Character user) {
        this.worldX = worldX; // set worldX
        this.worldY = worldY;// set worldY
        this.direction = direction;// set direction
        this.alive = alive;// set alive
        this.user = user;// set user
        this.life = this.maxLife; // reset life to max
    }

    // Update projectile
    public void update() {

        if (user == gp.player) {
            // check collision with monsters
            int monsterIndex = gp.collisionChecker.checkCharacter(this, gp.monster);
            if (monsterIndex != 999) {
                gp.player.damagedMonster(monsterIndex, attack); // damage monster
                alive = false;// set alive to false
            }
        }
        if (user != gp.player) {
            // check collision with player
            boolean contactWithPlayer = gp.collisionChecker.checkPlayer(this);
            if(gp.player.invincible == false && contactWithPlayer == true){
                if (contactWithPlayer) {
                    gp.player.damagePlayer(attack); // damage player
                    alive = false;// set alive to false
                }
            }
        }
        // if alive, move
        switch (direction) { // move in direction
            case "up":
                worldY -= speed;// move up
                break;
            case "down":
                worldY += speed;// move down
                break;
            case "left":
                worldX -= speed;// move left
                break;
            case "right":
                worldX += speed;// move right
                break;
        }
        life--; // decrease life
        if (life <= 0) { // if life is 0, set alive to false
            alive = false;
        }
        // sprite animation
        spriteCounter++;
        if (spriteCounter >= 12) {// if 12 frames have passed, change sprite
            if (spriteNumber == 1) { // if sprite 1, change to sprite 2
                spriteNumber = 2;
            } else if (spriteNumber == 2) {// if sprite 2, change to sprite 1
                spriteNumber = 1;
            }
            spriteCounter = 0; // reset sprite counter
        }
    }

}
