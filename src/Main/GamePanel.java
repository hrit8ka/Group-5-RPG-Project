package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import Character.NPC_Sage;
import Character.Player;
import Character.Character;
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

    // WORLD MAP SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    // fps
    int FPS = 60;
    // System
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound soundEffect = new Sound();
    // Thread gameThread;
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eventHandler = new EventHandler(this);
    Thread gameThread;

    // Character and Object
    public Player player = new Player(this, keyH);
    public Character obj[] = new Character[10];
    public NPC_Sage npc[] = new NPC_Sage[10];
    ArrayList<Character> characterList = new ArrayList<Character>();

    // game state
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        // requestFocus();
    }

    public void setUpGame() {
        assetSetter.setObject();
        assetSetter.setNPC();
        playMusic(0);
        stopMusic();
        gameState = titleState;
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
        if (gameState == playState) {
            // player
            player.update();
            // npc
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].update();
                }
            }
        }
        if (gameState == pauseState) {
            // do nothing
        }
        // player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // debug
        long drawStart = 0;
        if (keyH.checkDrawTime == true) {
            drawStart = System.nanoTime();
        }
        // drawStart = System.nanoTime();

        // title screen
        if (gameState == titleState) {
            ui.draw(g2);

        }
        // others
        else {
            // Tile
            tileM.draw(g2); // tile is drawn before player so as to be behind the player
            // Add character to characterList
            characterList.add(player);
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    characterList.add(npc[i]);
                }
            }
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    characterList.add(obj[i]);
                }
            }
            //sort
            Collections.sort(characterList, new Comparator<Character>() {
                @Override
                public int compare(Character c1, Character c2) {
                    int result = Integer.compare(c1.worldY, c2.worldY);
                    return result;
                }
            });
            //draw characters
            for(int i=0; i<characterList.size(); i++){
                characterList.get(i).draw(g2);
            }
            //empty characterList
            for(int i=0; i<characterList.size(); i++){
                characterList.remove(i);
            }

            // UI
            ui.draw(g2);
        }
        // debug
        if (keyH.checkDrawTime == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.drawString("Draw time: " + passed, 10, 400);
            System.out.println("Draw time: " + passed);
        }

        g2.dispose();
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSE(int i) {
        soundEffect.setFile(i);
        soundEffect.play();
    }

}