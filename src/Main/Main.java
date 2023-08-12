package Main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame("The Legend of Hyrule");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false); // window cannot be resized
        // window.setContentPane(new GamePanel());
        window.setTitle("The Legend of Hyrule");
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null); // window appears in the center of the screen
        window.setVisible(true);
        gamePanel.setUpGame();
        gamePanel.startGameThread();
    }
}

// video 31, 1:13