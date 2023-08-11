package Object;

import Character.Character;
import Main.GamePanel;

public class OBJ_BlackCrystal extends Character {
    GamePanel gp;
    int healingValue = 5;
    public OBJ_BlackCrystal(GamePanel gp) {
        
        super(gp);
        this.gp = gp;
        type = crystalType;
        name = "black crystal";
        down1 = setUp("src/objects/black_crystal", gp.tileSize, gp.tileSize);

        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        description = "[" + name + "]\nPowerful black crystal\nthat can heal you by " + healingValue + " HP.";
    }
    public void use(Character Character){
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You used the " + name + ".\nYou healed by " + healingValue + " HP.";
        Character.life += healingValue;
        if(gp.player.life > gp.player.maxLife){
            gp.player.life = gp.player.maxLife;
        }
        gp.playSE(2);
    }
}
