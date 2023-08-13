package Character;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Main.GamePanel;
import Main.KeyHandler;
import Object.Fireball;

import Object.OBJ_Armor;
import Object.OBJ_Key;
import Object.OBJ_Sword;

// Player class for the player character, extends the Character class
public class Player extends Character {

    // GamePanel gp;
    KeyHandler keyH;// key handler for the player

    // screenX and screenY are the coordinates of the player on the screen, final
    // because they will never change
    public final int screenX;
    public final int screenY;
    int standCounter = 0;// counter for the standing animation
    public boolean noAttack = false;// boolean to check if the player is attacking
    // public int hasKey = 0;
    public ArrayList<Character> inventory = new ArrayList<>();// inventory of the player
    public final int maxInventorySize = 20;// max size of the inventory

    private BufferedImage up1;// image Up for the player
    private BufferedImage up2;// image Up for the player
    public BufferedImage down1;// image Down for the player
    private BufferedImage down2;// image Down for the player
    private BufferedImage left1;// image Left for the player
    private BufferedImage left2;// image Left for the player
    private BufferedImage right1;// image Right for the player
    private BufferedImage right2;// image Right for the player
    // Constructor for the player class, passes the GamePanel and the KeyHandler as
    // parameters

    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);// call the super constructor
        // this.gp = gp;
        this.keyH = keyH;// set the key handler

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);// set screenX
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);// set screenY

        solidArea = new Rectangle();// set the solid area
        solidArea.x = 8;// set the x coordinate of the solid area
        solidArea.y = 16;// set the y coordinate of the solid area
        solidAreaDefaultX = solidArea.x;// set the default x coordinate of the solid area
        solidAreaDefaultY = solidArea.y;// set the default y coordinate of the solid area
        solidArea.width = 32;// set the width of the solid area
        solidArea.height = 32;// set the height of the solid area

        setDefaultValues();// set the default values of the player
        getPlayerImages(); // get the images of the player
        getPlayerAttackImage();// get the attack image of the player
        setItems();// set the items of the player
    }

    // method to set the default values of the player
    public void setDefaultValues() {
        worldX = gp.tileSize * 23;// set the worldX coordinate
        worldY = gp.tileSize * 21;// set the worldY coordinate
        speed = 4;// set the speed of the player
        direction = "down";// set the direction of the player

        // Player status
        level = 1;// set the level of the player
        maxLife = 6;// set the max life of the player
        life = maxLife;// set the life of the player
        maxMana = 4;// set the max mana of the player
        mana = maxMana;// set the mana of the player
        strength = 1; // more strength, more damage given by the player
        agility = 1; // more agility, less damage received by the player
        xp = 0;// xp is the experience of the player
        nextLevelXP = 5;// set the next level xp of the player
        gold = 0;// set the number of gold coins the player has
        currentWeapon = new OBJ_Sword(gp);// set the current weapon of the player
        currentArmor = new OBJ_Armor(gp);// set the current armor of the player
        projectile = new Fireball(gp);// set the projectile of the player
        attack = getAttack(); // total attack value depends on strength and weapon
        defense = getDefense(); // total defense value depends on agility and armor

    }

    // method to set the items of the player
    public void setItems() {
        inventory.add(currentWeapon);// add the current weapon to the inventory
        inventory.add(currentArmor);// add the current armor to the inventory
        inventory.add(new OBJ_Key(gp));// add a key to the inventory
        inventory.add(new OBJ_Key(gp));// add a key to the inventory
        inventory.add(new OBJ_Key(gp));// add a key to the inventory
    }

    // method getAttack to get the attack of the player
    public int getAttack() {
        attackArea = currentWeapon.attackArea;//
        return attack = strength * currentWeapon.attackValue;// return the attack of the player
    }

    // method getDefense to get the defense of the player
    public int getDefense() {
        return defense = agility * currentArmor.defenseValue;// return the defense of the player
    }

    // method to get the images of the player
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
        // down1 = setUp("./src/player/down_1", gp.tileSize, gp.tileSize);
        down1 = setUp("./src/player/link_attacking_down_1", gp.tileSize, gp.tileSize);
        down2 = setUp("./src/player/down_2", gp.tileSize, gp.tileSize);
        left1 = setUp("./src/player/left_1", gp.tileSize, gp.tileSize);
        left2 = setUp("./src/player/left_2", gp.tileSize, gp.tileSize);
        right1 = setUp("./src/player/right_1", gp.tileSize, gp.tileSize);
        right2 = setUp("./src/player/right_2", gp.tileSize, gp.tileSize);
    }

    // method to get the attack image of the player
    public void getPlayerAttackImage() {

        if (currentWeapon.type == swordType) {// if the current weapon is a sword, load the sword attack images
            attackUp1 = setUp("./src/player/boy_attack_up_1", gp.tileSize, gp.tileSize * 2);
            attackUp2 = setUp("./src/player/boy_attack_up_2", gp.tileSize, gp.tileSize * 2);
            // attackDown1 = setUp("./src/player/boy_attack_down_1", gp.tileSize,
            // gp.tileSize * 2);
            attackDown1 = setUp("./src/player/link_attacking_down_1", gp.tileSize, gp.tileSize * 2);
            attackDown2 = setUp("./src/player/boy_attack_down_2", gp.tileSize, gp.tileSize * 2);
            attackLeft1 = setUp("./src/player/boy_attack_left_1", gp.tileSize * 2, gp.tileSize);
            attackLeft2 = setUp("./src/player/boy_attack_left_2", gp.tileSize * 2, gp.tileSize);
            attackRight1 = setUp("./src/player/boy_attack_right_1", gp.tileSize * 2, gp.tileSize);
            attackRight2 = setUp("./src/player/boy_attack_right_2", gp.tileSize * 2, gp.tileSize);
        }
        if (currentWeapon.type == axeType) {// if the current weapon is an axe, load the axe attack images
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

    // method to get the images of the player's weapons
    public void update() {

        if (attacking == true) {// if the player is attacking
            attacking();// call the method to attack
        }
        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true
                || keyH.rightPressed == true || keyH.enterPressed == true) {// if the player is moving

            if (keyH.upPressed == true) {// if the player is moving up
                direction = "up";// set the direction to up
                // worldY -= speed;
            }
            if (keyH.downPressed == true) {// if the player is moving down
                direction = "down";// set the direction to down
                // worldY += speed;
            }
            if (keyH.leftPressed == true) {// if the player is moving left
                direction = "left";// set the direction to left
                // worldX -= speed;
            }
            if (keyH.rightPressed == true) {// if the player is moving right
                direction = "right";// set the direction to right
                // worldX += speed;
            }

            // check collision
            collisionOn = false;// set collision to false
            gp.collisionChecker.checkTile(this);// check collision with tiles

            // check collision with objects
            int objIndex = gp.collisionChecker.checkObject(this, true);
            pickUpObject(objIndex);// call the method to pick up the object

            // check npc collision
            int npcIndex = gp.collisionChecker.checkCharacter(this, gp.npc);
            interactWithNPC(npcIndex);// call the method to interact with the npc

            // check healer collision
            int healerIndex = gp.collisionChecker.checkCharacter(this, gp.healer);
            interactWithHealer(healerIndex);// call the method to interact with the healer

            // check monster collision
            int monsterIndex = gp.collisionChecker.checkCharacter(this, gp.monster);
            contactMonster(monsterIndex);// call the method to contact the monster

            // check interactive tile collision
            int interactiveTileIndex = gp.collisionChecker.checkCharacter(this, gp.interactiveTile);
            interactWithInteractiveTile(interactiveTileIndex);// call the method to interact with the interactive tile

            // check event
            gp.eventHandler.checkEvent();
            // gp.keyH.enterPressed = false;
            // if collision is false, player can move
            if (collisionOn == false && keyH.enterPressed == false) {
                switch (direction) {// move the player in the direction
                    case "up":
                        worldY -= speed;// move the player up
                        break;
                    case "down":
                        worldY += speed;// move the player down
                        break;
                    case "left":
                        worldX -= speed;// move the player left
                        break;
                    case "right":
                        worldX += speed;// move the player right
                        break;
                }
            }
            if (keyH.enterPressed == true && noAttack == false) {// if the player is attacking
                gp.playSE(7);// play the sound effect
                attacking = true;// set attacking to true
                spriteCounter = 0;// reset the sprite counter
            }
            noAttack = false;// set no attack to false
            gp.keyH.enterPressed = false;// set enter pressed to false
            spriteCounter++;// increment the sprite counter
            if (spriteCounter > 12) {// if the sprite counter is greater than 12
                if (spriteNumber == 1) {// if the sprite number is 1, set it to 2
                    spriteNumber = 2;
                } else if (spriteNumber == 2) {// if the sprite number is 2, set it to 1
                    spriteNumber = 1;
                }
                spriteCounter = 0;// reset the sprite counter
            }
        }
        // if F key is pressed and previous projectile is inactive, shoot projectile
        if (gp.keyH.shotKeyPressed == true && projectile.alive == false && projectileCounter == 30
                && projectile.haveMana(this) == true) {
            // set default coordinates, direction and user of projectile
            projectile.set(worldX, worldY, direction, true, this);
            // subtract mana
            projectile.subtractMana(this);
            // add projectile to array list
            gp.projectileList.add(projectile);
            projectileCounter = 0; // reset projectile counter
            gp.playSE(10);
            // stop projectile shooting
            gp.keyH.shotKeyPressed = false;
        }

        // check if player is invincible
        if (invincible == true) {// if the player is invincible
            invincibleCounter++;// increment the invincible counter
            if (invincibleCounter > 60) {// if the invincible counter is greater than 60
                invincible = false;// set invincible to false
                invincibleCounter = 0;// reset the invincible counter
            }
        }
        if (projectileCounter < 30) {
            // increase projectile counter
            projectileCounter++;
        }
        // if player's life is greater than max life, set life to max life
        if (life > maxLife) {
            // set life to max life
            life = maxLife;
        }
        // if player's mana is greater than max mana, set mana to max mana
        if (mana > maxMana) {
            // set mana to max mana
            mana = maxMana;
        }
    }

    // method attack
    public void attacking() {
        spriteCounter++;// increment the sprite counter
        if (spriteCounter <= 5) {// if the sprite counter is less than or equal to 5
            spriteNumber = 1;// set the sprite number to 1
        }
        if (spriteCounter > 5 && spriteCounter <= 25) {// if the sprite counter is greater than 5 and less than or equal
                                                       // to 25
            spriteNumber = 2;// set the sprite number to 2
            int currentWorldX = worldX;// save the current worldX
            int currentWorldY = worldY;// save the current worldY
            int solidAreaWidth = solidArea.width;// save the current solidAreaWidth
            int solidAreaHeight = solidArea.height;// save the current solidAreaHeight
            // adjust player's worldX and worldY for the attackArea
            switch (direction) {
                case "up":
                    worldY -= attackArea.height;// if the player is attacking up, attack area is up
                    break;
                case "down":
                    worldY += attackArea.height;// if the player is attacking down, attack area is down
                    break;
                case "left":
                    worldX -= attackArea.width;// if the player is attacking left, attack area is left
                    break;
                case "right":
                    worldX += attackArea.width;// if the player is attacking right, attack area is right
                    break;
            }
            // attack area becomes solid area
            solidArea.width = attackArea.width;// set the solid area width to the attack area width
            solidArea.height = attackArea.height;// set the solid area height to the attack area height
            // check collision with monster with the attack area
            int monsterIndex = gp.collisionChecker.checkCharacter(this, gp.monster);// check collision with monster
            damagedMonster(monsterIndex, attack);// call the method to damage the monster
            // check collision with interactive tiles
            int interactiveTileIndex = gp.collisionChecker.checkCharacter(this, gp.interactiveTile);
            damageInteractiveTile(interactiveTileIndex);
            // after checking, reset the worldX, worldY, solidAreaWidth and solidAreaHeight
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;

        }
        if (spriteCounter > 25) {// if the sprite counter is greater than 25
            spriteNumber = 1;// set the sprite number to 1
            spriteCounter = 0;// reset the sprite counter
            attacking = false;// set attacking to false
        }

    }

    // method pickUpObject to pick up object on the map
    public void pickUpObject(int i) {
        if (i != 999) {// if the object is not null
            // pickup object
            if (gp.obj[i].type == pickUpType) {// if the object is a pick up item
                gp.obj[i].use(this);// use the object
                gp.obj[i] = null;// set the object to null

            } else {// inventory items
                String text;// text to display
                // if inventory is not full, pick up the object
                if (inventory.size() != maxInventorySize) {// if the inventory is not full
                    inventory.add(gp.obj[i]);// add the object to the inventory
                    gp.playSE(5);// play the sound effect
                    text = "picked up  " + gp.obj[i].name;// set the text to display
                } else {
                    text = "Your inventory is full";// else display that inventory is full
                }
                gp.ui.addMessage(text);// add the text to the message list
                gp.obj[i] = null;// set the object to null
            }
        }

    }

    // method to interact with npc
    public void interactWithNPC(int npcIndex) {
        if (gp.keyH.enterPressed == true) {// if the enter key is pressed
            if (npcIndex != 999) {// if the npc is not null
                noAttack = true;// set noAttack to true
                gp.gameState = gp.dialogueState;// set the game state to dialogue state
                gp.npc[npcIndex].speak(); // speak to the npc
            }
            // gp.keyH.enterPressed = false;
        }

    }

    // method to interact with healer
    public void interactWithHealer(int healerIndex) {
        if (gp.keyH.enterPressed == true) {// if the enter key is pressed
            if (healerIndex != 999) {// if the healer is not null
                noAttack = true;// set noAttack to true
                gp.gameState = gp.dialogueState;// set the game state to dialogue state
                gp.healer[healerIndex].speak(); // speak to the healer
                gp.gameState = gp.healingState;// set the game state to healing state
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

    private void interactWithInteractiveTile(int interactiveTileIndex) {
    }

    // method to check the damage of the monster
    public void damagedMonster(int monsterIndex, int attack) {
        if (monsterIndex != 999) {// if the monster is not null
            if (gp.monster[monsterIndex].invincible == false) {// if the monster is not invincible
                gp.playSE(5);// play the sound effect
                int damage = attack - gp.monster[monsterIndex].defense;// calculate the damage
                if (damage < 0) {// if the damage is less than 0, set the damage to 0
                    damage = 0;
                }
                gp.monster[monsterIndex].life -= damage;// decrease the monster's life
                gp.ui.addMessage("+" + damage + " damage!");// add the message to the message list
                gp.monster[monsterIndex].invincible = true;// set the monster to invincible
                gp.monster[monsterIndex].monsterDamageReaction();// call the method to make the monster react to the
                                                                 // damage

                if (gp.monster[monsterIndex].life <= 0) {// if the monster's life is less than or equal to 0
                    gp.monster[monsterIndex].dying = true;// set the monster to dying
                    gp.ui.addMessage("You defeated the " + gp.monster[monsterIndex].name + "!");// add the message to
                                                                                                // the message list
                    gp.ui.addMessage("+" + gp.monster[monsterIndex].xp + " XP!");// add the message to the message list
                    xp += gp.monster[monsterIndex].xp;// increase the player's xp
                    checkLevelUp();// call the method to check if the player leveled up
                }
            }
        }
    }

    private void damageInteractiveTile(int i) {
        // if the interactive tile is not null, damage the interactive tile
        if(i != 999 && gp.interactiveTile[i].destructible == true && gp.interactiveTile[i].isCorrectItem(this)==true){
            gp.interactiveTile[i].playSE();
            gp.interactiveTile[i] = gp.interactiveTile[i].getDestroyedTile();
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

    }

}