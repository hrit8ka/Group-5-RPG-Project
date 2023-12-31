/**
 * The EventHandler class handles various events in the game, such as checking for collisions,
 * teleporting the player, triggering dialogue with NPCs, and healing the player.
 */
package Main;

import Character.NPC_Merchant;

public class EventHandler {
    GamePanel gp;

    EventRectangle eventRect[][][];
    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    int tempMap, tempCol, tempRow;

    public EventHandler(GamePanel gp) {
        this.gp = gp;
        eventRect = new EventRectangle[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        int map = 0;
        int col = 0;
        int row = 0;
        while (map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {
            eventRect[map][col][row] = new EventRectangle();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height = 2;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;

            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
                if (row == gp.maxWorldRow) {
                    row = 0;
                    map++;
                }
            }
        }
    }

    public void checkEvent() {

        // check if the player is more than 1 tile away from the previous event
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gp.tileSize) {
            canTouchEvent = true;
        }

        if (canTouchEvent == true) {
            // if (hit(27, 16, "right") == true) {
            // damagePit(27, 16, gp.dialogueState);
            // }

            if (hit(0, 23, 12, "up") == true) {
                healingPool(gp.dialogueState);
            }
            // if hit tile 27, 39, teleport to tile 1, 12, 13
            else if (hit(0, 27, 16, "any") == true) {
                teleport(0, 36, 11);
            } else if (hit(0, 10, 39, "any") == true) {
                teleport(1, 12, 13);
            } else if (hit(1, 12, 13, "any") == true) {
                teleport(0, 10, 39);
            } else if (hit(1, 12, 9, "up") == true) {
                speak(gp.merchant[1][0]);
            }
        }

    }

    public boolean hit(int map, int col, int row, String reqDirection) {
        boolean hit = false;
        if (map == gp.currentMap) {
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;

            if (gp.player.solidArea.intersects(eventRect[map][col][row])
                    && eventRect[map][col][row].eventDone == false) {
                if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                    hit = true;

                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                }
            }

            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;

        }
        return hit;

    }

    public void teleport(int map, int col, int row) {
        gp.gameState = gp.transitionState;
        tempMap = map;
        tempCol = col;
        tempRow = row;
        canTouchEvent = false;
        gp.playSE(14);

    }

    public void damagePit(int gameState) {
        gp.gameState = gameState;
        gp.playSE(6);
        gp.ui.currentDialogue = "You fell into a pit! You lost 1 HP!";
        gp.player.life -= 1; // player loses 1 HP
        // eventRect[col][row].eventDone = true; // event is done
        canTouchEvent = false;
    }

    public void healingPool(int gameState) {
        if (gp.keyH.enterPressed == true) {
            gp.gameState = gameState;
            gp.player.noAttack = true;
            gp.playSE(2);
            gp.ui.currentDialogue = "You found a healing pool! You are healed!\n" + "The progress has been saved!";
            gp.player.life = gp.player.maxLife; // player recovers all HP
            // player recovers all mana
            gp.player.mana = gp.player.maxMana;
            gp.assetSetter.setMonster();
            gp.saveLoad.save();
        }
        // gp.keyH.enterPressed = false;
    }

    public void speak(NPC_Merchant merchant) {
        if (gp.keyH.enterPressed == true) {
            gp.gameState = gp.dialogueState;
            gp.player.noAttack = true;
            merchant.speak();

        }
    }

}
