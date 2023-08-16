package Main;

import Character.Healer;
import Character.NPC_Merchant;
import Character.NPC_Sage;
import Monster.ForestMonster;
import Monster.Slime;
import Object.CampingTent;
import Object.Candle;
import Object.Coin;
import Object.HiddenGem;
import Object.OBJ_Axe;
import Object.OBJ_BlackCrystal;
import Object.OBJ_BlueShield;
import Object.OBJ_Chest;
import Object.OBJ_Door;
import Object.OBJ_Heart;
import Object.OBJ_Key;
import Object.OBJ_Mana;
import Tile_Interactive.dryTree;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        int mapNum = 0;
        int i = 0;
        // add candle
        gp.obj[mapNum][i] = new Candle(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 18;
        gp.obj[mapNum][i].worldY = gp.tileSize * 20;
        i++;
        // add black crystal
        gp.obj[mapNum][i] = new OBJ_BlackCrystal(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 21;
        gp.obj[mapNum][i].worldY = gp.tileSize * 19;
        i++;
        gp.obj[mapNum][i] = new OBJ_BlackCrystal(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 26;
        gp.obj[mapNum][i].worldY = gp.tileSize * 21;
        i++;
        // add axe
        gp.obj[mapNum][i] = new OBJ_Axe(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 33;
        gp.obj[mapNum][i].worldY = gp.tileSize * 21;
        i++;
        // add blue shield
        gp.obj[mapNum][i] = new OBJ_BlueShield(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 35;
        gp.obj[mapNum][i].worldY = gp.tileSize * 21;

        // add gold coins
        i++;
        gp.obj[mapNum][i] = new Coin(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 21;
        gp.obj[mapNum][i].worldY = gp.tileSize * 21;
        i++;
        gp.obj[mapNum][i] = new Coin(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 21;
        gp.obj[mapNum][i].worldY = gp.tileSize * 17;
        i++;
        gp.obj[mapNum][i] = new Coin(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 26;
        gp.obj[mapNum][i].worldY = gp.tileSize * 19;
        i++;

        // set heart
        gp.obj[mapNum][i] = new OBJ_Heart(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 22;
        gp.obj[mapNum][i].worldY = gp.tileSize * 27;
        i++;
        // set mana
        gp.obj[mapNum][i] = new OBJ_Mana(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 22;
        gp.obj[mapNum][i].worldY = gp.tileSize * 29;
        i++;

        // set doors
        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 12;
        gp.obj[mapNum][i].worldY = gp.tileSize * 12;
        i++;

        // set chest
        gp.obj[mapNum][i] = new OBJ_Chest(gp, new OBJ_Key(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize * 30;
        gp.obj[mapNum][i].worldY = gp.tileSize * 29;
        i++;

        //place the tent
        gp.obj[mapNum][i] = new CampingTent(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 27;
        gp.obj[mapNum][i].worldY = gp.tileSize * 16;
        i++;

        //place the diamond
        gp.obj[mapNum][i] = new HiddenGem(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 12;
        gp.obj[mapNum][i].worldY = gp.tileSize * 10;
    }

    // to set the NPC in the game, call this method in the setUpGame() method in
    // GamePanel.java
    public void setNPC() {
        int mapNum = 0;
        int i;
        gp.npc[mapNum][0] = new NPC_Sage(gp); // create a new NPC object
        gp.npc[mapNum][0].worldX = 23 * gp.tileSize;
        gp.npc[mapNum][0].worldY = 23 * gp.tileSize;

        // adding merchant to the game in Map 1
        mapNum = 1;
        i = 0;
        gp.merchant[mapNum][i] = new NPC_Merchant(gp);
        gp.merchant[mapNum][i].worldX = 12 * gp.tileSize;
        gp.merchant[mapNum][i].worldY = 7 * gp.tileSize;
        i++;

    }

    // set healer in the game
    public void setHealer() {
        int mapNum = 0;
        int i = 0;
        gp.healer[mapNum][i] = new Healer(gp);
        gp.healer[mapNum][i].worldX = 37 * gp.tileSize;
        gp.healer[mapNum][i].worldY = 10 * gp.tileSize;
        i++;
    }

    // set monster to the game
    public void setMonster() {
        int mapNum = 0;

        int i = 0;
        gp.monster[mapNum][i] = new Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 21;
        gp.monster[mapNum][i].worldY = gp.tileSize * 38;
        i++;
        gp.monster[mapNum][i] = new Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 23;
        gp.monster[mapNum][i].worldY = gp.tileSize * 42;
        i++;
        gp.monster[mapNum][i] = new Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 24;
        gp.monster[mapNum][i].worldY = gp.tileSize * 37;
        i++;
        gp.monster[mapNum][i] = new Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 34;
        gp.monster[mapNum][i].worldY = gp.tileSize * 42;
        i++;
        gp.monster[mapNum][i] = new Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 38;
        gp.monster[mapNum][i].worldY = gp.tileSize * 42;
        i++;
        gp.monster[mapNum][i] = new ForestMonster(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 12;
        gp.monster[mapNum][i].worldY = gp.tileSize * 33;
        i++;

    }

    public void setInteractiveTile() {
        int mapNum = 0;
        int i = 0;
        gp.interactiveTile[mapNum][i] = new dryTree(gp, 27, 12);
        i++;
        gp.interactiveTile[mapNum][i] = new dryTree(gp, 28, 12);
        i++;
        gp.interactiveTile[mapNum][i] = new dryTree(gp, 29, 12);
        i++;
        gp.interactiveTile[mapNum][i] = new dryTree(gp, 30, 12);
        i++;
        gp.interactiveTile[mapNum][i] = new dryTree(gp, 31, 12);
        i++;
        gp.interactiveTile[mapNum][i] = new dryTree(gp, 32, 12);
        i++;
        gp.interactiveTile[mapNum][i] = new dryTree(gp, 33, 12);
        i++;

    }
}
