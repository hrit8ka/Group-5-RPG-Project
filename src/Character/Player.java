package Character;

import java.awt.AlphaComposite;
//import java.awt.Color;
//import java.awt.Font;
import java.awt.Graphics2D;
//import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
import java.util.ArrayList;

//import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.KeyHandler;
import Object.Fireball;
//import Main.UtilityTool;
import Object.OBJ_Armor;
import Object.OBJ_Key;
import Object.OBJ_Sword;

public class Player extends Character {

    // GamePanel gp;
    KeyHandler keyH;

    // screenX and screenY are the coordinates of the player on the screen
    public final int screenX;
    public final int screenY;
    int standCounter = 0;
    public boolean noAttack = false;
    // public int hasKey = 0;
    public ArrayList<Character> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;

    private BufferedImage up1;
    private BufferedImage up2;
    public BufferedImage down1;
    private BufferedImage down2;
    private BufferedImage left1;
    private BufferedImage left2;
    private BufferedImage right1;
    private BufferedImage right2;

    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);
        // this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImages(); // get the images of the player
        getPlayerAttackImage();
        setItems();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";

        // Player status
        level = 1;
        maxLife = 4;
        life = maxLife;
        strength = 1; // more strength, more damage given by the player
        agility = 1; // more agility, less damage received by the player
        xp = 0;
        nextLevelXP = 5;
        gold = 0;
        currentWeapon = new OBJ_Sword(gp);
        currentArmor = new OBJ_Armor(gp);
        projectile = new Fireball(gp);
        attack = getAttack(); // total attack value depends on strength and weapon
        defense = getDefense(); // total defense value depends on agility and armor

    }

    public void setItems() {
        inventory.add(currentWeapon);
        inventory.add(currentArmor);
        inventory.add(new OBJ_Key(gp));
        inventory.add(new OBJ_Key(gp));
        inventory.add(new OBJ_Key(gp));
    }

    public int getAttack() {
        attackArea = currentWeapon.attackArea;
        return attack = strength * currentWeapon.attackValue;
    }

    public int getDefense() {
        return defense = agility * currentArmor.defenseValue;
    }

    public void getPlayerImages() {

        // Load images for up movement
        /*
         * upImage = ImageIO.read(new File("./src/player/up.gif"));
         * 
         * // Load images for down movement
         * downImage = ImageIO.read(new File("./src/player/down.gif"));
         * 
         * // Load images for left movement
         * leftImage = ImageIO.read(new File("./src/player/left.gif"));
         * 
         * // Load images for right movement
         * rightImage = ImageIO.read(new File("./src/player/right.gif"));
         */

        /*
         * upImage=setUp("/player/up");
         * downImage=setUp("/player/down");
         * leftImage=setUp("/player/left");
         * rightImage=setUp("/player/right");
         */

        /*
         * up1 = setUp("./src/player/boy_up_1", gp.tileSize, gp.tileSize);
         * up2 = setUp("./src/player/boy_up_2", gp.tileSize, gp.tileSize);
         * down1 = setUp("./src/player/boy_down_1", gp.tileSize, gp.tileSize);
         * down2 = setUp("./src/player/boy_down_2", gp.tileSize, gp.tileSize);
         * left1 = setUp("./src/player/boy_left_1", gp.tileSize, gp.tileSize);
         * left2 = setUp("./src/player/boy_left_2", gp.tileSize, gp.tileSize);
         * right1 = setUp("./src/player/boy_right_1", gp.tileSize, gp.tileSize);
         * right2 = setUp("./src/player/boy_right_2", gp.tileSize, gp.tileSize);
         */

        up1 = setUp("./src/player/up_1", gp.tileSize, gp.tileSize);
        up2 = setUp("./src/player/up_2", gp.tileSize, gp.tileSize);
        down1 = setUp("./src/player/down_1", gp.tileSize, gp.tileSize);
        down2 = setUp("./src/player/down_2", gp.tileSize, gp.tileSize);
        left1 = setUp("./src/player/left_1", gp.tileSize, gp.tileSize);
        left2 = setUp("./src/player/left_2", gp.tileSize, gp.tileSize);
        right1 = setUp("./src/player/right_1", gp.tileSize, gp.tileSize);
        right2 = setUp("./src/player/right_2", gp.tileSize, gp.tileSize);
    }

    public void getPlayerAttackImage() {

        if (currentWeapon.type == swordType) {
            attackUp1 = setUp("./src/player/boy_attack_up_1", gp.tileSize, gp.tileSize * 2);
            attackUp2 = setUp("./src/player/boy_attack_up_2", gp.tileSize, gp.tileSize * 2);
            attackDown1 = setUp("./src/player/boy_attack_down_1", gp.tileSize, gp.tileSize * 2);
            attackDown2 = setUp("./src/player/boy_attack_down_2", gp.tileSize, gp.tileSize * 2);
            attackLeft1 = setUp("./src/player/boy_attack_left_1", gp.tileSize * 2, gp.tileSize);
            attackLeft2 = setUp("./src/player/boy_attack_left_2", gp.tileSize * 2, gp.tileSize);
            attackRight1 = setUp("./src/player/boy_attack_right_1", gp.tileSize * 2, gp.tileSize);
            attackRight2 = setUp("./src/player/boy_attack_right_2", gp.tileSize * 2, gp.tileSize);
        }
        if (currentWeapon.type == axeType) {
            attackUp1 = setUp("./src/player/boy_axe_up_1", gp.tileSize, gp.tileSize * 2);
            attackUp2 = setUp("./src/player/boy_axe_up_2", gp.tileSize, gp.tileSize * 2);
            attackDown1 = setUp("./src/player/boy_axe_down_1", gp.tileSize, gp.tileSize * 2);
            attackDown2 = setUp("./src/player/boy_axe_down_2", gp.tileSize, gp.tileSize * 2);
            attackLeft1 = setUp("./src/player/boy_axe_left_1", gp.tileSize * 2, gp.tileSize);
            attackLeft2 = setUp("./src/player/boy_axe_left_2", gp.tileSize * 2, gp.tileSize);
            attackRight1 = setUp("./src/player/boy_axe_right_1", gp.tileSize * 2, gp.tileSize);
            attackRight2 = setUp("./src/player/boy_axe_right_2", gp.tileSize * 2, gp.tileSize);
        }
    }

    public void update() {

        if (attacking == true) {
            attacking();
        }
        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true
                || keyH.rightPressed == true || keyH.enterPressed == true) {

            if (keyH.upPressed == true) {
                direction = "up";
                // worldY -= speed;
            }
            if (keyH.downPressed == true) {
                direction = "down";
                // worldY += speed;
            }
            if (keyH.leftPressed == true) {
                direction = "left";
                // worldX -= speed;
            }
            if (keyH.rightPressed == true) {
                direction = "right";
                // worldX += speed;
            }

            // check collision
            collisionOn = false;
            gp.collisionChecker.checkTile(this);

            // check collision with objects
            int objIndex = gp.collisionChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // check npc collision
            int npcIndex = gp.collisionChecker.checkCharacter(this, gp.npc);
            interactWithNPC(npcIndex);

            // check healer collision
            int healerIndex = gp.collisionChecker.checkCharacter(this, gp.healer);
            interactWithHealer(healerIndex);

            // check monster collision
            int monsterIndex = gp.collisionChecker.checkCharacter(this, gp.monster);
            contactMonster(monsterIndex);
            // check event
            gp.eventHandler.checkEvent();
            // gp.keyH.enterPressed = false;
            // if collision is false, player can move
            if (collisionOn == false && keyH.enterPressed == false) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }
            if (keyH.enterPressed == true && noAttack == false) {
                gp.playSE(7);
                attacking = true;
                spriteCounter = 0;
            }
            noAttack = false;
            gp.keyH.enterPressed = false;
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNumber == 1) {
                    spriteNumber = 2;
                } else if (spriteNumber == 2) {
                    spriteNumber = 1;
                }
                spriteCounter = 0;
            }
        }
        //if F key is pressed and previous projectile is inactive, shoot projectile
        if(gp.keyH.shotKeyPressed == true && projectile.alive == false && projectileCounter == 30){
            //set default coordinates, direction and user of projectile
            projectile.set(worldX, worldY, direction, true, this);
            //add projectile to array list
            gp.projectileList.add(projectile);
            projectileCounter = 0; //reset projectile counter
            gp.playSE(10);
            //stop projectile shooting
            gp.keyH.shotKeyPressed = false;
        }


        // check if player is invincible
        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if(projectileCounter < 30){
            //increase projectile counter
            projectileCounter++;
        }
    }

    public void attacking() {
        spriteCounter++;
        if (spriteCounter <= 5) {
            spriteNumber = 1;
        }
        if (spriteCounter > 5 && spriteCounter <= 25) {
            spriteNumber = 2;
            // save the current worldX and worldY, solidAreaWidth and solidAreaHeight
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;
            // adjust player's worldX and worldY for the attackArea
            switch (direction) {

                case "up":
                    worldY -= attackArea.height;
                    break;
                case "down":
                    worldY += attackArea.height;
                    break;
                case "left":
                    worldX -= attackArea.width;
                    break;
                case "right":
                    worldX += attackArea.width;
                    break;
            }
            // attack area becomes solid area
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;
            // check collision with monster with the attack area
            int monsterIndex = gp.collisionChecker.checkCharacter(this, gp.monster);
            damagedMonster(monsterIndex, attack);
            // after checking, reset the worldX, worldY, solidAreaWidth and solidAreaHeight
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;

        }
        if (spriteCounter > 25) {
            spriteNumber = 1;
            spriteCounter = 0;
            attacking = false;
        }

    }

    public void pickUpObject(int i) {
        if (i != 999) {
            String text;
            // if inventory is not full, pick up the object
            if (inventory.size() != maxInventorySize) {
                inventory.add(gp.obj[i]);
                gp.playSE(5);
                text = "picked up a " + gp.obj[i].name;
            } else {
                text = "Your inventory is full";
            }
            gp.ui.addMessage(text);
            gp.obj[i] = null;
        }

    }

    public void interactWithNPC(int npcIndex) {
        if (gp.keyH.enterPressed == true) {
            if (npcIndex != 999) {
                noAttack = true;
                gp.gameState = gp.dialogueState;
                gp.npc[npcIndex].speak(); // speak to the npc
            }
            // gp.keyH.enterPressed = false;
        }

    }

    public void interactWithHealer(int healerIndex) {
        if (gp.keyH.enterPressed == true) {
            if (healerIndex != 999) {
                noAttack = true;
                gp.gameState = gp.dialogueState;
                gp.healer[healerIndex].speak(); // speak to the healer
                gp.gameState = gp.healingState;
                gp.healer[healerIndex].catHeal(); // heal the player

            }
            // gp.keyH.enterPressed = false;
        }

    }

    // method to check if the player is in contact with the monster
    public void contactMonster(int monsterIndex) {
        if (monsterIndex != 999) {// if the player is in contact with the monster, the player will be damaged
            if (invincible == false && gp.monster[monsterIndex].dying == false) {
                gp.playSE(6);// play the sound effect
                int damage = gp.monster[monsterIndex].attack - defense; // calculate the damage
                if (damage < 0) {// if the damage is less than 0, set the damage to 0
                    damage = 0;
                }
                life -= damage;// decrease the player's life
                invincible = true;// set the player to invincible
            }
        }
    }

    public void damagedMonster(int monsterIndex, int attack) {
        if (monsterIndex != 999) {
            if (gp.monster[monsterIndex].invincible == false) {
                gp.playSE(5);
                int damage = attack - gp.monster[monsterIndex].defense;
                if (damage < 0) {
                    damage = 0;
                }
                gp.monster[monsterIndex].life -= damage;
                gp.ui.addMessage("+" + damage + " damage!");
                gp.monster[monsterIndex].invincible = true;
                gp.monster[monsterIndex].monsterDamageReaction();

                if (gp.monster[monsterIndex].life <= 0) {
                    gp.monster[monsterIndex].dying = true;
                    gp.ui.addMessage("You defeated the " + gp.monster[monsterIndex].name + "!");
                    gp.ui.addMessage("+" + gp.monster[monsterIndex].xp + " XP!");
                    xp += gp.monster[monsterIndex].xp;
                    checkLevelUp();
                }
            }
        }
    }

    private void checkLevelUp() {
        if (xp >= nextLevelXP) {
            level++;
            nextLevelXP = nextLevelXP * 2;
            maxLife += 2;
            life = maxLife;
            strength++;
            agility++;
            attack = getAttack();
            defense = getDefense();
            gp.playSE(8);
            gp.gameState = gp.dialogueState;
            gp.ui.currentDialogue = "Level " + level + "!\nYou are stronger now";
        }
    }

    public void selectItem() {
        int itemIndex = gp.ui.getItemIndexOnInventorySlot();

        if (itemIndex < inventory.size()) {
            Character selectedItem = inventory.get(itemIndex);
            // check the type of the item
            if (selectedItem.type == swordType || selectedItem.type == axeType) {
                currentWeapon = selectedItem;
                attack = getAttack();
                getPlayerAttackImage();
            }
            if (selectedItem.type == armorType) {
                currentArmor = selectedItem;
                defense = getDefense();
            }
            if (selectedItem.type == crystalType) {
                selectedItem.use(this);
                inventory.remove(itemIndex);
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;
        switch (direction) {
            case "up":
                if (attacking == false) {
                    if (spriteNumber == 1) {
                        image = up1;
                    }
                    if (spriteNumber == 2) {
                        image = up2;
                    }
                }
                if (attacking == true) {
                    tempScreenY = screenY - gp.tileSize;
                    if (spriteNumber == 1) {
                        image = attackUp1;
                    }
                    if (spriteNumber == 2) {
                        image = attackUp2;
                    }
                }
                break;
            case "down":
                if (attacking == false) {
                    if (spriteNumber == 1) {
                        image = down1;
                    }
                    if (spriteNumber == 2) {
                        image = down2;
                    }
                }
                if (attacking == true) {
                    if (spriteNumber == 1) {
                        image = attackDown1;
                    }
                    if (spriteNumber == 2) {
                        image = attackDown2;
                    }
                }
                break;
            case "left":
                if (attacking == false) {
                    if (spriteNumber == 1) {
                        image = left1;
                    }
                    if (spriteNumber == 2) {
                        image = left2;
                    }
                }
                if (attacking == true) {
                    tempScreenX = screenX - gp.tileSize;
                    if (spriteNumber == 1) {
                        image = attackLeft1;
                    }
                    if (spriteNumber == 2) {
                        image = attackLeft2;
                    }
                }
                break;
            case "right":
                if (attacking == false) {
                    if (spriteNumber == 1) {
                        image = right1;
                    }
                    if (spriteNumber == 2) {
                        image = right2;
                    }
                }
                if (attacking == true) {
                    if (spriteNumber == 1) {
                        image = attackRight1;
                    }
                    if (spriteNumber == 2) {
                        image = attackRight2;
                    }
                }
                break;
        }
        if (invincible == true) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));

        }

        g2.drawImage(image, tempScreenX, tempScreenY, null);

        // reset the composite
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));

        // debug
        // g2.setFont(new Font("Arial", Font.PLAIN, 20));
        // g2.setColor(Color.WHITE);
        // g2.drawString("Invincible: " + invincibleCounter, 10, 400);
    }

}