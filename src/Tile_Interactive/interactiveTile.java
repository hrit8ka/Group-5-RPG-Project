/**
 * The interactiveTile class is a subclass of Character that represents a tile in a game that can be
 * interacted with and potentially destroyed.
 */
package Tile_Interactive;

import Character.Character;
import Main.GamePanel;

public class interactiveTile extends Character{
   
    GamePanel gp;
    public boolean destructible = false;

    public interactiveTile(GamePanel gp, int col, int row) {
        super(gp);
        this.gp = gp;
    }
    public boolean isCorrectItem(Character Character){
        boolean isCorrectItem = false;
        return isCorrectItem;
    }
    public void playSE(){

    }
    public interactiveTile getDestroyedTile(){
        interactiveTile tile = null;
        return tile;
    }
   public void update(){

   }



}
