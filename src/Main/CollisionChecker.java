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

        // checking if the character is colliding with a solid tile, if so, the
        // character will not move
        switch (Character.direction) {
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

    public int checkObject(Character Character, boolean player) {
        int index = 999;
        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] != null) {
                // get character's solid area position
                Character.solidArea.x = Character.worldX + Character.solidArea.x;
                Character.solidArea.y = Character.worldY + Character.solidArea.y;
                // get object's solid area position
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                // check if the character is colliding with an object
                switch (Character.direction) {
                    case "up":
                        Character.solidArea.y -= Character.speed;
                        break;
                    case "down":
                        Character.solidArea.y += Character.speed;
                        break;
                    case "left":
                        Character.solidArea.x -= Character.speed;
                        break;
                    case "right":
                        Character.solidArea.x += Character.speed;
                        break;
                }
                if (Character.solidArea.intersects(gp.obj[i].solidArea)) {
                    if (gp.obj[i].collision == true) {
                        Character.collisionOn = true;
                    }
                    if (player == true) {
                        index = i;
                    }
                }
                Character.solidArea.x = Character.solidAreaDefaultX;
                Character.solidArea.y = Character.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }
        return index;

    }

    // check NPC or monster collision
    public int checkCharacter(Character character, Character[] target) {
        int index = 999;
        for (int i = 0; i < target.length; i++) {
            if (target[i] != null) {
                // get character's solid area position
                character.solidArea.x = character.worldX + character.solidArea.x;
                character.solidArea.y = character.worldY + character.solidArea.y;

                // get object's solid area position
                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

                // check if the character is colliding with an object
                switch (character.direction) {
                    case "up":
                        character.solidArea.y -= character.speed;
                        break;
                    case "down":
                        character.solidArea.y += character.speed;
                        break;
                    case "left":
                        character.solidArea.x -= character.speed;
                        break;
                    case "right":
                        character.solidArea.x += character.speed;
                        break;
                }
                if (character.solidArea.intersects(target[i].solidArea)) {
                    if(target[i] != character){
                    character.collisionOn = true;
                    index = i;
                    }
                }
                character.solidArea.x = character.solidAreaDefaultX;
                character.solidArea.y = character.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }
        return index;
    }

    public void checkPlayer(Character character) {
        // get character's solid area position
        character.solidArea.x = character.worldX + character.solidArea.x;
        character.solidArea.y = character.worldY + character.solidArea.y;

        // get object's solid area position
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        // check if the character is colliding with an object
        switch (character.direction) {
            case "up":
                character.solidArea.y -= character.speed;
                break;
            case "down":
                character.solidArea.y += character.speed;
                break;
            case "left":
                character.solidArea.x -= character.speed;
                break;
            case "right":
                character.solidArea.x += character.speed;
                break;

        }
        if (character.solidArea.intersects(gp.player.solidArea)) {
                    character.collisionOn = true;
                }
        character.solidArea.x = character.solidAreaDefaultX;
        character.solidArea.y = character.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;

    }
}
