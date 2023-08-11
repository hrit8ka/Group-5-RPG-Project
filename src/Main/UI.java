package Main;

import java.awt.Color;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import Object.OBJ_Heart;
import Character.Character;

import java.awt.BasicStroke;
import java.awt.image.BufferedImage;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font maruMonica;
    BufferedImage heart_full, heart_half, heart_blank;
    // BufferedImage keyImage;
    public boolean messageOn = false;
    // public String message = "";
    // int messageCounter = 0;
    ArrayList<String> messages = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNumber = 0;
    public int titleScreenState = 0;
    public int slotColumn = 0;
    public int slotRow = 0;

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
        // Create heart images
        Character heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;

    }

    public void addMessage(String text) {
        messages.add(text);
        messageCounter.add(0);
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
            drawMessage();
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

        // character state
        if (gp.gameState == gp.characterState) {
            drawCharacterScreen();
            drawInventory();
        }
    }

    public void drawPlayerLife() {

        // gp.player.life = 5;
        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;
        // draw max life
        while (i < gp.player.maxLife / 2) {
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }
        // Reset x and y
        x = gp.tileSize / 2;
        y = gp.tileSize / 2;
        i = 0;
        // draw current hearts/life
        while (i < gp.player.life) {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if (i < gp.player.life) {
                g2.drawImage(heart_full, x, y, null);
                i++;
                x = x + gp.tileSize;
            }
        }
    }

    private void drawMessage() {
        int messageX = gp.tileSize;
        int messageY = gp.tileSize * 4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
        for (int i = 0; i < messages.size(); i++) {
            if (messages.get(i) != null) {
                g2.setColor(Color.white);
                g2.drawString(messages.get(i), messageX, messageY);

                int counter = messageCounter.get(i) + 1; // increase the counter
                messageCounter.set(i, counter); // set the counter to the arraylist
                messageY += 40; // move the message down

                if (messageCounter.get(i) > 180) {
                    messages.remove(i);
                    messageCounter.remove(i);
                }
            }
        }

    }

    public void drawTitleScreen() {

        // background
        g2.setColor(new Color(70, 120, 80));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        // title name
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
        String text = "The Legend of Hyrule";
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

    public void drawCharacterScreen() {
        // creating a frame
        final int frameX = gp.tileSize;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize * 5;
        final int frameHeight = gp.tileSize * 10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // text
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 35;
        // title of the frame in bold
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
        g2.drawString("Player Stats", textX, textY);
        // reset font
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        // names
        g2.drawString("Level: ", textX, textY + lineHeight);
        g2.drawString("HP: ", textX, textY + lineHeight * 2);
        g2.drawString("Strength: ", textX, textY + lineHeight * 3);
        g2.drawString("Agility: ", textX, textY + lineHeight * 4);
        g2.drawString("Attack: ", textX, textY + lineHeight * 5);
        g2.drawString("Defense: ", textX, textY + lineHeight * 6);
        g2.drawString("XP: ", textX, textY + lineHeight * 7);
        g2.drawString("Next Level: ", textX, textY + lineHeight * 8);
        g2.drawString("Gold: ", textX, textY + lineHeight * 9);
        g2.drawString("Weapon: ", textX, textY + lineHeight * 10);
        g2.drawString("Armor: ", textX, textY + lineHeight * 11);
        // values
        // align the values to the right
        int valueX = (frameX + frameWidth) - 30;
        // reset textY
        textY = frameY + gp.tileSize;
        String value;
        value = String.valueOf(gp.player.level);
        g2.drawString(value, getXforAlignRightText(value, valueX), textY + lineHeight);
        value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
        g2.drawString(value, getXforAlignRightText(value, valueX), textY + lineHeight * 2);
        value = String.valueOf(gp.player.strength);
        g2.drawString(value, getXforAlignRightText(value, valueX), textY + lineHeight * 3);
        value = String.valueOf(gp.player.agility);
        g2.drawString(value, getXforAlignRightText(value, valueX), textY + lineHeight * 4);
        value = String.valueOf(gp.player.attack);
        g2.drawString(value, getXforAlignRightText(value, valueX), textY + lineHeight * 5);
        value = String.valueOf(gp.player.defense);
        g2.drawString(value, getXforAlignRightText(value, valueX), textY + lineHeight * 6);
        value = String.valueOf(gp.player.xp);
        g2.drawString(value, getXforAlignRightText(value, valueX), textY + lineHeight * 7);
        value = String.valueOf(gp.player.nextLevelXP);
        g2.drawString(value, getXforAlignRightText(value, valueX), textY + lineHeight * 8);
        value = String.valueOf(gp.player.gold);
        g2.drawString(value, getXforAlignRightText(value, valueX), textY + lineHeight * 9);
        // display equipments image
        g2.drawImage(gp.player.currentWeapon.down1, textX + 142, textY + lineHeight * 10 - 32, null);
        g2.drawImage(gp.player.currentArmor.down1, textX + 148, textY + lineHeight * 11 - 32, null);

    }

    public void drawInventory() {
        // inventory frame
        int frameX = gp.tileSize * 9;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 6;
        int frameHeight = gp.tileSize * 5;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        // inventory slot
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize + 3;
        // draw player's items
        for (int i = 0; i < gp.player.inventory.size(); i++) {
            g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY, null);
            slotX += slotSize;
            if (slotX >= slotXstart + (gp.tileSize * 5)) {
                slotX = slotXstart;
                slotY += gp.tileSize;
            }
        }
        // cursor - highlight the selected item
        int cursorX = slotXstart + (slotColumn * slotSize);
        int cursorY = slotYstart + (slotRow * slotSize);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
        // description frame
        int descriptionFrameX = frameX;
        int descriptionFrameY = frameY + frameHeight;
        int descriptionFrameWidth = frameWidth;
        int descriptionFrameHeight = gp.tileSize * 3;
        // description text
        int textX = descriptionFrameX;
        int textY = descriptionFrameY;
        g2.setFont(g2.getFont().deriveFont(28F));

        int itemIndex = getItemIndexOnInventorySlot();
        if (itemIndex < gp.player.inventory.size()) {
            drawSubWindow(descriptionFrameX, descriptionFrameY, descriptionFrameWidth, descriptionFrameHeight);
            for (String line : gp.player.inventory.get(itemIndex).description.split("\n")) {
                //draw text in description frame
                g2.drawString(line, textX + 20, textY + 40);
                textY += 40;
            }
        } 

    }

    public int getItemIndexOnInventorySlot() {
        int itemIndex = slotColumn + (slotRow * 5);
        return itemIndex;
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
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }

    public int getXforAlignRightText(String text, int valueX) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = valueX - length;
        return x;
    }
}