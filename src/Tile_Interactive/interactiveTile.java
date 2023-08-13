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

   public void update(){

   }



}
