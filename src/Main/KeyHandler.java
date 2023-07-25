package Main;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    //debug
    boolean checkDrawTime = false;

    public KeyHandler(GamePanel gp){
        this.gp=gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP) {
            upPressed = true;
        }
        else if (code == KeyEvent.VK_DOWN) {
            downPressed = true;            
        }
        else if (code == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        else if (code == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
        else if (code == KeyEvent.VK_P) {
            if(gp.gameState == gp.playState){
                gp.gameState = gp.pauseState;
            }
            else if(gp.gameState == gp.pauseState){
                gp.gameState = gp.playState;
            }
        }

        //debug
        if(code == KeyEvent.VK_T) {
           if(checkDrawTime == false) {
               checkDrawTime = true;
           }
           else if(checkDrawTime == true){
                checkDrawTime = false;
           }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP) {
            upPressed = false;
        }
        else if (code == KeyEvent.VK_DOWN) {
            downPressed = false;            
        }
        else if (code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        else if (code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }

    }    
}
