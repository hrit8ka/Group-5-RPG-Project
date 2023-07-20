package Character;

//import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.KeyHandler;

import Character.Player;

public class Player extends Character {

    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage(); // get the image of the player
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            //up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            //up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_p_2.png"));
            //down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            //down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            //left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            //left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            //right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            //right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));

            File f1 = new File("./src/player/boy_up_1.png");
            File f2 = new File("./src/player/boy_up_2.png");
            File f3 = new File("./src/player/boy_down_1.png");
            File f4 = new File("./src/player/boy_down_2.png");
            File f5 = new File("./src/player/boy_left_1.png");
            File f6 = new File("./src/player/boy_left_2.png");
            File f7 = new File("./src/player/boy_right_1.png");
            File f8 = new File("./src/player/boy_right_2.png");
            up1=ImageIO.read(f1);
            up2=ImageIO.read(f2);
            down1=ImageIO.read(f3);
            down2=ImageIO.read(f4);
            left1=ImageIO.read(f5);
            left2=ImageIO.read(f6);
            right1=ImageIO.read(f7);
            right2=ImageIO.read(f8);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyH.upPressed == true) {
            direction = "up";
            y -= speed;
        }
        if (keyH.downPressed == true) {
            direction = "down";
            y += speed;
        }
        if (keyH.leftPressed == true) {
            direction = "left";
            x -= speed;
        }
        if (keyH.rightPressed == true) {
            direction = "right";
            x += speed;
        }
        spriteCounter++;
        if (spriteCounter > 14) {
            if (spriteNumber == 1) {
                spriteNumber = 2;
            } else if (spriteNumber == 2) {
                spriteNumber = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {

        // g2.setColor(Color.WHITE);
        // g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        BufferedImage image = null;

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
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }

}
