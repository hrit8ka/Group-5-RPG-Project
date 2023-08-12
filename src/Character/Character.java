//This is the parent class of all characters in the game. It contains all the attributes and methods that are common to all characters.
package Character;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.UtilityTool;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
//import java.awt.event.KeyEvent;

public class Character {

    GamePanel gp;// GamePanel object
    // declaring all the methods and attributes that are common to all characters
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1,
            attackRight2;
    public BufferedImage image, image2, image3;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;
    String dialogues[] = new String[20];
    // states
    public int worldX, worldY; // worldX:x coordinate of player... worldY:y coordinate of player
    public String direction = "down";
    public int spriteNumber = 1;
    int dialogueIndex = 0;
    public boolean collisionOn = false;
    public boolean invincible = false;
    boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    boolean hpBarOn = false;
    // counters
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    public int projectileCounter = 0;
    int dyingCounter = 0;
    int hpBarCounter = 0;
    // attributes
    // public int type; // 0=Player, 1=NPC, 2=Monster..
    public String name;
    public int speed; // speed of the player
    // Character status
    public int maxLife;
    public int life;
    public int maxMana;
    public int mana;
    public int level;
    public int strength;
    public int agility;
    public int attack;
    public int defense;
    public int xp;
    public int nextLevelXP;
    public int gold;
    public Character currentWeapon;
    public Character currentArmor;
    public Projectile projectile;

    // item attributes
    public int attackValue;
    public int defenseValue;
    public String description = "";
    public int usePrice;

    // Type
    public int type;
    public final int playerType = 0;
    public final int npcType = 1;
    public final int monsterType = 2;
    public final int swordType = 3;
    public final int armorType = 4;
    public final int axeType = 5;
    public final int crystalType = 6;
    public final int potionType = 7;

    // constructor Character
    public Character(GamePanel gp) {
        this.gp = gp;
    }

    // set action method
    public void setAction() {
    }

    public void monsterDamageReaction() {

    }

    public void speak() {
        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch (gp.player.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }

    public void use(Character Character) {
        // to be overridden in player class
    }

    public void update() {
        setAction();
        collisionOn = false;
        gp.collisionChecker.checkTile(this);
        gp.collisionChecker.checkObject(this, false);
        gp.collisionChecker.checkCharacter(this, gp.npc);
        gp.collisionChecker.checkCharacter(this, gp.monster);
        boolean contactPlayer = gp.collisionChecker.checkPlayer(this);

        if (this.type == monsterType && contactPlayer == true) {
            if (gp.player.invincible == false) {
                // give damage to player
                gp.playSE(6);
                int damage = this.attack - gp.player.defense;
                if (damage < 0) {
                    damage = 0;
                }
                gp.player.life -= damage;
                gp.player.invincible = true;
            }
        }

        // if collision is false, player can move
        if (collisionOn == false) {
            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }
        spriteCounter++;
        if (spriteCounter > 12) {
            if (spriteNumber == 1) {
                spriteNumber = 2;
            } else if (spriteNumber == 2) {
                spriteNumber = 1;
            }
            spriteCounter = 0;
        }
        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 40) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            switch (direction) {
                case "up":
                    if (spriteNumber == 1) {
                        image = up1;
                    }
                    if (spriteNumber == 2) {
                        image = up2;
                    }
                    break;
                case "down":
                    if (spriteNumber == 1) {
                        image = down1;
                    }
                    if (spriteNumber == 2) {
                        image = down2;
                    }
                    break;
                case "left":
                    if (spriteNumber == 1) {
                        image = left1;
                    }
                    if (spriteNumber == 2) {
                        image = left2;
                    }
                    break;
                case "right":
                    if (spriteNumber == 1) {
                        image = right1;
                    }
                    if (spriteNumber == 2) {
                        image = right2;
                    }
                    break;
            }
            // health bar for monster
            if (type == 2 && hpBarOn == true) {
                // updating monster health bar
                double healthBar = (double) gp.tileSize / maxLife;
                double healthBarValue = healthBar * life;

                // outline of health bar
                g2.setColor(new Color(35, 35, 35));
                g2.fillRect(screenX - 1, screenY - 11, gp.tileSize + 2, 6);
                // set color of health bar (red)
                g2.setColor(new Color(255, 0, 30));
                g2.fillRect(screenX, screenY - 10, (int) healthBarValue, 5);

                hpBarCounter++;

                if (hpBarCounter > 600) {
                    hpBarCounter = 0;
                    hpBarOn = false;
                }
            }

            if (invincible == true) {
                hpBarOn = true;
                hpBarCounter = 0;
                updateAlpha(g2, 0.4f);
            }
            if (dying == true) {
                dyingAnimation(g2);
            }

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

            // reset the composite
            updateAlpha(g2, 1.0f);
        }
    }

    //method to update alpha when "dying"
    public void dyingAnimation(Graphics2D g2) {
        dyingCounter++;//increment counter

        int i = 5;//counter interval
        if (dyingCounter <= i) {//first interval
            updateAlpha(g2, 0f);
        }
        if (dyingCounter > i && dyingCounter <= i * 2) {//second interval 
            updateAlpha(g2, 1f);
        }
        if (dyingCounter > i * 2 && dyingCounter <= i * 3) {//third interval 
            updateAlpha(g2, 0f);
        }
        if (dyingCounter > i * 3 && dyingCounter <= i * 4) {//fourth interval
            updateAlpha(g2, 1f);
        }
        if (dyingCounter > i * 4 && dyingCounter <= i * 5) {//fifth interval
            updateAlpha(g2, 0f);
        }
        if (dyingCounter > i * 5 && dyingCounter <= i * 6) {//sixth interval
            updateAlpha(g2, 1f);
        }
        if (dyingCounter > i * 6 && dyingCounter <= i * 7) {//seventh interval
            updateAlpha(g2, 0f);
        }
        if (dyingCounter > i * 7 && dyingCounter <= i * 8) {//
            updateAlpha(g2, 1f);
        }
        if (dyingCounter > i * 8) {//reset counter and set dying to false
            //dying = false;
            // dyingCounter = 0;
            alive = false;
        }
    }

    public void updateAlpha(Graphics2D g2, float alpha) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
    }

    public BufferedImage setUp(String imagePath, int width, int height) {
        UtilityTool tool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(imagePath + ".png"));
            image = tool.scaleImage(image, width, height);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void catHeal() {
        // if player life is less than max life, heal player when 'enter' is pressed
        if (gp.keyH.enterPressed && gp.player.life < gp.player.maxLife) {
            // heal the player fully
            gp.player.life = gp.player.maxLife;
            // set enterPressed to false so that the player can't heal again
            gp.keyH.enterPressed = false;
            // if player is healed, display text saying so
            if (gp.player.life == gp.player.maxLife) {
                gp.ui.addMessage("You have been healed!");
            }
        }
        else{
            //display message "You are already at full health!"
            gp.ui.addMessage("Meoww!");
        }
    }
}
