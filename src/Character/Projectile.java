package Character;

import Main.GamePanel;

// Projectile class for the projectiles
public class Projectile extends Character {
    Character user;// the user of the projectile

    // Constructor Projectile
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

        if (user == gp.player) {// if user is player
            // check collision with monsters
            int monsterIndex = gp.collisionChecker.checkCharacter(this, gp.monster);// check collision with monsters
            if (monsterIndex != 999) {// if collision with monster
                gp.player.damagedMonster(monsterIndex, attack); // damage monster
                alive = false;// set alive to false
            }
        }
        if (user != gp.player) {// if user is not player
            // check collision with player
            boolean contactWithPlayer = gp.collisionChecker.checkPlayer(this);// check collision with player
            if (gp.player.invincible == false && contactWithPlayer == true) {// if player is not invincible and there is
                                                                             // contact with player
                if (contactWithPlayer) {// if contact with player
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

    // method to check if the user has enough mana to use the projectile
    public boolean haveMana(Character user) {
        boolean haveMana = false;// boolean to check if the user has enough mana
        return haveMana;// return the boolean
    }

    // method to subtract mana from the user
    public void subtractMana(Character user) {
        // Override this method in the subclasses
    }

}
