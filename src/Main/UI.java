package Main;

import java.awt.Color;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.awt.BasicStroke;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font maruMonica;
    // BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    // double playTime;
    // DecimalFormat df = new DecimalFormat("#0.00");
    public String currentDialogue = "";

    public UI(GamePanel gp) {
        this.gp = gp;

        try {
            // InputStream is =
            // this.getClass().getResourceAsStream("src/font/x12y16pxMaruMonica.ttf");
            // importing font
            File fontFile = new File("src/font/x12y16pxMaruMonica.ttf");
            InputStream is = new FileInputStream(fontFile);

            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(maruMonica);
        g2.setColor(Color.white);

        // play state
        if (gp.gameState == gp.playState) {
            // do playState UI
        }
        // pause state
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();

        }
        // dialogue state
        if (gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
        }
    }

    public void drawPauseScreen() {

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80));
        String text = "PAUSED";

        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen() {
        // creating a dialogue box
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        x += gp.tileSize;
        y += gp.tileSize;
        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
        // g2.drawString(currentDialogue, x, y);
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);

    }

    public int getXforCenteredText(String text) {
        int x;
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth / 2 - length / 2;
        return x;
    }

}

// previous draw method
/*
 * if (gameFinished == true) {
 * g2.setFont(arial_40);
 * g2.setColor(Color.white);
 * 
 * String text;
 * int textLength;
 * int x;
 * int y;
 * 
 * text = "You found the treasure chest!";
 * textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
 * x = gp.screenWidth / 2 - textLength / 2;
 * y = gp.screenHeight / 2 - (gp.tileSize * 3);
 * g2.drawString(text, x, y);
 * 
 * text = "Your time is " + df.format(playTime) + " !!!";
 * textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
 * x = gp.screenWidth / 2 - textLength / 2;
 * y = gp.screenHeight / 2 +(gp.tileSize * 4);
 * g2.drawString(text, x, y);
 * 
 * g2.setFont(arial_80B);
 * g2.setColor(Color.white);
 * text = "Congratulations!";
 * textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
 * x = gp.screenWidth / 2 - textLength / 2;
 * y = gp.screenHeight / 2 + (gp.tileSize * 2);
 * 
 * gp.gameThread = null;
 * 
 * } else {
 * 
 * g2.setFont(arial_40);
 * g2.setColor(Color.white);
 * g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize,
 * gp.tileSize, null);
 * g2.drawString("x " + gp.player.hasKey, 74, 65);
 * 
 * // play time
 * playTime += (double) 1 / 60;
 * g2.drawString("Time: " + df.format(playTime), gp.tileSize * 11, 65);
 * // message
 * if (messageOn == true) {
 * g2.setFont(g2.getFont().deriveFont(30F));
 * g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);
 * 
 * messageCounter++;
 * 
 * if (messageCounter > 120) {
 * messageCounter = 0;
 * messageOn = false;
 * }
 * }
 * }
 * 
 * }
 */
