package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import Character.NPC_Sage;
import Character.Player;
import Character.Character;
import Character.Healer;
import Tile.TileManager;
import Tile_Interactive.interactiveTile;

public class GamePanel extends JPanel implements Runnable {

    // Screen Settings
    final int originalTileSize = 16; // original tile size
    final int scale = 3; // scale of the game

    public final int tileSize = originalTileSize * scale; // tile size
    public final int maxScreenCol = 20; // max number of columns on the screen
    public final int maxScreenRow = 12; // max number of rows on the screen
    public final int screenWidth = tileSize * maxScreenCol; // screen width, 20 tiles * 48 pixels = 960 pixels
    public final int screenHeight = tileSize * maxScreenRow; // screen height, 12 tiles * 48 pixels = 576 pixels

    // WORLD MAP SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int maxMap = 10;
    public int currentMap = 1;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    // FULL SCREEN SETTINGS
    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;
    public boolean fullScreenOn = false;

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
    Config config = new Config(this);
    Thread gameThread;

    // Character and Object
    public Player player = new Player(this, keyH);
    public Character obj[] = new Character[20];
    public NPC_Sage npc[] = new NPC_Sage[10];
    public Healer healer[] = new Healer[10];
    public Character monster[] = new Character[20];
    public interactiveTile interactiveTile[] = new interactiveTile[50];
    public ArrayList<Character> projectileList = new ArrayList<>();
    public ArrayList<Character> particleList = new ArrayList<>();
    ArrayList<Character> characterList = new ArrayList<Character>();

    // game state
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;
    public final int healingState = 5;
    public final int optionState = 6;
    public final int gameOverState = 7;

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
        assetSetter.setHealer();
        assetSetter.setMonster();
        assetSetter.setInteractiveTile();
        playMusic(0);
        stopMusic();
        gameState = titleState;

        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_RGB);
        g2 = (Graphics2D) tempScreen.getGraphics();

        if(fullScreenOn == true){
        setFullScreen();
        }
    }
    public void retry(){
        player.setDefaultPosition();
        player.restoreLifeandMana();
        assetSetter.setMonster();
        assetSetter.setNPC();
        assetSetter.setHealer();

    }
    public void restart(){
        player.setDefaultPosition();
        player.restoreLifeandMana();
        player.setItems();
        assetSetter.setMonster();
        assetSetter.setNPC();
        assetSetter.setHealer();
        assetSetter.setObject();
        assetSetter.setInteractiveTile();
        playMusic(0);
        stopMusic();
        gameState = titleState;
    }
    public void setFullScreen() {
       //Get local graphics environment
         GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //Get graphics device
            GraphicsDevice gd = ge.getDefaultScreenDevice();
            //Enter full screen mode
            gd.setFullScreenWindow(Main.window);
        //get full screen width and height
        screenWidth2 = Main.window.getWidth();
        screenHeight2 = Main.window.getHeight();
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

           //repaint();
           drawTempScreen(); //draw everything to the buffered image
           drawToScreen();//draw the buffered image to the screen

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

    // update method for the characterList
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
            // healer
            for (int i = 0; i < healer.length; i++) {
                if (healer[i] != null) {
                    healer[i].update();
                }
            }
            // monster
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    if (monster[i].alive == true && monster[i].dying == false) {
                        monster[i].update();
                    }
                    if (monster[i].alive == false) {
                        // check if monster drops item when it dies
                        monster[i].checkDrop();
                        monster[i] = null;
                    }
                    // monster[i].update();
                }
            }
            // projectile
            for (int i = 0; i < projectileList.size(); i++) {
                if (projectileList.get(i) != null) {
                    if (projectileList.get(i).alive == true) {
                        projectileList.get(i).update();
                    }
                    if (projectileList.get(i).alive == false) {
                        projectileList.remove(i);
                    }
                }
            }
            // particle
            for (int i = 0; i < particleList.size(); i++) {
                if (particleList.get(i) != null) {
                    if (particleList.get(i).alive == true) {
                        particleList.get(i).update();
                    }
                    if (particleList.get(i).alive == false) {
                        particleList.remove(i);
                    }
                }
            }
            // interactiveTile
            for (int i = 0; i < interactiveTile.length; i++) {
                if (interactiveTile[i] != null) {
                    interactiveTile[i].update();
                }
            }

        }
        if (gameState == pauseState) {
            // do nothing
        }
        // player.update();
    }

    public void drawTempScreen() {
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
            // interactiveTile
            for (int i = 0; i < interactiveTile.length; i++) {
                if (interactiveTile[i] != null) {
                    interactiveTile[i].draw(g2);
                }
            }
            // Add character to characterList
            characterList.add(player);
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    characterList.add(npc[i]);// add npc to characterList
                }
            }
            for (int i = 0; i < healer.length; i++) {
                if (healer[i] != null) {
                    characterList.add(healer[i]);// add healer to characterList
                }
            }
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    characterList.add(obj[i]);// add object to characterList
                }
            }
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    characterList.add(monster[i]); // add monster to characterListS
                }
            }
            for (int i = 0; i < projectileList.size(); i++) {
                if (projectileList.get(i) != null) {
                    characterList.add(projectileList.get(i));// add projectile to characterList
                }
            }
            for (int i = 0; i < particleList.size(); i++) {
                if (particleList.get(i) != null) {
                    characterList.add(particleList.get(i));// add particle to characterList
                }
            }
            // sort
            Collections.sort(characterList, new Comparator<Character>() {
                @Override
                public int compare(Character c1, Character c2) {
                    int result = Integer.compare(c1.worldY, c2.worldY);
                    return result;
                }
            });
            // draw characters
            for (int i = 0; i < characterList.size(); i++) {
                characterList.get(i).draw(g2);
            }
            // empty characterList
            characterList.clear();

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
    }

    /*public void paintComponent(Graphics g) {
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
            // interactiveTile
            for (int i = 0; i < interactiveTile.length; i++) {
                if (interactiveTile[i] != null) {
                    interactiveTile[i].draw(g2);
                }
            }
            // Add character to characterList
            characterList.add(player);
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    characterList.add(npc[i]);// add npc to characterList
                }
            }
            for (int i = 0; i < healer.length; i++) {
                if (healer[i] != null) {
                    characterList.add(healer[i]);// add healer to characterList
                }
            }
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    characterList.add(obj[i]);// add object to characterList
                }
            }
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    characterList.add(monster[i]); // add monster to characterListS
                }
            }
            for (int i = 0; i < projectileList.size(); i++) {
                if (projectileList.get(i) != null) {
                    characterList.add(projectileList.get(i));// add projectile to characterList
                }
            }
            for (int i = 0; i < particleList.size(); i++) {
                if (particleList.get(i) != null) {
                    characterList.add(particleList.get(i));// add particle to characterList
                }
            }
            // sort
            Collections.sort(characterList, new Comparator<Character>() {
                @Override
                public int compare(Character c1, Character c2) {
                    int result = Integer.compare(c1.worldY, c2.worldY);
                    return result;
                }
            });
            // draw characters
            for (int i = 0; i < characterList.size(); i++) {
                characterList.get(i).draw(g2);
            }
            // empty characterList
            characterList.clear();

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
    }*/
    public void drawToScreen(){
        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
        g.dispose();
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