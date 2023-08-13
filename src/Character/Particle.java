package Character;

import java.awt.Color;
import java.awt.Graphics2D;

import Main.GamePanel;

public class Particle extends Character {

    Character generator; // the character that generated the particle
    Color color;// color of the particle
    int size; // size of the particle
    int xd;// x direction of the particle
    int yd;// y direction of the particle

    // Constructor for particle
    public Particle(GamePanel gp, Character generator, Color color, int size, int speed, int maxLife, int xd, int yd) {
        super(gp);

        this.generator = generator; // set generator
        this.color = color;// set color
        this.size = size;// set size
        this.speed = speed;// set speed
        this.maxLife = maxLife;// set max life
        this.xd = xd;// set x direction
        this.yd = yd;// set y direction

        life = maxLife;// set life to max life
        int offset = (gp.tileSize / 2) - (size / 2);
        worldX = generator.worldX + offset;// set world x to generator world x
        worldY = generator.worldY + offset;// set world y to generator world y

    }

    public void update() {
        life--;// decrease life
        if (life < maxLife / 3) {// if life is less than half of max life
            yd++;// increase y direction (to add gravity)
        }
        worldX += xd * speed;// update world x
        worldY += yd * speed;// update world y

        if (life == 0) {
            alive = false;// set alive to false
        }

    }

    public void draw(Graphics2D g2) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        g2.setColor(color);
        g2.fillRect(screenX, screenY, size, size);
    }
}
