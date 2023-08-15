package Object;

import Main.GamePanel;
import Character.Character;

public class House extends Character {
    GamePanel gp;

    public House(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = consumableType;
        name = "house";
        down1 = setUp("./src/objects/house", gp.tileSize, gp.tileSize);
        description ="["+name+"]\n\nA house. You can rest here.";
        price = 300;
        stackable = true;

    }
    public boolean use(Character character){
        
    }

}
