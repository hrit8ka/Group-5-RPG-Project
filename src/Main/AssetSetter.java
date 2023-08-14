package Main;

import Character.Healer;
import Character.NPC_Sage;
import Monster.Slime;
import Object.Coin;
import Object.OBJ_Axe;
import Object.OBJ_BlackCrystal;
import Object.OBJ_BlueShield;
import Object.OBJ_Heart;
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
        //add black crystal
        gp.obj[mapNum][i] = new OBJ_BlackCrystal(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 21;
        gp.obj[mapNum][i].worldY = gp.tileSize * 23;
        i++;
        gp.obj[mapNum][i] = new OBJ_BlackCrystal(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 21;
        gp.obj[mapNum][i].worldY = gp.tileSize * 19;
        i++;
        gp.obj[mapNum][i] = new OBJ_BlackCrystal(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 26;
        gp.obj[mapNum][i].worldY = gp.tileSize * 21;
        i++;
        //add axe
        gp.obj[mapNum][i] = new OBJ_Axe(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 33;
        gp.obj[mapNum][i].worldY = gp.tileSize * 21;
        i++;
        //add blue shield
        gp.obj[mapNum][i] = new OBJ_BlueShield(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 35;
        gp.obj[mapNum][i].worldY = gp.tileSize * 21;

        //add gold coins
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

        //set heart
        gp.obj[mapNum][i] = new OBJ_Heart(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 22;
        gp.obj[mapNum][i].worldY = gp.tileSize * 27;
        i++;
        //set mana
        gp.obj[mapNum][i] = new OBJ_Mana(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 22;
        gp.obj[mapNum][i].worldY = gp.tileSize * 29;
        i++;
    }

    // to set the NPC in the game, call this method in the setUpGame() method in
    // GamePanel.java
    public void setNPC() {
        int mapNum = 0;
        gp.npc[mapNum][0] = new NPC_Sage(gp); // create a new NPC object
        gp.npc[mapNum][0].worldX = 23 * gp.tileSize;
        gp.npc[mapNum][0].worldY = 23 * gp.tileSize;

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
        // System.out.println("Monsters set up:");
        // System.out.println(gp.monster[0]);
        // System.out.println(gp.monster[1]);

        //mapNum = 1;

    }

    public void setInteractiveTile(){
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
