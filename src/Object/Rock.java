package Object;

import Character.Projectile;
import Main.GamePanel;

// Rock class for the rock projectile
public class Rock extends Projectile {

    GamePanel gp;

    // Constructor
    public Rock(GamePanel gp) {
        super(gp);// call super constructor to set gp
        this.gp = gp;

        name = "Rock";
        speed = 5; // 5 speed
        maxLife = 80; // 80 frames = 1.33 seconds
        attack = 10; // + 10 damage
        alive = false;// set alive to false

        getImage(); // import image
    }

    // Get image method
    public void getImage() {

        up1 = setUp("./src/projectiles/rock_down_1", gp.tileSize, gp.tileSize);
        up2 = setUp("./src/projectiles/rock_down_1", gp.tileSize, gp.tileSize);
        down1 = setUp("./src/projectiles/rock_down_1", gp.tileSize, gp.tileSize);
        down2 = setUp("./src/projectiles/rock_down_1", gp.tileSize, gp.tileSize);
        left1 = setUp("./src/projectiles/rock_down_1", gp.tileSize, gp.tileSize);
        left2 = setUp("./src/projectiles/rock_down_1", gp.tileSize, gp.tileSize);
        right1 = setUp("./src/projectiles/rock_down_1", gp.tileSize, gp.tileSize);
        right2 = setUp("./src/projectiles/rock_down_1", gp.tileSize, gp.tileSize);
    }
}
