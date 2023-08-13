package Tile_Interactive;

import Main.GamePanel;

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
}
