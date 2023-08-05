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
