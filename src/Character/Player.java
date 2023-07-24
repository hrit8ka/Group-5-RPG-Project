//Using GIF
package Character;

import java.awt.Graphics2D;
//import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.KeyHandler;
import Main.UtilityTool;

public class Player extends Character {

    GamePanel gp;
    KeyHandler keyH;

    // screenX and screenY are the coordinates of the player on the screen
    public final int screenX;
    public final int screenY;
    public int hasKey = 0;

    private BufferedImage upImage; // Image for up movement
    private BufferedImage downImage; // Image for down movement
    private BufferedImage leftImage; // Image for left movement
    private BufferedImage rightImage; // Image for right movement

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 2;
        solidArea.y = 4;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 14;
        solidArea.height = 14;

        setDefaultValues();
        getPlayerImages(); // get the images of the player
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImages() {
    
            // Load images for up movement
            /*upImage = ImageIO.read(new File("./src/player/up.gif"));

            // Load images for down movement
            downImage = ImageIO.read(new File("./src/player/down.gif"));

            // Load images for left movement
            leftImage = ImageIO.read(new File("./src/player/left.gif"));

            // Load images for right movement
            rightImage = ImageIO.read(new File("./src/player/right.gif"));*/

        upImage=setUp("up");
        downImage=setUp("down");
        leftImage=setUp("left");
        rightImage=setUp("right");

    }

    public BufferedImage setUp(String imageName) {
        UtilityTool tool = new UtilityTool();
        BufferedImage image = null;
        try{
           image=ImageIO.read(new File("./src/player/"+imageName+".gif"));
            image = tool.scaleImage(image, gp.tileSize, gp.tileSize);


        }catch(IOException e){
            e.printStackTrace();
        }
        return image;
    }

    public void update() {

        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true
                || keyH.rightPressed == true) {

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
        }
    }

    public void pickUpObject(int i) {
        if (i != 999) {
            String objName = gp.obj[i].name;
            switch (objName) {
                case "key":
                    gp.playSE(1);
                    hasKey++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("You got a key!");
                    System.out.println("You have " + hasKey + " key(s)");
                    break;
                case "door":
                    gp.playSE(3);
                    if (hasKey > 0) {
                        gp.obj[i] = null;
                        hasKey--;
                        gp.ui.showMessage("Door Opened! You used a key!");
                        System.out.println("You have " + hasKey + " key(s)");
                    } else {
                        gp.ui.showMessage("You need a key to open this door!");
                    }
                    break;
                case "boots":
                    gp.playSE(2);
                    speed += 2;
                    gp.obj[i] = null;
                    gp.ui.showMessage("You got boots! Your speed increased!");
                    System.out.println("You have boots");
                    break;
                case "chest":
                    gp.playSE(4);
                    //gp.ui.showMessage("You found the treasure! You win!");
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    gp.playSE(4);
                    break;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                image = upImage;
                break;
            case "down":
                image = downImage;
                break;
            case "left":
                image = leftImage;
                break;
            case "right":
                image = rightImage;
                break;
        }

        g2.drawImage(image, screenX, screenY, null);
    }
}
