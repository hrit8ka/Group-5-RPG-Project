//This is the parent class of all characters in the game. It contains all the attributes and methods that are common to all characters.
package Character;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.UtilityTool;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Character {

    GamePanel gp;// GamePanel object
    // declaring all the methods and attributes that are common to all characters
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1,
            attackRight2;
    public BufferedImage image, image2, image3;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48); // solidArea: the area that the player cannot walk through
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);// attackArea: the area that the player can attack
    public int solidAreaDefaultX, solidAreaDefaultY;// the default position of the solidArea
    public boolean collision = false;// collision: whether the player can walk through the character
    String dialogues[] = new String[20];// dialogues: the dialogues that the character can say
    // states
    public int worldX, worldY; // worldX:x coordinate of player... worldY:y coordinate of player
    public String direction = "down";// direction: the direction that the player is facing
    public int spriteNumber = 1;// spriteNumber: the number of sprites that the character has
    int dialogueIndex = 0;// dialogueIndex: the index of the dialogue that the character is saying
    public boolean collisionOn = false;// collisionOn: whether the collision is on
    public boolean invincible = false;// invincible: whether the character is invincible
    boolean attacking = false;// attacking: whether the character is attacking
    public boolean alive = true;// alive: whether the character is alive
    public boolean dying = false; // dying: whether the character is dying
    boolean hpBarOn = false;// hpBarOn: whether the hpBar is on
    public boolean onPath = false;// onPath: whether the character is on a path
    // counters
    public int spriteCounter = 0;// spriteCounter: the counter that controls the sprite animation of the
                                 // character
    public int actionLockCounter = 0;// actionLockCounter: the counter that controls the actionLock of the character
    public int invincibleCounter = 0;// invincibleCounter: the counter that checks if the character is invincible
    public int projectileCounter = 0;// projectileCounter: the counter that controls the projectile usage
    int dyingCounter = 0;// dyingCounter: the counter that controls the dying animation
    int hpBarCounter = 0; // hpBarCounter: the counter that controls the hpBar

    // attributes
    public String name;// name: the name of the character or object
    public int speed; // speed of the player

    // Character status
    public int maxLife;// maxLife: the maximum life of the character
    public int life;// life: the current life of the character
    public int level;// level: the level of the character
    public int maxMana;// maxMana: the maximum mana of the character
    public int mana;// mana: the current mana of the character
    public int strength;// strength: the strength of the character
    public int agility;// agility: the agility of the character
    public int attack;// attack: the attacking power of weapon or the character
    public int defense; // defense: the defending power of armor or the character
    public int xp;// xp: the current xp of the character
    public int nextLevelXP; // nextLevelXP: the xp that the character needs to level up
    public int gold;// gold: the number of gold coins that the character has
    public Character currentWeapon; // currentWeapon: the current weapon that the character is using
    public Character currentArmor;// currentArmor: the current armor that the character is using
    public Projectile projectile;// projectile: the projectile that the character is using

    // item attributes
    public ArrayList<Character> inventory = new ArrayList<>();// inventory of the player
    public final int maxInventorySize = 20;// max size of the inventory
    public int value; // value: the value of the item
    public int attackValue;// attackValue: the attacking power of the item
    public int defenseValue;// defenseValue: the defending power of the item
    public String description = "";// description: the description of the item
    public int usePrice;// usePrice: the price of the item

    // Type
    public int type;// type: the type of the character
    public final int playerType = 0; // playerType: the type of the player is 0
    public final int npcType = 1;// npcType: the type of the npc is 1
    public final int monsterType = 2;// monsterType: the type of the monster is 2
    public final int swordType = 3;// swordType: the type of the sword is 3
    public final int armorType = 4;// armorType: the type of the armor is 4
    public final int axeType = 5;// axeType: the type of the axe is 5
    public final int crystalType = 6;// crystalType: the type of the crystal is 6
    public final int potionType = 7;// potionType: the type of the potion is 7
    public final int pickUpType = 8;// pickUpType: the type of the pickUp is 8

    // constructor Character
    public Character(GamePanel gp) {
        this.gp = gp;// set gp
    }

    // set action method
    public void setAction() {
    }

    // monster damage reaction method
    public void monsterDamageReaction() {
        // override in monster class
    }

    // speak method that sets the current dialogue of the character/npc
    public void speak() {
        if (dialogues[dialogueIndex] == null) {// if the dialogue is null
            dialogueIndex = 0;// set dialogueIndex to 0
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex]; // set the current dialogue to the dialogue at the
                                                          // dialogueIndex
        dialogueIndex++; // increase the dialogueIndex

        switch (gp.player.direction) {// set the direction of the player
            case "up": // if the player is facing up
                direction = "down"; // set the direction of the character to down
                break;
            case "down":// if the player is facing down
                direction = "up";// set the direction of the character to up
                break;
            case "left": // if the player is facing left
                direction = "right";// set the direction of the character to right
                break;
            case "right":// if the player is facing right
                direction = "left";// set the direction of the character to left
                break;
        }
    }

    // use method
    public void use(Character Character) {
        // to be overridden in player class
    }

    // check drop method to check if items were dropped
    public void checkDrop() {

    }

    // drop item method for dropping items
    public void dropItem(Character droppedItem) {
        for (int i = 0; i < gp.obj[1].length; i++) {// loop through the obj array
            if (gp.obj[gp.currentMap][i] == null) {// if the obj is null
                gp.obj[gp.currentMap][i] = droppedItem;// set the obj to the droppedItem
                gp.obj[gp.currentMap][i].worldX = worldX; // dead monster's worldX
                gp.obj[gp.currentMap][i].worldY = worldY; // dead monster's worldY
                break;// break out of the loop
            }
        }
    }

    // get particle color method to get the color of the particle
    public Color getParticleColor() {
        Color color = null;
        return color;
    }

    // get particle size method to get the size of the particle
    public int getParticleSize() {
        int size = 0; // size of the particle 6 pixels
        return size;
    }

    // get particle speed method to get the speed of the particle
    public int getParticleSpeed() {
        int speed = 0; // speed of the particle 2 pixels per frame
        return speed;
    }

    // get particle max life method to get the time that the particle will last
    public int getParticleMaxLife() {
        int maxLife = 0; // max life of the particle 20 frames
        return maxLife;
    }

    // method to generate particles
    public void generateParticles(Character generator, Character target) {
        // get the particle color
        Color color = generator.getParticleColor();
        // get the particle size
        int size = generator.getParticleSize();
        // get the particle speed
        int speed = generator.getParticleSpeed();
        // get the particle max life
        int maxLife = generator.getParticleMaxLife();
        // instantiate the particle class and pass in the parameters
        Particle particle = new Particle(gp, target, color, size, speed, maxLife, -2, -1);
        Particle particle2 = new Particle(gp, target, color, size, speed, maxLife, 2, -1);
        Particle particle3 = new Particle(gp, target, color, size, speed, maxLife, -2, 1);
        Particle particle4 = new Particle(gp, target, color, size, speed, maxLife, 2, 1);
        gp.particleList.add(particle);// add particle to the particleList
        gp.particleList.add(particle2);// add particle2 to the particleList
        gp.particleList.add(particle3);// add particle3 to the particleList
        gp.particleList.add(particle4);// add particle4 to the particleList

    }

    public void checkCollision() {
        collisionOn = false;// set collisionOn to false
        gp.collisionChecker.checkTile(this);// call checkTile method
        gp.collisionChecker.checkObject(this, false);// check collision with object
        gp.collisionChecker.checkCharacter(this, gp.npc);// check collision with npc
        gp.collisionChecker.checkCharacter(this, gp.monster);// check collision with monster
        gp.collisionChecker.checkCharacter(this, gp.interactiveTile);// check collision with interactiveTile
        boolean contactPlayer = gp.collisionChecker.checkPlayer(this);// check collision with player
        // if the character is a monster and the player is attacking
        if (this.type == monsterType && contactPlayer == true) {
            // call damagePlayer method
            damagePlayer(attack);
        }

    }

    // update method
    public void update() {
        setAction();// call setAction method
        checkCollision();
        // if collision is false, player can move
        if (collisionOn == false) {
            switch (direction) {
                case "up": // if the direction is up
                    worldY -= speed;// move up
                    break;
                case "down":// if the direction is down
                    worldY += speed;// move down
                    break;
                case "left":// if the direction is left
                    worldX -= speed;// move left
                    break;
                case "right":// if the direction is right
                    worldX += speed;// move right
                    break;
            }
        }
        spriteCounter++;// increase spriteCounter
        if (spriteCounter > 24) {// if spriteCounter is greater than 12, change sprite
            if (spriteNumber == 1) {// if spriteNumber is 1
                spriteNumber = 2;// set spriteNumber to 2
            } else if (spriteNumber == 2) {// if spriteNumber is 2, set spriteNumber to 1
                spriteNumber = 1;
            }
            spriteCounter = 0;// set spriteCounter to 0
        }
        if (invincible == true) {// if the character is invincible
            invincibleCounter++;// increase invincibleCounter
            if (invincibleCounter > 40) {// if invincibleCounter is greater than 40
                invincible = false;// set invincible to false
                invincibleCounter = 0;// set invincibleCounter to 0
            }
        }
        if (projectileCounter < 30) {// if projectileCounter is less than 30
            projectileCounter++;// increase projectileCounter
        }

    }

    // method damagePlayer
    public void damagePlayer(int attack) {
        if (gp.player.invincible == false) {// if the player is not invincible
            // give damage to player
            gp.playSE(6);// play sound effect
            int damage = this.attack - gp.player.defense;// calculate damage
            if (damage < 0) {// if damage is less than 0, set damage to 0
                damage = 0;// set damage to 0
            }
            gp.player.life -= damage;// decrease player life by damage
            gp.player.invincible = true;// set player invincible to true
        }
    }

    // method draw
    public void draw(Graphics2D g2) {
        BufferedImage image = null;// set image to null
        int screenX = worldX - gp.player.worldX + gp.player.screenX;// calculate screenX
        int screenY = worldY - gp.player.worldY + gp.player.screenY;// calculate screenY
        // if the character is on the screen, draw the character
        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            switch (direction) {
                case "up": // if the direction is up
                    if (spriteNumber == 1) {// if spriteNumber is 1, set image to up1
                        image = up1;
                    }
                    if (spriteNumber == 2) {// if spriteNumber is 2, set image to up2
                        image = up2;
                    }
                    break;
                case "down":// if the direction is down
                    if (spriteNumber == 1) {// if spriteNumber is 1, set image to down1
                        image = down1;
                    }
                    if (spriteNumber == 2) {// if spriteNumber is 2, set image to down2
                        image = down2;
                    }
                    break;
                case "left":// if the direction is left
                    if (spriteNumber == 1) {// if spriteNumber is 1, set image to left1
                        image = left1;
                    }
                    if (spriteNumber == 2) {// if spriteNumber is 2, set image to left2
                        image = left2;
                    }
                    break;
                case "right":// if the direction is right
                    if (spriteNumber == 1) {// if spriteNumber is 1, set image to right1
                        image = right1;
                    }
                    if (spriteNumber == 2) {// if spriteNumber is 2, set image to right2
                        image = right2;
                    }
                    break;
            }
            // health bar for monster
            if (type == 2 && hpBarOn == true) {// if the character is a monster and hpBarOn is true
                // updating monster health bar
                double healthBar = (double) gp.tileSize / maxLife;// calculate health bar
                double healthBarValue = healthBar * life;// calculate health bar value

                // outline of health bar
                g2.setColor(new Color(35, 35, 35));// set color of health bar outline (black)
                g2.fillRect(screenX - 1, screenY - 11, gp.tileSize + 2, 6);// draw health bar outline
                // set color of health bar (red)
                g2.setColor(new Color(255, 0, 30));// set color of health bar (red)
                g2.fillRect(screenX, screenY - 10, (int) healthBarValue, 5);// draw health bar
                hpBarCounter++;// increase hpBarCounter
                // if hpBarCounter is greater than 600, set hpBarOn to false
                if (hpBarCounter > 600) {
                    hpBarCounter = 0;// set hpBarCounter to 0
                    hpBarOn = false;
                }
            }
            // health bar for boss
            if (invincible == true) {// if the character is invincible
                hpBarOn = true;// set hpBarOn to true
                hpBarCounter = 0;// set hpBarCounter to 0
                updateAlpha(g2, 0.4f);// set alpha to 0.4
            }
            if (dying == true) {// if the character is dying
                dyingAnimation(g2);// call dyingAnimation method
            }

            g2.drawImage(image, screenX, screenY, null);// draw the character

            // reset the composite
            updateAlpha(g2, 1.0f);
        }
    }

    // method to update alpha when "dying"
    public void dyingAnimation(Graphics2D g2) {
        dyingCounter++;// increment counter

        int i = 5;// counter interval
        if (dyingCounter <= i) {// first interval
            updateAlpha(g2, 0f);
        }
        if (dyingCounter > i && dyingCounter <= i * 2) {// second interval
            updateAlpha(g2, 1f);
        }
        if (dyingCounter > i * 2 && dyingCounter <= i * 3) {// third interval
            updateAlpha(g2, 0f);
        }
        if (dyingCounter > i * 3 && dyingCounter <= i * 4) {// fourth interval
            updateAlpha(g2, 1f);
        }
        if (dyingCounter > i * 4 && dyingCounter <= i * 5) {// fifth interval
            updateAlpha(g2, 0f);
        }
        if (dyingCounter > i * 5 && dyingCounter <= i * 6) {// sixth interval
            updateAlpha(g2, 1f);
        }
        if (dyingCounter > i * 6 && dyingCounter <= i * 7) {// seventh interval
            updateAlpha(g2, 0f);
        }
        if (dyingCounter > i * 7 && dyingCounter <= i * 8) {//
            updateAlpha(g2, 1f);
        }
        if (dyingCounter > i * 8) {// reset counter and set dying to false
            alive = false;
        }
    }

    // method to update alpha
    public void updateAlpha(Graphics2D g2, float alpha) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));// set alpha
    }

    // method to set up images
    public BufferedImage setUp(String imagePath, int width, int height) {
        UtilityTool tool = new UtilityTool();// create new UtilityTool object
        BufferedImage image = null;// create new BufferedImage object
        try {// try to read image
            image = ImageIO.read(new File(imagePath + ".png"));// read image
            image = tool.scaleImage(image, width, height);// scale image

        } catch (IOException e) {// catch error
            e.printStackTrace();// print error
        }
        return image;// return image
    }

    // method for cat (healer) to heal player
    public void catHeal() {
        // if player life is less than max life, heal player when 'enter' is pressed
        if (gp.keyH.enterPressed && gp.player.life < gp.player.maxLife) {
            // heal the player fully
            gp.player.life = gp.player.maxLife;
            // set enterPressed to false so that the player can't heal again
            gp.keyH.enterPressed = false;
            // if player is healed, display text saying so
            if (gp.player.life == gp.player.maxLife) {
                gp.playSE(7);// play sound effect
                gp.ui.addMessage("You have been healed!");// display message
            }
        } else {
            gp.playSE(11);// play sound effect
            gp.ui.addMessage("Meoww!");// display message
        }
    }

    public void searchPath(int goalCol, int goalRow) {
        int startCol = (worldX + solidArea.x) / gp.tileSize;
        int startRow = (worldY + solidArea.y) / gp.tileSize;

        gp.pathFinder.setNodes(startCol, startRow, goalCol, goalRow);

        if (gp.pathFinder.search() == true) {

            // next world position
            int nextX = gp.pathFinder.pathList.get(0).col * gp.tileSize;
            int nextY = gp.pathFinder.pathList.get(0).row * gp.tileSize;
            // character's solidArea position
            int characterLeftX = worldX + solidArea.x;
            int characterRightX = worldX + solidArea.x + solidArea.width;
            int characterTopY = worldY + solidArea.y;
            int characterBottomY = worldY + solidArea.y + solidArea.height;

            if (characterTopY > nextY && characterLeftX >= nextX && characterRightX < nextX + gp.tileSize) {
                // move up
                direction = "up";
            } else if (characterTopY < nextY && characterLeftX >= nextX && characterRightX < nextX + gp.tileSize) {
                // move down
                direction = "down";
            } else if (characterTopY >= nextY && characterBottomY < nextY + gp.tileSize) {
                // left side
                if (characterLeftX > nextX) {
                    // move left
                    direction = "left";
                }
                // right side
                else if (characterRightX < nextX) {
                    // move right
                    direction = "right";
                }
            } else if (characterTopY > nextY && characterLeftX > nextX) {
                // up or left
                direction = "up";
                checkCollision();
                if (collisionOn == true) {
                    direction = "left";
                }
            } else if (characterTopY > nextY && characterLeftX < nextX) {
                // up or right
                direction = "up";
                checkCollision();
                if (collisionOn == true) {
                    direction = "right";
                }
            } else if (characterTopY < nextY && characterLeftX > nextX) {
                // down or left
                direction = "down";
                checkCollision();
                if (collisionOn == true) {
                    direction = "left";
                }
            } else if (characterTopY < nextY && characterLeftX < nextX) {
                // down or right
                direction = "down";
                checkCollision();
                if (collisionOn == true) {
                    direction = "right";
                }
            }
        }

        /*
         * int nextCol = gp.pathFinder.pathList.get(0).col;
         * int nextRow = gp.pathFinder.pathList.get(0).row;
         * if(nextCol == goalCol && nextRow == goalRow){
         * onPath = false;
         * }
         */

    }
}
