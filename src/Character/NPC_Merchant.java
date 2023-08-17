/**
 * The NPC_Merchant class is used to create a merchant character in a game, with properties such as
 * dialogue and inventory items.
 */
package Character;

import Main.GamePanel;
import Object.OBJ_Axe;
import Object.OBJ_BlackCrystal;
import Object.OBJ_Sword;

//NPC_Merchant class is used to create the merchant character and set its properties. 
//This class extends the Character class using inheritance.
public class NPC_Merchant extends Character {

    GamePanel gp;

    // constructor for the merchant class
    public NPC_Merchant(GamePanel gp) {
        super(gp);// call the super constructor
        this.gp = gp;// set the game panel
        direction = "down";// the merchant starts facing down
        speed = 1;// the merchant moves at a speed of 1

        getImage();// get the images for the merchant
        setDialogue();// set the dialogue for the merchant
        setItems();
    }

    // get the images for the merchant
    public void getImage() {

        // get merchant images
        up1 = setUp("./src/npc/merchant_down_1", gp.tileSize, gp.tileSize);
        up2 = setUp("./src/npc/merchant_down_2", gp.tileSize, gp.tileSize);
        down1 = setUp("./src/npc/merchant_down_1", gp.tileSize, gp.tileSize);
        down2 = setUp("./src/npc/merchant_down_2", gp.tileSize, gp.tileSize);
        left1 = setUp("./src/npc/merchant_down_1", gp.tileSize, gp.tileSize);
        left2 = setUp("./src/npc/merchant_down_2", gp.tileSize, gp.tileSize);
        right1 = setUp("./src/npc/merchant_down_1", gp.tileSize, gp.tileSize);
        right2 = setUp("./src/npc/merchant_down_2", gp.tileSize, gp.tileSize);

    }

    // set the dialogue for the merchant
    public void setDialogue() {
        // the merchant has dialogues
        dialogues[0] = "Howdy, stranger!\nI am the merchant of Minish Haven.\nDo you want to trade?";
    }

    public void setItems() {
        inventory.add(new OBJ_BlackCrystal(gp));
        inventory.add(new OBJ_Axe(gp));
        inventory.add(new OBJ_Sword(gp));
    }

    public void speak() {
        super.speak();
        gp.gameState = gp.tradeState;
        gp.ui.merchant = this;
    }

}
