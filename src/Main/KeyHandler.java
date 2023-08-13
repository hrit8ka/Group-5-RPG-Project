package Main;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, shotKeyPressed;

    // debug
    boolean checkDrawTime = false;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // title state
        if (gp.gameState == gp.titleState) {
            titleState(code);
        }

        // play state
        else if (gp.gameState == gp.playState) {
            playState(code);
        }
        // pause state
        else if (gp.gameState == gp.pauseState) {
            pauseState(code);
        }
        // dialogue state
        else if (gp.gameState == gp.dialogueState) {
            dialogueState(code);
        }
        //healing state
        else if (gp.gameState == gp.healingState) {
            healingState(code);
        }
        // character state
        else if (gp.gameState == gp.characterState) {
            characterState(code);
        }
        // option state
        else if (gp.gameState == gp.optionState) {
            optionState(code);
        }

    }

    public void titleState(int code) {
        if (code == KeyEvent.VK_UP) {
            gp.ui.commandNumber--;
            if (gp.ui.commandNumber < 0) {
                gp.ui.commandNumber = 2;
            }
        }
        if (code == KeyEvent.VK_DOWN) {
            gp.ui.commandNumber++;
            if (gp.ui.commandNumber > 2) {
                gp.ui.commandNumber = 0;
            }
        }
        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNumber == 0) {
                gp.gameState = gp.playState;
                gp.playMusic(0);
            }
            if (gp.ui.commandNumber == 1) {
                System.exit(0);
                // add code to load game
            }
            if (gp.ui.commandNumber == 2) {
                System.exit(0);
            }
        }
    }

    public void playState(int code) {
        if (code == KeyEvent.VK_UP) {
            upPressed = true;
        } else if (code == KeyEvent.VK_DOWN) {
            downPressed = true;
        } else if (code == KeyEvent.VK_LEFT) {
            leftPressed = true;
        } else if (code == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        } else if (code == KeyEvent.VK_P) {
            if (gp.gameState == gp.playState) {
                gp.gameState = gp.pauseState;
            } else if (gp.gameState == gp.pauseState) {
                gp.gameState = gp.playState;
            }
        } else if (code == KeyEvent.VK_C) {
            gp.gameState = gp.characterState;
        } else if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }else if(code == KeyEvent.VK_F){
            shotKeyPressed = true;
        }else if(code == KeyEvent.VK_ESCAPE){
            gp.gameState =gp.optionState;
        }

        // debug
        if (code == KeyEvent.VK_T) {
            if (checkDrawTime == false) {
                checkDrawTime = true;
            } else if (checkDrawTime == true) {
                checkDrawTime = false;
            }
        }
    }

    public void pauseState(int code) {
        if (code == KeyEvent.VK_P) {
            gp.gameState = gp.playState;
        }
    }

    public void dialogueState(int code) {
        if (code == KeyEvent.VK_ENTER) {
            gp.gameState = gp.playState;
        }
    }
    public void healingState(int code) {
        if (code == KeyEvent.VK_ENTER) {
            gp.gameState = gp.playState;
        }
    }

    public void characterState(int code) {
        if (code == KeyEvent.VK_C) {
            gp.gameState = gp.playState;
        }
        // move cursor in inventory frame
        if (code == KeyEvent.VK_UP) {
            if (gp.ui.slotRow != 0) {
                gp.ui.slotRow--;
                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_DOWN) {
            if (gp.ui.slotRow != 3) {
                gp.ui.slotRow++;
                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_LEFT) {
            if (gp.ui.slotColumn != 0) {
                gp.ui.slotColumn--;
                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_RIGHT) {
            if (gp.ui.slotColumn != 4) {
                gp.ui.slotColumn++;
                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_ENTER) {
            gp.player.selectItem();
            gp.playSE(8);
        }

    }
    private void optionState(int code) {
        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }

        int maxCommandNumber = 0;
        switch(gp.ui.subState){
            case 0:
                maxCommandNumber = 7;
        }

        if(code == KeyEvent.VK_UP){
           gp.ui.commandNumber --;
           gp.playSE(9);
           if(gp.ui.commandNumber < 0){
            gp.ui.commandNumber = maxCommandNumber;
           }
           //reset command number
        }
        if(code == KeyEvent.VK_DOWN){
            gp.ui.commandNumber ++;
            gp.playSE(9);
            if(gp.ui.commandNumber > maxCommandNumber){
                gp.ui.commandNumber = 0;
            }
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP) {
            upPressed = false;
        } else if (code == KeyEvent.VK_DOWN) {
            downPressed = false;
        } else if (code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        } else if (code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_F) {
            enterPressed = false;
        }

    }
}
