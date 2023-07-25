package Main;

import Character.NPC_Sage;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        
    }

    public void setNPC() {
        
        //sage
        gp.npc[0] = new NPC_Sage(gp);
        gp.npc[0].worldX = 23 * gp.tileSize;
        gp.npc[0].worldY = 7 * gp.tileSize;
    }
}

// previous setObject method
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