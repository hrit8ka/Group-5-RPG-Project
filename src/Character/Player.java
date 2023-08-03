//Using GIF
package Character;

import java.awt.AlphaComposite;
//import java.awt.Color;
//import java.awt.Font;
import java.awt.Graphics2D;
//import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;

//import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.KeyHandler;
//import Main.UtilityTool;

public class Player extends Character {

    // GamePanel gp;
    KeyHandler keyH;

    // screenX and screenY are the coordinates of the player on the screen
    public final int screenX;
    public final int screenY;
    // public int hasKey = 0;

    // private BufferedImage upImage; // Image for up movement
    // private BufferedImage downImage; // Image for down movement
    // private BufferedImage leftImage; // Image for left movement
    // private BufferedImage rightImage; // Image for right movement
    private BufferedImage up1;
    private BufferedImage up2;
    public BufferedImage down1;
    private BufferedImage down2;
    private BufferedImage left1;
    private BufferedImage left2;
    private BufferedImage right1;
    private BufferedImage right2;

    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);
        // this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImages(); // get the images of the player
        getPlayerAttackImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";

        // Player status
        maxLife = 10;
        life = maxLife;
    }

    public void getPlayerImages() {

        // Load images for up movement
        /*
         * upImage = ImageIO.read(new File("./src/player/up.gif"));
         * 
         * // Load images for down movement
         * downImage = ImageIO.read(new File("./src/player/down.gif"));
         * 
         * // Load images for left movement
         * leftImage = ImageIO.read(new File("./src/player/left.gif"));
         * 
         * // Load images for right movement
         * rightImage = ImageIO.read(new File("./src/player/right.gif"));
         */

        /*
         * upImage=setUp("/player/up");
         * downImage=setUp("/player/down");
         * leftImage=setUp("/player/left");
         * rightImage=setUp("/player/right");
         */

        up1 = setUp("./src/player/boy_up_1", gp.tileSize, gp.tileSize);
        up2 = setUp("./src/player/boy_up_2", gp.tileSize, gp.tileSize);
        down1 = setUp("./src/player/boy_down_1", gp.tileSize, gp.tileSize);
        down2 = setUp("./src/player/boy_down_2", gp.tileSize, gp.tileSize);
        left1 = setUp("./src/player/boy_left_1", gp.tileSize, gp.tileSize);
        left2 = setUp("./src/player/boy_left_2", gp.tileSize, gp.tileSize);
        right1 = setUp("./src/player/boy_right_1", gp.tileSize, gp.tileSize);
        right2 = setUp("./src/player/boy_right_2", gp.tileSize, gp.tileSize);

    }

    public void getPlayerAttackImage() {
        attackUp1 = setUp("./src/player/boy_attack_up_1", gp.tileSize, gp.tileSize * 2);
        attackUp2 = setUp("./src/player/boy_attack_up_2", gp.tileSize, gp.tileSize * 2);
        attackDown1 = setUp("./src/player/boy_attack_down_1", gp.tileSize, gp.tileSize * 2);
        attackDown2 = setUp("./src/player/boy_attack_down_2", gp.tileSize, gp.tileSize * 2);
        attackLeft1 = setUp("./src/player/boy_attack_left_1", gp.tileSize * 2, gp.tileSize);
        attackLeft2 = setUp("./src/player/boy_attack_left_2", gp.tileSize * 2, gp.tileSize);
        attackRight1 = setUp("./src/player/boy_attack_right_1", gp.tileSize * 2, gp.tileSize);
        attackRight2 = setUp("./src/player/boy_attack_right_2", gp.tileSize * 2, gp.tileSize);
    }

    public void update() {

        if (attacking == true) {
            attacking();
        }
        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true
                || keyH.rightPressed == true || keyH.enterPressed == true) {

            if (keyH.upPressed == true) {
                direction = "up";
                // worldY -= speed;
            }
            if (keyH.downPressed == true) {
                direction = "down";
                // worldY += speed;
            }
            if (keyH.leftPressed == true) {
                direction = "left";
                // worldX -= speed;
            }
            if (keyH.rightPressed == true) {
                direction = "right";
                // worldX += speed;
            }

            // check collision
            collisionOn = false;
            gp.collisionChecker.checkTile(this);

            // check collision with objects
            int objIndex = gp.collisionChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // check npc collision
            int npcIndex = gp.collisionChecker.checkCharacter(this, gp.npc);
            interactWithNPC(npcIndex);

            // check monster collision
            int monsterIndex = gp.collisionChecker.checkCharacter(this, gp.monster);
            contactMonster(monsterIndex);
            // check event
            gp.eventHandler.checkEvent();
            // gp.keyH.enterPressed = false;
            // if collision is false, player can move
            if (collisionOn == false && keyH.enterPressed == false) {
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
            gp.keyH.enterPressed = false;
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNumber == 1) {
                    spriteNumber = 2;
                } else if (spriteNumber == 2) {
                    spriteNumber = 1;
                }
                spriteCounter = 0;
            }
        }
        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void attacking() {
        spriteCounter++;
        if (spriteCounter <= 5) {
            spriteNumber = 1;
        }
        if (spriteCounter > 5 && spriteCounter <= 25) {
            spriteNumber = 2;
        }
        if (spriteCounter > 25) {
            spriteNumber = 1;
            spriteCounter = 0;
            attacking = false;
        }

    }

    public void pickUpObject(int i) {
        if (i != 999) {

        }

    }

    public void interactWithNPC(int npcIndex) {
        if (gp.keyH.enterPressed == true) {
            if (npcIndex != 999) {
                gp.gameState = gp.dialogueState;
                gp.npc[npcIndex].speak();
            }
            // gp.keyH.enterPressed = false;
            else {
                attacking = true;
            }
        }

    }

    public void contactMonster(int monsterIndex) {
        if (monsterIndex != 999) {
            if (invincible == false) {
                life -= 1;
                invincible = true;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
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
        if (invincible == true) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));

        }

        g2.drawImage(image, tempScreenX, tempScreenY, null);

        // reset the composite
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));

        // debug
        // g2.setFont(new Font("Arial", Font.PLAIN, 20));
        // g2.setColor(Color.WHITE);
        // g2.drawString("Invincible: " + invincibleCounter, 10, 400);
    }
}