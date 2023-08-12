package Object;

import Character.Projectile;
import Character.Character;
import Main.GamePanel;

// Fireball class for the fireball projectile
public class Fireball extends Projectile {

    GamePanel gp;

    // Constructor
    public Fireball(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Fireball";
        speed = 10;
        maxLife = 80; // 80 frames = 1.33 seconds
        life = maxLife; // set life to max
        attack = 5; // 5 damage
        usePrice = 1; // 1 mana
        alive = false; // set alive to false
        getImage();
    }

    // Get image
    public void getImage() {
        up1 = setUp("./src/projectiles/fireball_up_1", gp.tileSize, gp.tileSize);
        up2 = setUp("./src/projectiles/fireball_up_2", gp.tileSize, gp.tileSize);
        down1 = setUp("./src/projectiles/fireball_down_1", gp.tileSize, gp.tileSize);
        down2 = setUp("./src/projectiles/fireball_down_2", gp.tileSize, gp.tileSize);
        left1 = setUp("./src/projectiles/fireball_left_1", gp.tileSize, gp.tileSize);
        left2 = setUp("./src/projectiles/fireball_left_2", gp.tileSize, gp.tileSize);
        right1 = setUp("./src/projectiles/fireball_right_1", gp.tileSize, gp.tileSize);
        right2 = setUp("./src/projectiles/fireball_right_2", gp.tileSize, gp.tileSize);

    }
    public boolean haveMana(Character user){
        boolean haveMana = false;
        if(user.mana >= usePrice){ //why is this not working? because it's not in the update method
            haveMana = true;
        } 
        return haveMana;
    }
    public void subtractMana(Character user){
        user.mana -= usePrice;
    }
}
