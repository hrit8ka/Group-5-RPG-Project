package Character;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class Character {
    public int worldX, worldY; //worldX is the x coordinate of the player in the world, worldY is the y coordinate of the player in the world
    public int speed; // speed of the player

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNumber = 1;

    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;


}
