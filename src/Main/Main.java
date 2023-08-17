/**
 * The Main class creates a window for the game and starts the game.
 */
package Main;

import javax.swing.JFrame;

//Main class is used to create the window for the game and start the game. It is the main class of the game.
public class Main {
    // main method is used to create the window for the game and start the game
    public static JFrame window;

    public static void main(String[] args) {
        window = new JFrame("The Legend of Hyrule");// create window and set title of frame
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// close window when x is clicked
        window.setResizable(false); // window cannot be resized
        window.setTitle("The Legend of Hyrule");// set title of window
        GamePanel gamePanel = new GamePanel();// create game panel
        window.add(gamePanel);// add game panel to window

        gamePanel.config.loadConfig();// load config
        if (gamePanel.fullScreenOn == true) {
            window.setExtendedState(JFrame.MAXIMIZED_BOTH);// set window to full screen
        }

        window.pack();// pack window

        window.setLocationRelativeTo(null); // window appears in the center of the screen
        window.setVisible(true);// make window visible
        gamePanel.setUpGame();// set up game panel
        gamePanel.startGameThread();// start game thread to start game
    }

    public interface Character {
    }
}