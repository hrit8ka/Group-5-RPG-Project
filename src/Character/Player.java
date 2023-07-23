//Using GIF
package Character;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.KeyHandler;

public class Player extends Character {

    GamePanel gp;
    KeyHandler keyH;

    // screenX and screenY are the coordinates of the player on the screen
    public final int screenX;
    public final int screenY;

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
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;

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
        try {
            // Load images for up movement
            upImage = ImageIO.read(new File("./src/player/up.gif"));

            // Load images for down movement
            downImage = ImageIO.read(new File("./src/player/down.gif"));

            // Load images for left movement
            leftImage = ImageIO.read(new File("./src/player/left.gif"));

            // Load images for right movement
            rightImage = ImageIO.read(new File("./src/player/right.gif"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyH.upPressed) {
            direction = "up";
            worldY -= speed;
        }
        if (keyH.downPressed) {
            direction = "down";
            worldY += speed;
        }
        if (keyH.leftPressed) {
            direction = "left";
            worldX -= speed;
        }
        if (keyH.rightPressed) {
            direction = "right";
            worldX += speed;
        }
        collisionOn=false;
        gp.collisionChecker.checkTile(this);
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

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}

/*
 * Uing PNG
 * package Character;
 * 
 * //import java.awt.Color;
 * import java.awt.Graphics2D;
 * import java.awt.image.BufferedImage;
 * import java.io.File;
 * import java.io.IOException;
 * 
 * import javax.imageio.ImageIO;
 * 
 * import Main.GamePanel;
 * import Main.KeyHandler;
 * 
 * import Character.Player;
 * 
 * public class Player extends Character {
 * 
 * GamePanel gp;
 * KeyHandler keyH;
 * 
 * public Player(GamePanel gp, KeyHandler keyH) {
 * this.gp = gp;
 * this.keyH = keyH;
 * 
 * setDefaultValues();
 * getPlayerImage(); // get the image of the player
 * }
 * 
 * public void setDefaultValues() {
 * x = 100;
 * y = 100;
 * speed = 4;
 * direction = "down";
 * }
 * 
 * public void getPlayerImage() {
 * try {
 * //up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
 * //up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_p_2.png"));
 * //down1 =
 * ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
 * //down2 =
 * ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
 * //left1 =
 * ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
 * //left2 =
 * ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
 * //right1 =
 * ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
 * //right2 =
 * ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
 * 
 * File f1 = new File("./src/player/boy_up_1.png");
 * File f2 = new File("./src/player/boy_up_2.png");
 * File f3 = new File("./src/player/boy_down_1.png");
 * File f4 = new File("./src/player/boy_down_2.png");
 * File f5 = new File("./src/player/boy_left_1.png");
 * File f6 = new File("./src/player/boy_left_2.png");
 * File f7 = new File("./src/player/boy_right_1.png");
 * File f8 = new File("./src/player/boy_right_2.png");
 * up1=ImageIO.read(f1);
 * up2=ImageIO.read(f2);
 * down1=ImageIO.read(f3);
 * down2=ImageIO.read(f4);
 * left1=ImageIO.read(f5);
 * left2=ImageIO.read(f6);
 * right1=ImageIO.read(f7);
 * right2=ImageIO.read(f8);
 * 
 * } catch (IOException e) {
 * e.printStackTrace();
 * }
 * }
 * 
 * public void update() {
 * if (keyH.upPressed == true) {
 * direction = "up";
 * y -= speed;
 * }
 * if (keyH.downPressed == true) {
 * direction = "down";
 * y += speed;
 * }
 * if (keyH.leftPressed == true) {
 * direction = "left";
 * x -= speed;
 * }
 * if (keyH.rightPressed == true) {
 * direction = "right";
 * x += speed;
 * }
 * spriteCounter++;
 * if (spriteCounter > 10) {
 * if (spriteNumber == 1) {
 * spriteNumber = 2;
 * } else if (spriteNumber == 2) {
 * spriteNumber = 1;
 * }
 * spriteCounter = 0;
 * }
 * }
 * 
 * public void draw(Graphics2D g2) {
 * 
 * // g2.setColor(Color.WHITE);
 * // g2.fillRect(x, y, gp.tileSize, gp.tileSize);
 * BufferedImage image = null;
 * 
 * switch (direction) {
 * case "up":
 * if (spriteNumber == 1) {
 * image = up1;
 * }
 * if (spriteNumber == 2) {
 * image = up2;
 * }
 * break;
 * case "down":
 * if (spriteNumber == 1) {
 * image = down1;
 * }
 * if (spriteNumber == 2) {
 * image = down2;
 * }
 * break;
 * case "left":
 * if (spriteNumber == 1) {
 * image = left1;
 * }
 * if (spriteNumber == 2) {
 * image = left2;
 * }
 * break;
 * case "right":
 * if (spriteNumber == 1) {
 * image = right1;
 * }
 * if (spriteNumber == 2) {
 * image = right2;
 * }
 * break;
 * }
 * g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
 * }
 * 
 * }
 */