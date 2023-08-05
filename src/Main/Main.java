package Main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame("RPG");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false); // window cannot be resized
        // window.setContentPane(new GamePanel());
        window.setTitle("RPG");
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null); // window appears in the center of the screen
        window.setVisible(true);
        gamePanel.setUpGame();
        gamePanel.startGameThread();
    }
}

//video 25, reached 16:04