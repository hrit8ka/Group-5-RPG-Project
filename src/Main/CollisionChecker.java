package Main;

import Character.Character;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Character Character) {
        int CharacterLeftWorldX=Character.worldX + Character.solidArea.x;
        int CharacterRightWorldX=Character.worldX + Character.solidArea.x + Character.solidArea.width;
        int CharacterTopWorldY=Character.worldY + Character.solidArea.y;
        int CharacterBottomWorldY=Character.worldY + Character.solidArea.y + Character.solidArea.height;

        int CharacterLeftCol = CharacterLeftWorldX / gp.tileSize;
        int CharacterRightCol = CharacterRightWorldX / gp.tileSize;
        int CharacterTopRow = CharacterTopWorldY / gp.tileSize;
        int CharacterBottomRow = CharacterBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;
        
    }

}
