package Main;

import Character.Character;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Character Character) {
        int CharacterLeftWorldX = Character.worldX + Character.solidArea.x;
        int CharacterRightWorldX = Character.worldX + Character.solidArea.x + Character.solidArea.width;
        int CharacterTopWorldY = Character.worldY + Character.solidArea.y;
        int CharacterBottomWorldY = Character.worldY + Character.solidArea.y + Character.solidArea.height;

        int CharacterLeftCol = CharacterLeftWorldX / gp.tileSize;
        int CharacterRightCol = CharacterRightWorldX / gp.tileSize;
        int CharacterTopRow = CharacterTopWorldY / gp.tileSize;
        int CharacterBottomRow = CharacterBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        switch (Character.direction) {
            case "up":
                CharacterTopRow = (CharacterTopWorldY - Character.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[CharacterLeftCol][CharacterTopRow];
                tileNum2 = gp.tileM.mapTileNum[CharacterRightCol][CharacterTopRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    Character.collisionOn = true;
                }
                break;
            case "down":
                CharacterBottomRow = (CharacterBottomWorldY + Character.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[CharacterLeftCol][CharacterBottomRow];
                tileNum2 = gp.tileM.mapTileNum[CharacterRightCol][CharacterBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    Character.collisionOn = true;
                }
                break;
            case "left":
                CharacterLeftCol = (CharacterLeftWorldX - Character.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[CharacterLeftCol][CharacterTopRow];
                tileNum2 = gp.tileM.mapTileNum[CharacterLeftCol][CharacterBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    Character.collisionOn = true;
                }
                break;
            case "right":
                CharacterRightCol = (CharacterRightWorldX + Character.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[CharacterRightCol][CharacterTopRow];
                tileNum2 = gp.tileM.mapTileNum[CharacterRightCol][CharacterBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    Character.collisionOn = true;
                }
                break;

        }

    }

}
