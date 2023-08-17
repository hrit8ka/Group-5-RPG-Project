/**
 * The `envManager` class is responsible for managing the environment in a game, including setting up
 * and updating lighting.
 */
package Environment;

import java.awt.Graphics2D;

import Main.GamePanel;

public class envManager {

    GamePanel gp;
    public Light lighting;

    public envManager(GamePanel gp) {
        this.gp = gp;

    }

    public void setUp() {
        // set up lighting
        lighting = new Light(gp);
    }
    public void update(){
        lighting.update();
    }

    public void draw(Graphics2D g2) {
        lighting.draw(g2);// draw lighting
    }

}
