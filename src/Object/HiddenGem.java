package Object;

import Main.GamePanel;
import Character.Character;

public class HiddenGem extends Character{
    GamePanel gp;
    //the player will have to find the hidden gem to save the village of Minish Haven
    //the hidden gem will be hidden in the top left corner of the map  where there is a locked door
    //the player will have to find the key to open the door and get the hidden gem

    //constructor
    public HiddenGem(GamePanel gp){
        super(gp);
        this.gp = gp;

        type = obstacleType;
        name = "precious gem";
        down1= setUp("src/objects/diamond", gp.tileSize, gp.tileSize);

        collision = true;

        solidArea.x=0;
        solidArea.y=16;
        solidArea.width=48;
        solidArea.height=32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
    public void interact(){
        //once player interacts with the hidden gem, the game will end.
        //a game winning dialogue will be displayed saying the player has saved the village of Minish Haven
        //the player will be able to restart the game or exit the game
        gp.gameState = gp.dialogueState;
        
        StringBuilder sb = new StringBuilder();
        gp.playSE(16);
        sb.append("You found the hidden gem!");
        sb.append("\n");
        sb.append("You saved the village of Minish Haven!");
        sb.append("\n");
        gp.ui.currentDialogue = sb.toString();
        //then return to the title screen
        gp.gameState = gp.titleState;
        
        
    }

    
}
