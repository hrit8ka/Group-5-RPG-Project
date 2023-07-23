package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Character.Player;
import Tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

    // Screen Settings
    final int originalTileSize = 16; // original tile size
    final int scale = 3; // scale of the game

    public final int tileSize = originalTileSize * scale; // tile size
    public final int maxScreenCol = 16; // max number of columns on the screen
    public final int maxScreenRow = 12; // max number of rows on the screen
    public final int screenWidth = tileSize * maxScreenCol; // screen width
    public final int screenHeight = tileSize * maxScreenRow; // screen height

    int FPS = 60; // frames per second
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);

    // set player's default position
    // int playerX = 100;
    // int playerY = 100;

    // int playerSpeed = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        // requestFocus();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {

            update();

            repaint();

            try {

                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }
    }

    public void update() {

        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        // g2.setColor(Color.WHITE);
        // g2.fillRect(playerX, playerY, tileSize, tileSize);
        tileM.draw(g2); //tile is drawn before player so as to be behind the player
        player.draw(g2);
        g2.dispose();
    }

}