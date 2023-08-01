package Main;

import java.awt.Color;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import Object.OBJ_Heart;
import Object.SuperObject;

import java.awt.BasicStroke;
import java.awt.image.BufferedImage;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font maruMonica;
    BufferedImage heart_full, heart_half, heart_blank;
    // BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNumber = 0;

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
        //Create heart images
        SuperObject heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;

    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(maruMonica);
        g2.setColor(Color.white);

        // title state
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        // play state
        if (gp.gameState == gp.playState) {
            drawPlayerLife();
        }
        // pause state
        if (gp.gameState == gp.pauseState) {
            drawPlayerLife();
            drawPauseScreen();

        }
        // dialogue state
        if (gp.gameState == gp.dialogueState) {
            drawPlayerLife();
            drawDialogueScreen();
        }
    }

    public void drawPlayerLife(){

        gp.player.life = 5;
        int x= gp.tileSize/2;
        int y= gp.tileSize/2;
        int i=0;
        //draw max life
        while(i < gp.player.maxLife/2){
            g2.drawImage(heart_blank, x, y,null);
            i++;
            x+=gp.tileSize;
        }
        //Reset x and y
        x= gp.tileSize/2;
        y= gp.tileSize/2;
        i=0;
        //draw current hearts/life
        while(i < gp.player.life){
            g2.drawImage(heart_half, x, y,null);
            i++;
            if(i<gp.player.life){
                g2.drawImage(heart_full, x, y,null);
                i++;
                x=x+gp.tileSize;
            }
        }
    }
    public void drawTitleScreen() {

        // background
        g2.setColor(new Color(70, 120, 80));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        // title name
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
        String text = "RPG";
        int x = getXforCenteredText(text);
        int y = gp.tileSize * 3;
        // shadow
        g2.setColor(Color.black);
        g2.drawString(text, x + 5, y + 5);
        // main color
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        // display player
        x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
        y += gp.tileSize * 2;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

        // menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
        text = "NEW GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize * 3.5;
        g2.drawString(text, x, y);
        if (commandNumber == 0) {
            g2.drawString(">", x - gp.tileSize, y);

        }

        text = "LOAD GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNumber == 1) {
            g2.drawString(">", x - gp.tileSize, y);

        }

        text = "EXIT";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNumber == 2) {
            g2.drawString(">", x - gp.tileSize, y);

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
