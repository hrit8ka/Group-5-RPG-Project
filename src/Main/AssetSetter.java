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

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

        int i = 0;
        //add black crystal
        gp.obj[i] = new OBJ_BlackCrystal(gp);
        gp.obj[i].worldX = gp.tileSize * 21;
        gp.obj[i].worldY = gp.tileSize * 23;
        i++;
        gp.obj[i] = new OBJ_BlackCrystal(gp);
        gp.obj[i].worldX = gp.tileSize * 21;
        gp.obj[i].worldY = gp.tileSize * 19;
        i++;
        gp.obj[i] = new OBJ_BlackCrystal(gp);
        gp.obj[i].worldX = gp.tileSize * 26;
        gp.obj[i].worldY = gp.tileSize * 21;
        i++;
        //add axe
        gp.obj[i] = new OBJ_Axe(gp);
        gp.obj[i].worldX = gp.tileSize * 33;
        gp.obj[i].worldY = gp.tileSize * 21;
        i++;
        //add blue shield
        gp.obj[i] = new OBJ_BlueShield(gp);
        gp.obj[i].worldX = gp.tileSize * 35;
        gp.obj[i].worldY = gp.tileSize * 21;

        //add gold coins
        i++;
        gp.obj[i] = new Coin(gp);
        gp.obj[i].worldX = gp.tileSize * 21;
        gp.obj[i].worldY = gp.tileSize * 21;
        i++;
        gp.obj[i] = new Coin(gp);
        gp.obj[i].worldX = gp.tileSize * 21;
        gp.obj[i].worldY = gp.tileSize * 17;
        i++;
        gp.obj[i] = new Coin(gp);
        gp.obj[i].worldX = gp.tileSize * 26;
        gp.obj[i].worldY = gp.tileSize * 19;
        i++;

        //set heart
        gp.obj[i] = new OBJ_Heart(gp);
        gp.obj[i].worldX = gp.tileSize * 22;
        gp.obj[i].worldY = gp.tileSize * 27;
        i++;
        //set mana
        gp.obj[i] = new OBJ_Mana(gp);
        gp.obj[i].worldX = gp.tileSize * 22;
        gp.obj[i].worldY = gp.tileSize * 29;
        i++;
    }

    // to set the NPC in the game, call this method in the setUpGame() method in
    // GamePanel.java
    public void setNPC() {
        gp.npc[0] = new NPC_Sage(gp); // create a new NPC object
        gp.npc[0].worldX = 23 * gp.tileSize;
        gp.npc[0].worldY = 23 * gp.tileSize;

    }

    // set healer in the game
    public void setHealer() {
        int i = 0;
        gp.healer[i] = new Healer(gp);
        gp.healer[i].worldX = 37 * gp.tileSize;
        gp.healer[i].worldY = 10 * gp.tileSize;
        i++;
    }

    // set monster to the game
    public void setMonster() {

        int i = 0;
        gp.monster[i] = new Slime(gp);
        gp.monster[i].worldX = gp.tileSize * 21;
        gp.monster[i].worldY = gp.tileSize * 38;
        i++;
        gp.monster[i] = new Slime(gp);
        gp.monster[i].worldX = gp.tileSize * 23;
        gp.monster[i].worldY = gp.tileSize * 42;
        i++;
        gp.monster[i] = new Slime(gp);
        gp.monster[i].worldX = gp.tileSize * 24;
        gp.monster[i].worldY = gp.tileSize * 37;
        i++;
        gp.monster[i] = new Slime(gp);
        gp.monster[i].worldX = gp.tileSize * 34;
        gp.monster[i].worldY = gp.tileSize * 42;
        i++;
        gp.monster[i] = new Slime(gp);
        gp.monster[i].worldX = gp.tileSize * 38;
        gp.monster[i].worldY = gp.tileSize * 42;
        i++;
        // System.out.println("Monsters set up:");
        // System.out.println(gp.monster[0]);
        // System.out.println(gp.monster[1]);

    }
}
