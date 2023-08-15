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
import Object.OBJ_Mana;
import Character.Character;

import java.awt.BasicStroke;
import java.awt.image.BufferedImage;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font maruMonica;
    BufferedImage heart_full, heart_half, heart_blank, mana_full, mana_blank;
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
    public int playerSlotCol = 0;
    public int playerSlotRow = 0;
    public int merchantSlotCol = 0;
    public int merchantSlotRow = 0;
    int subState = 0;
    int counter =0;
    public Character merchant;

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
        // Create mana images
        Character mana = new OBJ_Mana(gp);
        mana_full = mana.image;
        mana_blank = mana.image2;

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
            drawInventory(gp.player, true);
        }

        // option state
        if (gp.gameState == gp.optionState) {
            drawOptionScreen();
        }

        // game over state
        if (gp.gameState == gp.gameOverState) {
            drawGameOverScreen();
        }

        //transition state
        if(gp.gameState == gp.transitionState){
            drawTransition();
        }

        //trade state
        if(gp.gameState == gp.tradeState){
            drawTradeScreen();
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
        // draw mana images
        x = (gp.tileSize / 2) - 5;
        y = (int) (gp.tileSize * 1.5);
        i = 0;
        // draw max mana
        while (i < gp.player.maxMana) {
            g2.drawImage(mana_blank, x, y, null);
            i++;
            x += 35;
        }
        // Reset x and y
        x = (gp.tileSize / 2) - 5;
        y = (int) (gp.tileSize * 1.5);
        i = 0;
        // draw current mana
        while (i < gp.player.mana) {
            g2.drawImage(mana_full, x, y, null);
            i++;
            x += 35;
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
        g2.drawImage(gp.player.idle, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

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
        int x = gp.tileSize * 3;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 6);
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
        final int frameX = gp.tileSize * 2;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize * 5;
        final int frameHeight = (int) (gp.tileSize * 10.5);
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
        g2.drawString("Mana: ", textX, textY + lineHeight * 3);
        g2.drawString("Strength: ", textX, textY + lineHeight * 4);
        g2.drawString("Agility: ", textX, textY + lineHeight * 5);
        g2.drawString("Attack: ", textX, textY + lineHeight * 6);
        g2.drawString("Defense: ", textX, textY + lineHeight * 7);
        g2.drawString("XP: ", textX, textY + lineHeight * 8);
        g2.drawString("Next Level: ", textX, textY + lineHeight * 9);
        g2.drawString("Gold: ", textX, textY + lineHeight * 10);
        g2.drawString("Weapon: ", textX, textY + lineHeight * 11);
        g2.drawString("Armor: ", textX, textY + lineHeight * 12);
        // values
        // align the values to the right
        int valueX = (frameX + frameWidth) - 30;
        // reset textY
        textY = frameY + gp.tileSize;
        String value;
        // level
        value = String.valueOf(gp.player.level);
        g2.drawString(value, getXforAlignRightText(value, valueX), textY + lineHeight);
        // hp
        value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
        g2.drawString(value, getXforAlignRightText(value, valueX), textY + lineHeight * 2);
        // mana
        value = String.valueOf(gp.player.mana + "/" + gp.player.maxMana);
        g2.drawString(value, getXforAlignRightText(value, valueX), textY + lineHeight * 3);
        // strength
        value = String.valueOf(gp.player.strength);
        g2.drawString(value, getXforAlignRightText(value, valueX), textY + lineHeight * 4);
        // agility
        value = String.valueOf(gp.player.agility);
        g2.drawString(value, getXforAlignRightText(value, valueX), textY + lineHeight * 5);
        // attack
        value = String.valueOf(gp.player.attack);
        g2.drawString(value, getXforAlignRightText(value, valueX), textY + lineHeight * 6);
        // defense
        value = String.valueOf(gp.player.defense);
        g2.drawString(value, getXforAlignRightText(value, valueX), textY + lineHeight * 7);
        // xp
        value = String.valueOf(gp.player.xp);
        g2.drawString(value, getXforAlignRightText(value, valueX), textY + lineHeight * 8);
        // next level
        value = String.valueOf(gp.player.nextLevelXP);
        g2.drawString(value, getXforAlignRightText(value, valueX), textY + lineHeight * 9);
        // gold
        value = String.valueOf(gp.player.gold);
        g2.drawString(value, getXforAlignRightText(value, valueX), textY + lineHeight * 10);
        // display equipments image
        g2.drawImage(gp.player.currentWeapon.down1, textX + 142, textY + lineHeight * 11 - 32, null);
        g2.drawImage(gp.player.currentArmor.down1, textX + 148, textY + lineHeight * 12 - 32, null);

    }

    public void drawInventory(Character character, boolean cursor) {
        int frameX = 0;
        int frameY = 0;
        int frameWidth = 0;
        int frameHeight = 0;
        int slotCol = 0;
        int slotRow = 0;
        if(character == gp.player){
            // inventory frame
        frameX = gp.tileSize * 12;
        frameY = gp.tileSize;
        frameWidth = gp.tileSize * 6;
        frameHeight = gp.tileSize * 5;
        slotCol = playerSlotCol;
        slotRow = playerSlotRow;
        }
        else{
            // inventory frame
        frameX = gp.tileSize * 2;
        frameY = gp.tileSize;
        frameWidth = gp.tileSize * 6;
        frameHeight = gp.tileSize * 5;
        slotCol = merchantSlotCol;
        slotRow = merchantSlotRow;
        }
       //frame
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        // inventory slot
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize + 3;
        // draw character's items
        for (int i = 0; i < character.inventory.size(); i++) {
            // equip cursor
            if (character.inventory.get(i) == character.currentWeapon
                    || character.inventory.get(i) == character.currentArmor) {
                g2.setColor(new Color(240, 190, 190));
                g2.setStroke(new BasicStroke(3));
                g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
            }

            g2.drawImage(character.inventory.get(i).down1, slotX, slotY, null);
            slotX += slotSize;
            if (slotX >= slotXstart + (gp.tileSize * 5)) {
                slotX = slotXstart;
                slotY += gp.tileSize;
            }
        }
        // cursor - highlight the selected item
        if (cursor == true){
        int cursorX = slotXstart + (slotCol * slotSize);
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

        int itemIndex = getItemIndexOnInventorySlot(slotCol, slotRow);
        if (itemIndex < character.inventory.size()) {
            drawSubWindow(descriptionFrameX, descriptionFrameY, descriptionFrameWidth, descriptionFrameHeight);
            for (String line : character.inventory.get(itemIndex).description.split("\n")) {
                // draw text in description frame
                g2.drawString(line, textX + 20, textY + 40);
                textY += 40;
            }
        }
    }

    }
    public void drawGameOverScreen(){
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));
        text = "GAME OVER";
        //shadow
        g2.setColor(Color.black);
        x = getXforCenteredText(text);
        y = gp.tileSize*4;
        g2.drawString(text,x,y);
        //main text
        g2.setColor(Color.white);
        g2.drawString(text, x-4, y-4);

        //retry
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Retry";
        x = getXforCenteredText(text);
        y += gp.tileSize*4;
        g2.drawString(text,x,y);
        //display cursor
        if(commandNumber == 0){
            g2.drawString(">",x-40,y);
        }

        //back to title screen
        text = "Quit";
        x = getXforCenteredText(text);
        y += 55;
        g2.drawString(text,x,y);
        //display cursor
        if(commandNumber == 1){
            g2.drawString(">",x-40,y);
        }

    }
    public void drawOptionScreen() {
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        // sub window
        int frameX = gp.tileSize * 6;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 8;
        int frameHeight = gp.tileSize * 10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch (subState) {
            case 0:
                optionTop(frameX, frameY);
                break;
            case 1: optionFullScreenNotif(frameX, frameY);
                break;
            case 2: optionControl(frameX, frameY);
                break;
            case 3: optionEndGame(frameX, frameY);
                break;
        }
        gp.keyH.enterPressed = false;
    }

    public void optionTop(int frameX, int frameY) {
        int textX;
        int textY;

        // Title
        String text = "Options";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);
        // Full Screen on/off
        textX = frameX + gp.tileSize;
        textY += gp.tileSize * 2;
        g2.drawString("Full Screen", textX, textY);
        if(commandNumber == 0 ){
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed == true){
                if(gp.fullScreenOn == false){
                    gp.fullScreenOn = true;
                }
                else{
                    gp.fullScreenOn = false;
                }
                subState = 1;
            }
        }
        // Music
        textY += gp.tileSize;
        g2.drawString("Music", textX, textY);
        if(commandNumber == 1 ){
            g2.drawString(">", textX - 25, textY);
        }
        // SE
        textY += gp.tileSize;
        g2.drawString("SE", textX, textY);
        if(commandNumber == 2 ){
            g2.drawString(">", textX - 25, textY);
        }
        // Control
        textY += gp.tileSize;
        g2.drawString("Control", textX, textY);
        if(commandNumber == 3 ){
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed == true){
                subState = 2;
                commandNumber = 0;
            }
        }
        // End Game
        textY += gp.tileSize;
        g2.drawString("End Game", textX, textY);
        if(commandNumber == 4 ){
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed == true){
               subState = 3;
                commandNumber = 0;
            }
        }
        //back
        textY += gp.tileSize * 2;
        g2.drawString("Back", textX, textY);
        if(commandNumber == 5 ){
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed == true){
                gp.gameState = gp.playState;
                commandNumber = 0;
            }
        }

        //Full screen check box
        textX = frameX + (int)(gp.tileSize * 4.5);
        textY = frameY + gp.tileSize * 2 + 24;
        g2.setStroke(new BasicStroke (3));
        g2.drawRect(textX, textY, 24, 24);
        if(gp.fullScreenOn == true){
            g2.fillRect(textX, textY, 24, 24);
        }

        //Music volume
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24); // 120/5 = 24 
        int volumeWidth = 24 * gp.music.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);

        //SE volume
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24);
        volumeWidth = 24 * gp.soundEffect.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);

        gp.config.saveConfig();


    }
    public void optionFullScreenNotif(int frameX, int frameY){
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize * 3;
        currentDialogue = "Full screen mode will be \napplied after restarting \nthe game.";
        for(String line : currentDialogue.split("\n")){
            g2.drawString(line, textX, textY);
            textY += 40;
        }
        //back
        textY = frameY + gp.tileSize * 8;
        g2.drawString("Back", textX, textY);
        if(commandNumber == 0 ){
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed == true){
                subState = 0;
            }
        }
    }
    public void optionControl(int frameX, int frameY){
        int textX;
        int textY;

        // Title
        String text = "Control";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);
       
        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Move" , textX, textY);
        textY += gp.tileSize;
        g2.drawString("Attack" , textX, textY);
        textY += gp.tileSize;
        g2.drawString("Shoot/Cast" , textX, textY);
        textY += gp.tileSize;
        g2.drawString("Player Status" , textX, textY);
        textY += gp.tileSize;
        g2.drawString("Pause" , textX, textY);
        textY += gp.tileSize;
        g2.drawString("Options" , textX, textY);
        textY += gp.tileSize;

        textX = frameX + gp.tileSize*6;
        textY = frameY + gp.tileSize *2;
        g2.drawString("Arrow" , textX, textY);
        textY += gp.tileSize;
        g2.drawString("ENTER" , textX, textY);
        textY += gp.tileSize;
        g2.drawString("F" , textX, textY);
        textY += gp.tileSize;
        g2.drawString("C" , textX, textY);
        textY += gp.tileSize;
        g2.drawString("P" , textX, textY);
        textY += gp.tileSize;
        g2.drawString("ESC" , textX, textY);
        textY += gp.tileSize;

        //Back
        textX = frameX + gp.tileSize;
        textY = frameY + gp.tileSize * 9;
        g2.drawString("Back", textX, textY);
        if(commandNumber == 0 ){
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed == true){
                subState = 0;
                commandNumber = 3;
            }
        }

    }
    public void optionEndGame(int frameX, int frameY){
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize * 3;
        currentDialogue = "Are you sure you want to \nend the game?\nThis will redirect you to \nthe title screen.";
        for(String line : currentDialogue.split("\n")){
            g2.drawString(line, textX, textY);
            textY += 40;
        }
        //Yes
        String text = "Yes";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize * 8;
        g2.drawString(text, textX, textY);
        if(commandNumber == 0 ){
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed == true){
                //return to title screen
                subState = 0;
                gp.gameState = gp.titleState;
            }
        }
        //No
        text = "No";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize * 9;
        g2.drawString(text, textX, textY);
        if(commandNumber == 1 ){
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed == true){
                subState = 0;
                commandNumber= 4;
            }
        }


    }
   
    public void drawTransition(){
        counter ++;
        g2.setColor(new Color(0,0,0));//black
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        if(counter == 50){
            counter = 0;
            gp.gameState = gp.playState;
            gp.currentMap =gp.eventHandler.tempMap;
            gp.player.worldX = gp.tileSize * gp.eventHandler.tempCol;
            gp.player.worldY = gp.tileSize * gp.eventHandler.tempRow;
            gp.eventHandler.previousEventX = gp.player.worldX;
            gp.eventHandler.previousEventY = gp.player.worldY;
        }
    }

    public void drawTradeScreen(){
        switch(subState){
            case 0: 
            selectTrade();
            break;
            case 1:
            tradeBuy();
            break;
            case 2:
            tradeSell();
            break;
        }
        gp.keyH.enterPressed = false;
    }

    public void selectTrade(){
        drawDialogueScreen();   

        //drawSubWindow
        int x = gp.tileSize * 15;
        int y = gp.tileSize * 4;
        int width = gp.tileSize * 3;
        int height = (int)(gp.tileSize * 3.5);
        drawSubWindow(x, y, width, height);

        //drawText
        x += gp.tileSize;
        y += gp.tileSize;
        g2.drawString("Buy", x, y);
        if(commandNumber == 0){
            g2.drawString(">", x - 24, y);
            if(gp.keyH.enterPressed == true){
                subState = 1;
                //commandNumber = 0;
            }
        }
        y += gp.tileSize;
        g2.drawString("Sell", x, y);
        if(commandNumber == 1){
            g2.drawString(">", x - 24, y);
            if(gp.keyH.enterPressed == true){
                commandNumber = 0;
                gp.gameState = gp.dialogueState;
                currentDialogue = "Come again, friend!";
                //commandNumber = 0;
            }
        }
        y += gp.tileSize;
        g2.drawString("Exit", x, y);
        if(commandNumber == 2){
            g2.drawString(">", x - 24, y);
            if(gp.keyH.enterPressed == true){
                subState = 0;
                commandNumber = 0;
            }
        }

    }

    public void tradeBuy(){
        //draw player inventory
        drawInventory(gp.player, false);
        //draw merchant inventory
        drawInventory(merchant, true);
        
    }
    public void tradeSell(){

    }

    public int getItemIndexOnInventorySlot(int slotCol, int slotRow) {
        int itemIndex = slotCol + (slotRow * 5);
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