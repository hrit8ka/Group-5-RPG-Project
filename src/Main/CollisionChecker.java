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

        // checking if the character is colliding with a solid tile, if so, the character will not move
        switch(Character.direction) {
            case "up":
                tileNum1 = gp.tileM.mapTileNum[CharacterTopRow][CharacterLeftCol];
                tileNum2 = gp.tileM.mapTileNum[CharacterTopRow][CharacterRightCol];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    Character.worldY += Character.speed;
                }
                break;
            case "down":
                tileNum1 = gp.tileM.mapTileNum[CharacterBottomRow][CharacterLeftCol];
                tileNum2 = gp.tileM.mapTileNum[CharacterBottomRow][CharacterRightCol];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    Character.worldY -= Character.speed;
                }
                break;
            case "left":
                tileNum1 = gp.tileM.mapTileNum[CharacterTopRow][CharacterLeftCol];
                tileNum2 = gp.tileM.mapTileNum[CharacterBottomRow][CharacterLeftCol];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    Character.worldX += Character.speed;
                }
                break;
            case "right":
                tileNum1 = gp.tileM.mapTileNum[CharacterTopRow][CharacterRightCol];
                tileNum2 = gp.tileM.mapTileNum[CharacterBottomRow][CharacterRightCol];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    Character.worldX -= Character.speed;
                }
                break;
        }
    }
    public int checkObject(Character Character, boolean player){
        int index = 999;
        return index;
        
    }

}
