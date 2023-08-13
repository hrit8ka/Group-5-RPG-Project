package Tile_Interactive;

import Main.GamePanel;
import Character.Character;
public class dryTree extends interactiveTile {

    GamePanel gp;

    public dryTree(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;
        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;
        
        down1 = setUp("./src/tiles_interactive/drytree", gp.tileSize, gp.tileSize);
        destructible = true;
    }
    public boolean isCorrectItem(Character Character){
        boolean isCorrectItem = false;
        if(Character.currentWeapon.type== axeType){
            isCorrectItem = true;
        }
        return isCorrectItem;
    }
    public void playSE(){
        gp.playSE(12);
    }
    public interactiveTile getDestroyedTile(){
        interactiveTile tile = new Trunk(gp, worldX/gp.tileSize, worldY/gp.tileSize);
        return tile;
    }
}
