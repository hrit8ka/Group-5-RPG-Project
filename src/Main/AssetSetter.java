package Main;

import Character.NPC_Sage;
import Monster.Slime;
import Object.OBJ_Axe;
import Object.OBJ_BlackCrystal;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
       
        int i = 0;
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
        gp.obj[i] = new OBJ_Axe(gp);
        gp.obj[i].worldX = gp.tileSize * 33;
        gp.obj[i].worldY = gp.tileSize * 21;
        i++;
        
    }
    // to set the NPC in the game, call this method in the setUpGame() method in
    // GamePanel.java
    public void setNPC() {
        gp.npc[0] = new NPC_Sage(gp); // create a new NPC object
        gp.npc[0].worldX = 23 * gp.tileSize;
        gp.npc[0].worldY = 23 * gp.tileSize;
    }

    public void setMonster(){

        int i=0;
        gp.monster[i] = new Slime(gp);
        gp.monster[i].worldX = gp.tileSize * 21;
        gp.monster[i].worldY = gp.tileSize * 38;
        i++;
        gp.monster[i] = new Slime(gp);
        gp.monster[i].worldX = gp.tileSize * 23;
        gp.monster[i].worldY =gp.tileSize * 42;
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
        //System.out.println("Monsters set up:");
        //System.out.println(gp.monster[0]);
        //System.out.println(gp.monster[1]);

    }
}
