package Object;

import Character.Character;

import Main.GamePanel;

public class OBJ_Chest extends Character {
    GamePanel gp;

    public OBJ_Chest(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = obstacleType;
        name = "chest";
        image = setUp("src/objects/chest", gp.tileSize, gp.tileSize);
        image2 = setUp("src/objects/chest_opened", gp.tileSize, gp.tileSize);
        down1 = image;
        collision = true;

        solidArea.x = 4;
        solidArea.y = 16;
        solidArea.width = 40;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void setLoot(Character loot) {
        this.loot = loot;
    }

    public void interact() {
        gp.gameState = gp.dialogueState;

        if (opened == false) {
            gp.playSE(3);

            StringBuilder sb = new StringBuilder();
            sb.append("You found a ");
            sb.append(loot.name);
            sb.append("!");

            if (gp.player.canObtainItem(loot) == false) {
                sb.append("\nBut your inventory is full.");
            } else {
                sb.append("\nThe " + loot.name + " was added to your inventory.");
                down1 = image2;
                opened = true;
            }
            gp.ui.currentDialogue = sb.toString();
        } else {
            gp.ui.currentDialogue = "The chest is empty.";
        }
    }
}
