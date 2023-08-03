package Character;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.UtilityTool;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Character {

    GamePanel gp;
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
    boolean alive = true;
    boolean dying = false;
    // counters
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    int dyingCounter = 0;
    // attributes
    public int type; // 0=Player, 1=NPC, 2=Monster..
    public String name;
    public int speed; // speed of the player
    // Character status
    public int maxLife;
    public int life;

    public Character(GamePanel gp) {
        this.gp = gp;
    }

    public void setAction() {
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

    public void update() {
        setAction();
        collisionOn = false;
        gp.collisionChecker.checkTile(this);
        gp.collisionChecker.checkObject(this, false);
        gp.collisionChecker.checkCharacter(this, gp.npc);
        gp.collisionChecker.checkCharacter(this, gp.monster);
        boolean contactPlayer = gp.collisionChecker.checkPlayer(this);

        if (this.type == 2 && contactPlayer == true) {
            if (gp.player.invincible == false) {
                // give damage to player
                gp.player.life -= 1;
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
            if (invincible == true) {
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
            }
            if(dying== true){
                dyingAnimation(g2);
            }

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

            // reset the composite
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        }
    }
    public void dyingAnimation(Graphics2D g2){
        dyingCounter++;
        if(dyingCounter <=5){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
        }
        if(dyingCounter >5 && dyingCounter <=10){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
        if(dyingCounter >10 && dyingCounter <=15){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
        }
        if(dyingCounter >15 && dyingCounter <=20){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
        if(dyingCounter >20 && dyingCounter <=25){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
        }
        if(dyingCounter >25 && dyingCounter <=30){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
        if(dyingCounter >30 && dyingCounter <=35){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
        }
        if(dyingCounter >35 && dyingCounter <=40){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
        if(dyingCounter >40){
            dying = false;
            //dyingCounter = 0;
            alive = false;
        }
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
}
