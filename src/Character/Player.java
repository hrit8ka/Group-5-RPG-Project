package Character;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Main.GamePanel;
import Main.KeyHandler;
import Object.Fireball;

import Object.OBJ_Armor;
import Object.OBJ_BlackCrystal;
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

    public boolean lightUpdated;

    public BufferedImage idle;

    // Constructor for the player class
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
        // worldX = gp.tileSize * 12;
        // worldY = gp.tileSize * 13;
        // gp.currentMap = 1;
        defaultSpeed = 4;

        speed = defaultSpeed;// set the speed of the player
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
        gold = 500;// set the number of gold coins the player has
        currentWeapon = new OBJ_Sword(gp);// set the current weapon of the player
        currentArmor = new OBJ_Armor(gp);// set the current armor of the player
        projectile = new Fireball(gp);// set the projectile of the player
        attack = getAttack(); // total attack value depends on strength and weapon
        defense = getDefense(); // total defense value depends on agility and armor

    }

    // call method to set the default position of the player
    public void setDefaultPosition() {
        worldX = gp.tileSize * 23;// set the worldX coordinate
        worldY = gp.tileSize * 21;// set the worldY coordinate
        direction = "down";// set the direction of the player

    }

    public void restoreLifeandMana() {
        life = maxLife;// set the life of the player
        mana = maxMana;// set the mana of the player
        invincible = false; // reset invincible
    }

    // method to set the items of the player
    public void setItems() {
        inventory.clear();// clear the inventory of the player
        inventory.add(currentWeapon);// add the current weapon to the inventory
        inventory.add(currentArmor);// add the current armor to the inventory
        inventory.add(new OBJ_Key(gp));// add a key to the inventory
        inventory.add(new OBJ_BlackCrystal(gp));// add a black crystal to the inventory
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
        // get image for idle
        idle = setUp("./src/player/idle", gp.tileSize, gp.tileSize);
        // get image for walking
        up1 = setUp("./src/player/up_1", gp.tileSize, gp.tileSize);
        up2 = setUp("./src/player/up_2", gp.tileSize, gp.tileSize);
        down1 = setUp("./src/player/down_1", gp.tileSize, gp.tileSize);
        down2 = setUp("./src/player/down_2", gp.tileSize, gp.tileSize);
        left1 = setUp("./src/player/left_1", gp.tileSize, gp.tileSize);
        left2 = setUp("./src/player/left_2", gp.tileSize, gp.tileSize);
        right1 = setUp("./src/player/right_1", gp.tileSize, gp.tileSize);
        right2 = setUp("./src/player/right_2", gp.tileSize, gp.tileSize);
    }

    public void getSleepingImage(BufferedImage image) {

        up1 = image;
        up2 = image;
        down1 = image;
        down2 = image;
        left1 = image;
        left2 = image;
        right1 = image;
        right2 = image;
    }

    // method to get the attack image of the player
    public void getPlayerAttackImage() {

        if (currentWeapon.type == swordType) {// if the current weapon is a sword, load the sword attack images
            attackUp1 = setUp("./src/player/attack_up_1", gp.tileSize, gp.tileSize);
            attackUp2 = setUp("./src/player/attack_up_2", gp.tileSize, gp.tileSize);
            attackDown1 = setUp("./src/player/attack_down_1", gp.tileSize, gp.tileSize);
            attackDown2 = setUp("./src/player/attack_down_2", gp.tileSize, gp.tileSize);
            attackLeft1 = setUp("./src/player/attack_left_1", gp.tileSize, gp.tileSize);
            attackLeft2 = setUp("./src/player/attack_left_2", gp.tileSize, gp.tileSize);
            attackRight1 = setUp("./src/player/attack_right_1", gp.tileSize, gp.tileSize);
            attackRight2 = setUp("./src/player/attack_right_2", gp.tileSize, gp.tileSize);

        }
        if (currentWeapon.type == axeType) {// if the current weapon is an axe, load the axe attack images
            attackUp1 = setUp("./src/player/axe_right_1", gp.tileSize, gp.tileSize * 2);
            attackUp2 = setUp("./src/player/axe_right_2", gp.tileSize, gp.tileSize * 2);
            attackDown1 = setUp("./src/player/axe_left_1", gp.tileSize, gp.tileSize * 2);
            attackDown2 = setUp("./src/player/axe_left_2", gp.tileSize, gp.tileSize * 2);
            attackLeft1 = setUp("./src/player/axe_left_1", gp.tileSize * 2, gp.tileSize);
            attackLeft2 = setUp("./src/player/axe_left_2", gp.tileSize * 2, gp.tileSize);
            attackRight1 = setUp("./src/player/axe_right_1", gp.tileSize * 2, gp.tileSize);
            attackRight2 = setUp("./src/player/axe_right_2", gp.tileSize * 2, gp.tileSize);
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
            // gp.projectileList.add(projectile);
            // check if projectile array is full
            for (int i = 0; i < gp.projectile[1].length; i++) {
                if (gp.projectile[gp.currentMap][i] == null) {
                    gp.projectile[gp.currentMap][i] = projectile;
                    break;
                }
            }
            projectileCounter = 0; // reset projectile counter
            gp.playSE(10);
            // stop projectile shooting
            gp.keyH.shotKeyPressed = false;
        }

        // check if player is invincible
        if (invincible == true)

        {// if the player is invincible
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
        // if player's life is less than or equal to 0, set game state to game over
        // state
        if (life <= 0) {
            gp.gameState = gp.gameOverState;
            gp.ui.commandNumber = -1;
            gp.playSE(13);
        }
    }


    // method pickUpObject to pick up object on the map
    public void pickUpObject(int i) {
        if (i != 999) {// if the object is not null
            // pickup object
            if (gp.obj[gp.currentMap][i].type == pickUpType) {// if the object is a pick up item

                gp.obj[gp.currentMap][i].use(this);// use the object
                gp.obj[gp.currentMap][i] = null;// set the object to null

            } else if (gp.obj[gp.currentMap][i].type == obstacleType) {// if the object is an obstacle
                if (keyH.enterPressed == true) {
                    noAttack = true;
                    gp.obj[gp.currentMap][i].interact();
                }
            } else {// inventory items
                String text;// text to display

                // if inventory is not full, pick up the object
                if (canObtainItem(gp.obj[gp.currentMap][i]) == true) {// if the inventory is not full
                    gp.playSE(5);// play the sound effect
                    text = "picked up  " + gp.obj[gp.currentMap][i].name;// set the text to display
                } else {
                    text = "Your inventory is full!";// else display that inventory is full
                }
                gp.ui.addMessage(text);// add the text to the message list
                gp.obj[gp.currentMap][i] = null;// set the object to null
            }
        }

    }

    // method to interact with npc
    public void interactWithNPC(int npcIndex) {
        if (gp.keyH.enterPressed == true) {// if the enter key is pressed
            if (npcIndex != 999) {// if the npc is not null
                noAttack = true;// set noAttack to true
                gp.gameState = gp.dialogueState;// set the game state to dialogue state
                gp.npc[gp.currentMap][npcIndex].speak(); // speak to the npc
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
                gp.healer[gp.currentMap][healerIndex].speak(); // speak to the healer
                gp.gameState = gp.healingState;// set the game state to healing state
                gp.healer[gp.currentMap][healerIndex].catHeal(); // heal the player

            }
            // gp.keyH.enterPressed = false;
        }

    }

    // method to check if the player is in contact with the monster
    public void contactMonster(int monsterIndex) {
        if (monsterIndex != 999) {// if the player is in contact with the monster, the player will be damaged
            if (invincible == false && gp.monster[gp.currentMap][monsterIndex].dying == false) {
                gp.playSE(6);// play the sound effect
                int damage = gp.monster[gp.currentMap][monsterIndex].attack - defense; // calculate the damage
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
    public void damagedMonster(int monsterIndex,Character attacker, int attack, int knockBackPower) {
        if (monsterIndex != 999) {// if the monster is not null
            if (gp.monster[gp.currentMap][monsterIndex].invincible == false) {// if the monster is not invincible
                gp.playSE(5);// play the sound effect
                if (knockBackPower > 0) {
                    knockBack(gp.monster[gp.currentMap][monsterIndex], attacker, knockBackPower);
                }
                knockBack(gp.monster[gp.currentMap][monsterIndex], attacker, knockBackPower);
                int damage = attack - gp.monster[gp.currentMap][monsterIndex].defense;// calculate the damage
                if (damage < 0) {// if the damage is less than 0, set the damage to 0
                    damage = 0;
                }
                gp.monster[gp.currentMap][monsterIndex].life -= damage;// decrease the monster's life
                gp.ui.addMessage("+" + damage + " damage!");// add the message to the message list
                gp.monster[gp.currentMap][monsterIndex].invincible = true;// set the monster to invincible
                gp.monster[gp.currentMap][monsterIndex].monsterDamageReaction();

                if (gp.monster[gp.currentMap][monsterIndex].life <= 0) {// if monster's life is <= to 0
                    gp.monster[gp.currentMap][monsterIndex].dying = true;// set the monster to dying
                    gp.ui.addMessage("You defeated the " + gp.monster[gp.currentMap][monsterIndex].name + "!");// add
                                                                                                               // the
                                                                                                               // message
                                                                                                               // to
                    // the message list
                    gp.ui.addMessage("+" + gp.monster[gp.currentMap][monsterIndex].xp + " XP!");// add the message to
                                                                                                // the message list
                    xp += gp.monster[gp.currentMap][monsterIndex].xp;// increase the player's xp
                    checkLevelUp();// call the method to check if the player leveled up
                }
            }
        }
    }
    public void damageInteractiveTile(int i) {
        // if the interactive tile is not null, damage the interactive tile
        if (i != 999 && gp.interactiveTile[gp.currentMap][i].destructible == true
                && gp.interactiveTile[gp.currentMap][i].isCorrectItem(this) == true) {
            gp.interactiveTile[gp.currentMap][i].playSE();
            generateParticles(gp.interactiveTile[gp.currentMap][i], gp.interactiveTile[gp.currentMap][i]);
            gp.interactiveTile[gp.currentMap][i] = gp.interactiveTile[gp.currentMap][i].getDestroyedTile();
        }
    }

    public void damageProjectile(int i) {
        if (i != 999) {
            Character projectile = gp.projectile[gp.currentMap][i];
            projectile.alive = false;
            generateParticles(projectile, projectile);
        }
    }

    public void checkLevelUp() {
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
        int itemIndex = gp.ui.getItemIndexOnInventorySlot(gp.ui.playerSlotCol, gp.ui.playerSlotRow);

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
            if (selectedItem.type == lightType) {
                if (currentLight == selectedItem) {
                    currentLight = null;
                } else {
                    currentLight = selectedItem;
                }
                lightUpdated = true;
            }
            if (selectedItem.type == consumableType) {
                if (selectedItem.use(this) == true) {
                    if (selectedItem.amount > 1) {
                        selectedItem.amount--;
                    } else {
                        // selectedItem.use(this);
                        inventory.remove(itemIndex);
                    }
                }
            }
        }
    }

    public int searchItemInInventory(String itemName) {
        int itemIndex = 999;
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).name.equals(itemName)) {
                itemIndex = i;
                break;
            }
        }
        return itemIndex;
    }

    public boolean canObtainItem(Character item) {
        boolean canObtain = false;
        // check if stackable
        if (item.stackable == true) {
            int index = searchItemInInventory(item.name);
            if (index != 999) {
                // if the item is stackable and the player has the item, increase the amount
                inventory.get(index).amount++;
                canObtain = true;
            } else {
                if (inventory.size() != maxInventorySize) {
                    inventory.add(item);
                    canObtain = true;
                }
            }
        } else {
            // not stackable, check if the inventory is full
            if (inventory.size() != maxInventorySize) {
                inventory.add(item);
                canObtain = true;
            }
        }
        return canObtain;
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