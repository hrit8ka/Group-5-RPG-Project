//This is the parent class of all characters in the game. It contains all the attributes and methods that are common to all characters.
package Character;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

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
    public Character attacker;
    // states
    public int worldX, worldY; // worldX:x coordinate of player... worldY:y coordinate of player
    public String direction = "down";// direction: the direction that the player is facing
    public int spriteNumber = 1;// spriteNumber: the number of sprites that the character has
    int dialogueIndex = 0;// dialogueIndex: the index of the dialogue that the character is saying
    public boolean collisionOn = false;// collisionOn: whether the collision is on
    public boolean invincible = false;// invincible: whether the character is invincible
    public boolean attacking = false;// attacking: whether the character is attacking
    public boolean alive = true;// alive: whether the character is alive
    public boolean dying = false; // dying: whether the character is dying
    boolean hpBarOn = false;// hpBarOn: whether the hpBar is on
    public boolean onPath = false;// onPath: whether the character is on a path
    public boolean knockBack = false;// knockBack: whether the character is knocked back
    public String knockBackDirection;
    // counters
    public int spriteCounter = 0;// the counter that controls the sprite animation of character
    public int actionLockCounter = 0;// the counter that controls the actionLock of the character
    public int invincibleCounter = 0;// the counter that checks if the character is invincible
    public int projectileCounter = 0;// the counter that controls the projectile usage
    int dyingCounter = 0;// the counter that controls the dying animation
    int hpBarCounter = 0; // the counter that controls the hpBar
    int knockBackCounter = 0;// the counter that controls the knockBack
    // attributes
    public String name;// name: the name of the character or object
    public int defaultSpeed;// defaultSpeed: the default speed of the character
    public int speed; // speed of the player

    // Character attributes
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
    public Character currentLight; // currentLight: the candle that the character is using
    public Projectile projectile;// projectile: the projectile that the character is using

    // item attributes
    public ArrayList<Character> inventory = new ArrayList<>();// inventory of the player
    public final int maxInventorySize = 20;// max size of the inventory
    public int value; // value: the value of the item
    public int attackValue;// attackValue: the attacking power of the item
    public int defenseValue;// defenseValue: the defending power of the item
    public String description = "";// description: the description of the item
    public int usePrice;// usePrice: the price of the item
    public int price; // price: the price of the item
    public int knockBackPower = 0;// knockBackPower: the knockBack power of the item
    public boolean stackable = false;// stackable: whether the item is stackable
    public int amount = 1;
    public int lightRadius = 0;// lightRadius: the light radius of the item

    // Type
    public int type;// type: the type of the character
    public final int playerType = 0; // playerType: the type of the player is 0
    public final int npcType = 1;// npcType: the type of the npc is 1
    public final int monsterType = 2;// monsterType: the type of the monster is 2
    public final int swordType = 3;// swordType: the type of the sword is 3
    public final int armorType = 4;// armorType: the type of the armor is 4
    public final int axeType = 5;// axeType: the type of the axe is 5
    public final int crystalType = 6;// crystalType: the type of the crystal is 6
    public final int consumableType = 7;// consumableType: the type of the consumable is 7
    public final int pickUpType = 8;// pickUpType: the type of the pickUp is 8
    public final int obstacleType = 9;// obstacleType: the type of the obstacle is 9
    public final int lightType = 10;// lightType: the type of the light is 10

    // constructor Character
    public Character(GamePanel gp) {
        this.gp = gp;// set gp
    }

    // getters for the x and y coordinates of the character
    public int getLeftX() {
        return worldX + solidArea.x;
    }

    public int getRightX() {
        return worldX + solidArea.x + solidArea.width;
    }

    public int getTopY() {
        return worldY + solidArea.y;
    }

    public int getBottomY() {
        return worldY + solidArea.y + solidArea.height;
    }

    public int getCol() {
        return (worldX + solidArea.x) / gp.tileSize;
    }

    public int getRow() {
        return (worldY + solidArea.y) / gp.tileSize;
    }

    public int getXDistance(Character target) {
        int xDistance = Math.abs(worldX - target.worldX);
        return xDistance;
    }

    public int getYDistance(Character target) {
        int yDistance = Math.abs(worldY - target.worldY);
        return yDistance;
    }

    public int getTileDistance(Character target) {
        int tileDistance = (getXDistance(target) + getYDistance(target)) / gp.tileSize;
        return tileDistance;
    }

    public int getGoalCol(Character target) {
        int goalCol = (target.worldX + target.solidArea.x) / gp.tileSize;
        return goalCol;
    }

    public int getGoalRow(Character target) {
        int goalRow = (target.worldY + target.solidArea.y) / gp.tileSize;
        return goalRow;
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

    public void interact() {

    }

    // use method
    public boolean use(Character Character) {
        // to be overridden in player class
        return false;
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

        if (knockBack == true) {
            checkCollision();

            if (collisionOn == true) {
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            } else if (collisionOn == false) {
                switch (knockBackDirection) {
                    case "up":
                        worldY += speed;
                        break;
                    case "down":
                        worldY -= speed;
                        break;
                    case "left":
                        worldX += speed;
                        break;
                    case "right":
                        worldX -= speed;
                        break;
                }
            }
            knockBackCounter++;
            if (knockBackCounter == 10) {
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }

        } else if (attacking == true) {
            attacking();
        } else {
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

    public void checkAttack(int rate, int straight, int horizontal) {
        boolean targetInRange = false;
        int xDistance = getXDistance(gp.player);
        int yDistance = getYDistance(gp.player);

        switch (direction) {
            case "up":
                if (gp.player.worldY < worldY && yDistance < straight && xDistance < horizontal) {
                    targetInRange = true;
                }
                break;
            case "down":
                if (gp.player.worldY > worldY && yDistance < straight && xDistance < horizontal) {
                    targetInRange = true;
                }
                break;
            case "left":
                if (gp.player.worldX < worldX && xDistance < straight && yDistance < horizontal) {
                    targetInRange = true;
                }
                break;
            case "right":
                if (gp.player.worldX > worldX && xDistance < straight && yDistance < horizontal) {
                    targetInRange = true;
                }
                break;
        }
        if (targetInRange == true) {
            int i = new Random().nextInt(rate) + 1; // pick a random number between 1 and 100
            if (i == 0) {
                attacking = true;
                //monster shoot projectile
                if (type == monsterType) {
                    projectile.set(worldX, worldY, direction, true, this);
                    for (int j = 0; j < gp.projectile[1].length; j++) {
                        if (gp.projectile[1][j] == null) {
                            gp.projectile[1][j] = projectile;
                            break;
                        }
                    }
                }
                spriteNumber = 1;
                spriteCounter = 0;

            }
        }

    }

    public void checkShoot(int rate, int shotInterval) {
        // check if player is in range to shoot projectile
        int i = new Random().nextInt(rate) + 1; // pick a random number between 1 and 100
        if (i == 0 && projectile.alive == false && projectileCounter == shotInterval) {
            projectile.set(worldX, worldY, direction, true, this);
            for (int j = 0; j < gp.projectile[1].length; j++) {
                if (gp.projectile[1][j] == null) {
                    gp.projectile[1][j] = projectile;
                    break;
                }
            }
            projectileCounter = 0;// set projectile counter to 0
        }
    }

    public void checkStartChasing(Character target, int distance, int rate) {
        if (getTileDistance(target) <= distance) {
            int i = new Random().nextInt(rate);
            if (i == 0) {
                onPath = true;
            }
        }
    }

    public void checkStopChasing(Character target, int distance, int rate) {
        if (getTileDistance(target) > distance) {
            int i = new Random().nextInt(rate);
            if (i == 0) {
                onPath = false;
            }
        }
    }

    public void getRandomDirection() {
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

    // method attack
    public void attacking() {
        spriteCounter++;// increment the sprite counter
        if (spriteCounter <= 5) {// if the sprite counter is less than or equal to 5
            spriteNumber = 1;// set the sprite number to 1
        }
        if (spriteCounter > 5 && spriteCounter <= 25) {// if the sprite counter is > 5 and <= equal to 25
            spriteNumber = 2;// set the sprite number to 2
            int currentWorldX = worldX;// save the current worldX
            int currentWorldY = worldY;// save the current worldY
            int solidAreaWidth = solidArea.width;// save the current solidAreaWidth
            int solidAreaHeight = solidArea.height;// save the current solidAreaHeight
            // adjust player's worldX and worldY for the attackArea
            switch (direction) {
                case "up":
                    worldY -= attackArea.height;// if the player is attacking up, attack area is up
                    break;
                case "down":
                    worldY += attackArea.height;// if the player is attacking down, attack area is down
                    break;
                case "left":
                    worldX -= attackArea.width;// if the player is attacking left, attack area is left
                    break;
                case "right":
                    worldX += attackArea.width;// if the player is attacking right, attack area is right
                    break;
            }
            // attack area becomes solid area
            solidArea.width = attackArea.width;// set the solid area width to the attack area width
            solidArea.height = attackArea.height;// set the solid area height to the attack area height
            if (type == monsterType) {
                if (gp.collisionChecker.checkPlayer(this) == true) {
                    damagePlayer(attack);
                }
            } else {
                // check collision with monster with the attack area
                int monsterIndex = gp.collisionChecker.checkCharacter(this, gp.monster);// check collision with monster
                gp.player.damagedMonster(monsterIndex, this, attack, currentWeapon.knockBackPower);// call the method to
                                                                                                   // damage the monster
                // check collision with interactive tiles
                int interactiveTileIndex = gp.collisionChecker.checkCharacter(this, gp.interactiveTile);
                gp.player.damageInteractiveTile(interactiveTileIndex);
                int projectileIndex = gp.collisionChecker.checkCharacter(this, gp.projectile);
                gp.player.damageProjectile(projectileIndex);
            }

            // after checking, reset the worldX, worldY, solidAreaWidth and solidAreaHeight
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;

        }
        if (spriteCounter > 25) {// if the sprite counter is greater than 25
            spriteNumber = 1;// set the sprite number to 1
            spriteCounter = 0;// reset the sprite counter
            attacking = false;// set attacking to false
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

    public void knockBack(Character target, Character attacker, int knockBackPower) {
        this.attacker = attacker;
        target.knockBackDirection = attacker.direction;
        target.speed += knockBackPower;
        target.knockBack = true;

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
                    int tempScreenX = screenX;
                    int tempScreenY = screenY;
                    switch (direction) {
                        case "up":
                            if (attacking == false) {
                                if (spriteNumber == 1) {
                                    image = up1;
                                }
                                if (spriteNumber == 2) {
                                    image = up2;
                                }
                            }
                            if (attacking == true) {
                                tempScreenY = screenY - gp.tileSize;
                                if (spriteNumber == 1) {
                                    image = attackUp1;
                                }
                                if (spriteNumber == 2) {
                                    image = attackUp2;
                                }
                            }
                            break;
                        case "down":
                            if (attacking == false) {
                                if (spriteNumber == 1) {
                                    image = down1;
                                }
                                if (spriteNumber == 2) {
                                    image = down2;
                                }
                            }
                            if (attacking == true) {
                                if (spriteNumber == 1) {
                                    image = attackDown1;
                                }
                                if (spriteNumber == 2) {
                                    image = attackDown2;
                                }
                            }
                            break;
                        case "left":
                            if (attacking == false) {
                                if (spriteNumber == 1) {
                                    image = left1;
                                }
                                if (spriteNumber == 2) {
                                    image = left2;
                                }
                            }
                            if (attacking == true) {
                                tempScreenX = screenX - gp.tileSize;
                                if (spriteNumber == 1) {
                                    image = attackLeft1;
                                }
                                if (spriteNumber == 2) {
                                    image = attackLeft2;
                                }
                            }
                            break;
                        case "right":
                            if (attacking == false) {
                                if (spriteNumber == 1) {
                                    image = right1;
                                }
                                if (spriteNumber == 2) {
                                    image = right2;
                                }
                            }
                            if (attacking == true) {
                                if (spriteNumber == 1) {
                                    image = attackRight1;
                                }
                                if (spriteNumber == 2) {
                                    image = attackRight2;
                                }
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

            g2.drawImage(image, tempScreenX, tempScreenY, null);// draw the character

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

    public int getDetected(Character user, Character target[][], String targetName) {
        int index = 999;
        // check the surrounding area
        int nextWorldX = user.getLeftX();
        int nextWorldY = user.getTopY();

        switch (user.direction) {
            case "up":
                nextWorldY -= user.getTopY() - 1;
                break;
            case "down":
                nextWorldY += user.getBottomY() + 1;
                break;
            case "left":
                nextWorldX -= user.getLeftX() - 1;
                break;
            case "right":
                nextWorldX += user.getRightX() + 1;
                break;
        }
        int col = nextWorldX / gp.tileSize;
        int row = nextWorldY / gp.tileSize;

        for (int i = 0; i < target[1].length; i++) {
            if (target[gp.currentMap][i] != null) {
                if (target[gp.currentMap][i].getCol() == col && target[gp.currentMap][i].getRow() == row
                        && target[gp.currentMap][i].name.equals(targetName)) {
                            System.out.print("detected");
                    index = i;
                    break;
                }
            }
        }
        return index;
    }
}
