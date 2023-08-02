package Main;

import Character.NPC_Sage;
import Monster.Slime;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

    }
    // to set the NPC in the game, call this method in the setUpGame() method in
    // GamePanel.java
    public void setNPC() {
        gp.npc[0] = new NPC_Sage(gp); // create a new NPC object
        gp.npc[0].worldX = 23 * gp.tileSize;
        gp.npc[0].worldY = 23 * gp.tileSize;
    }

    public void setMonster(){
        gp.monster[0] = new Slime(gp);
        gp.monster[0].worldX = gp.tileSize * 23;
        gp.monster[0].worldY = gp.tileSize * 36;

        gp.monster[1] = new Slime(gp);
        gp.monster[1].worldX = gp.tileSize * 23;
        gp.monster[1].worldY =gp.tileSize * 37;

        //System.out.println("Monsters set up:");
        //System.out.println(gp.monster[0]);
        //System.out.println(gp.monster[1]);

    }
}

// previous object setter
/*
 * gp.obj[0] = new OBJ_Key(gp);
 * gp.obj[0].worldX = 23 * gp.tileSize;
 * gp.obj[0].worldY = 7 * gp.tileSize;
 * 
 * gp.obj[1] = new OBJ_Key(gp);
 * gp.obj[1].worldX = 23 * gp.tileSize;
 * gp.obj[1].worldY = 40 * gp.tileSize;
 * 
 * gp.obj[2] = new OBJ_Key(gp);
 * gp.obj[2].worldX = 37 * gp.tileSize;
 * gp.obj[2].worldY = 7 * gp.tileSize;
 * 
 * gp.obj[3] = new OBJ_Door(gp);
 * gp.obj[3].worldX = 10 * gp.tileSize;
 * gp.obj[3].worldY = 9 * gp.tileSize;
 * 
 * gp.obj[4] = new OBJ_Door(gp);
 * gp.obj[4].worldX = 8 * gp.tileSize;
 * gp.obj[4].worldY = 28 * gp.tileSize;
 * 
 * gp.obj[5] = new OBJ_Door(gp);
 * gp.obj[5].worldX = 12 * gp.tileSize;
 * gp.obj[5].worldY = 22 * gp.tileSize;
 * 
 * gp.obj[6] = new OBJ_Chest(gp);
 * gp.obj[6].worldX = 10 * gp.tileSize;
 * gp.obj[6].worldY = 7 * gp.tileSize;
 * 
 * gp.obj[7] = new OBJ_Boots(gp);
 * gp.obj[7].worldX = 37 * gp.tileSize;
 * gp.obj[7].worldY = 42 * gp.tileSize;
 */