/**
 * The `dryTree` class is a subclass of `interactiveTile` that represents a dry tree in a game, and it
 * has methods for checking if the correct item is used to interact with it, playing sound effects,
 * getting the color, size, speed, and max life of particles, and getting the destroyed tile when the
 * tree is destroyed.
 */
package Tile_Interactive;

import Main.GamePanel;

import java.awt.Color;

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

    public Color getParticleColor() {
        Color color = new Color(65, 50, 30);//brown color
        return color;
    }

    public int getParticleSize() {
        int size = 6; // size of the particle 6 pixels
        return size;
    }

    public int getParticleSpeed() {
        int speed = 2; // speed of the particle 2 pixels per frame
        return speed;
    }

    public int getParticleMaxLife(){
        int maxLife = 20; // max life of the particle 20 frames
        return maxLife;
    }

}
