package Tile;

import Main.GamePanel;
import Main.UtilityTool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
//import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[50];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("src/maps/worldV2.txt");
    }

    public void getTileImage() {
        /*
         * File file = new File("src/tiles/grass.png");
         * FileInputStream fis = new FileInputStream(file);
         * tile[0] = new Tile();
         * tile[0].image = ImageIO.read(fis);
         * setUp(0, "grass", false);
         * 
         * 
         * file = new File("src/tiles/wall.png");
         * fis = new FileInputStream(file);
         * tile[1] = new Tile();
         * tile[1].image = ImageIO.read(fis);
         * tile[1].collision = true;
         * 
         * file = new File("src/tiles/water.png");
         * fis = new FileInputStream(file);
         * tile[2] = new Tile();
         * tile[2].image = ImageIO.read(fis);
         * tile[2].collision = true;
         * 
         * file = new File("src/tiles/earth.png");
         * fis = new FileInputStream(file);
         * tile[3] = new Tile();
         * tile[3].image = ImageIO.read(fis);
         * 
         * file = new File("src/tiles/tree.png");
         * fis = new FileInputStream(file);
         * tile[4] = new Tile();
         * tile[4].image = ImageIO.read(fis);
         * tile[4].collision = true;
         * 
         * file = new File("src/tiles/sand.png");
         * fis = new FileInputStream(file);
         * tile[5] = new Tile();
         * tile[5].image = ImageIO.read(fis);
         */
        setUp(0, "grass00", false);
        setUp(1, "grass00", false);
        setUp(2, "grass00", false);
        setUp(3, "grass00", false);
        setUp(4, "grass00", false);
        setUp(5, "grass00", false);
        setUp(6, "grass00", false);
        setUp(7, "grass00", false);
        setUp(8, "grass00", false);
        setUp(9, "grass00", false);
        // place holder
        setUp(10, "grass00", false);
        setUp(11, "grass01", false);
        setUp(12, "water00", false);
        setUp(13, "water01", true);
        setUp(14, "water02", true);
        setUp(15, "water03", true);
        setUp(16, "water04", true);
        setUp(17, "water05", true);
        setUp(18, "water06", true);
        setUp(19, "water07", true);
        setUp(20, "water08", true);
        setUp(21, "water09", true);
        setUp(22, "water10", true);
        setUp(23, "water11", true);
        setUp(24, "water12", true);
        setUp(25, "water12", true);
        setUp(26, "road00", false);
        setUp(27, "road01", false);
        setUp(28, "road02", false);
        setUp(29, "road03", false);
        setUp(30, "road04", false);
        setUp(31, "road05", false);
        setUp(32, "road06", false);
        setUp(33, "road07", false);
        setUp(34, "road08", false);
        setUp(35, "road09", false);
        setUp(36, "road10", false);
        setUp(37, "road11", false);
        setUp(38, "road12", false);
        setUp(39, "earth", false);
        setUp(40, "wall", true);
        setUp(41, "tree01", true);
        //setUp(42, "tree", true);
        //setUp(43, "tree02", true);
        setUp(44, "ruins", false);
        //setUp(45, "ruins01", true);
        //setUp(46, "ruins02", true);

    }

    public void setUp(int index, String imageName, boolean collision) {
        UtilityTool ut = new UtilityTool();

        try {
            /*
             * tile[index]=new Tile();
             * tile[index].image = ImageIO.read(new File("/src/tiles/"+ imageName +
             * ".png"));
             * tile[index].image = ut.scaleImage(tile[index].image, gp.tileSize,
             * gp.tileSize);
             * tile[index].collision = collision;
             */

            // import image
            File file = new File("src/tiles/" + imageName + ".png");
            FileInputStream fis = new FileInputStream(file);
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(fis);
            tile[index].image = ut.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = new FileInputStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();

                while (col < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[row][col] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {

        }
    }

    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldRow][worldCol];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
            // g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize,
            // null);
            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;

            }
        }

    }
}
