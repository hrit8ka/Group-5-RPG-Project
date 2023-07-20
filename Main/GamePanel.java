package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Character.Player;

public class GamePanel extends JPanel implements Runnable {

    // Screen Settings
    final int originalTileSize = 16; // original tile size
    final int scale = 3; // scale of the game

    public final int tileSize = originalTileSize * scale; // tile size
    final int maxScreenCol = 16; // max number of columns on the screen
    final int maxScreenRow = 12; // max number of rows on the screen
    final int screenWidth = tileSize * maxScreenCol; // screen width
    final int screenHeight = tileSize * maxScreenRow; // screen height

    int FPS = 60; // frames per second

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);

    // set player's default position
    //int playerX = 100;
    //int playerY = 100;

    //int playerSpeed = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        setFocusable(true);
        requestFocus();
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
        //g2.setColor(Color.WHITE);
        //g2.fillRect(playerX, playerY, tileSize, tileSize);
        player.draw(g2);
        g2.dispose();
    }

}