package Environment;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.image.BufferedImage;

import Main.GamePanel;

public class Light {
    GamePanel gp;
    BufferedImage darkEffect;

    public Light(GamePanel gp, int circleSize) {
        // create a buffered image that is the size of the screen
        darkEffect = new BufferedImage(gp.getWidth(), gp.getHeight(), BufferedImage.TYPE_INT_ARGB);
        // create a graphics object from the buffered image
        Graphics2D g2 = darkEffect.createGraphics();

        // create a new area that is the size of the circle
        int centerX = gp.player.screenX + (gp.tileSize) / 2;
        int centerY = gp.player.screenY + (gp.tileSize) / 2;

        // create a gradient effect with the light circle area
        Color color[] = new Color[12];
        float fraction[] = new float[12];

        color[0] = new Color(0, 0, 0, 0.1f);
        color[1] = new Color(0, 0, 0, 0.42f);
        color[2] = new Color(0, 0, 0, 0.52f);
        color[3] = new Color(0, 0, 0, 0.61f);
        color[4] = new Color(0, 0, 0, 0.69f);
        color[5] = new Color(0, 0, 0, 0.76f);
        color[6] = new Color(0, 0, 0, 0.82f);
        color[7] = new Color(0, 0, 0, 0.87f);
        color[8] = new Color(0, 0, 0, 0.91f);
        color[9] = new Color(0, 0, 0, 0.94f);
        color[10] = new Color(0, 0, 0, 0.96f);
        color[11] = new Color(0, 0, 0, 0.97f);

        fraction[0] = 0.1f;
        fraction[1] = 0.4f;
        fraction[2] = 0.5f;
        fraction[3] = 0.6f;
        fraction[4] = 0.65f;
        fraction[5] = 0.7f;
        fraction[6] = 0.75f;
        fraction[7] = 0.8f;
        fraction[8] = 0.85f;
        fraction[9] = 0.9f;
        fraction[10] = 0.95f;
        fraction[11] = 1.0f;

        // create a gradient paint setting for the light circle area
        RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, (circleSize / 2), fraction, color);
        // set the paint to the graphics object
        g2.setPaint(gPaint);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        // dispose of the graphics object
        g2.dispose();

    }

    public void draw(Graphics2D g2) {
        g2.drawImage(darkEffect, 0, 0, null);// draw the dark effect
    }
}
