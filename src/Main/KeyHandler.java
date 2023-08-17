package Main;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, shotKeyPressed, spacePressed;

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
        // healing state
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
        // game over state
        else if (gp.gameState == gp.gameOverState) {
            gameOverState(code);
        }
        // trade state
        else if (gp.gameState == gp.tradeState) {
            tradeState(code);
        }
        // map state
        else if (gp.gameState == gp.mapState) {
            mapState(code);
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
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        } else if (code == KeyEvent.VK_S) {
            downPressed = true;
        } else if (code == KeyEvent.VK_A) {
            leftPressed = true;
        } else if (code == KeyEvent.VK_D) {
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
        } else if (code == KeyEvent.VK_F) {
            shotKeyPressed = true;
        } else if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.optionState;
        } else if (code == KeyEvent.VK_M) {
            gp.gameState = gp.mapState;

        } else if (code == KeyEvent.VK_N) {
            if (gp.map.miniMapOn == false) {
                gp.map.miniMapOn = true;
            } else {
                gp.map.miniMapOn = false;
            }
        } else if (code == KeyEvent.VK_SPACE) {
            spacePressed = true;
        }

        // debug
        if (code == KeyEvent.VK_T) {
            if (checkDrawTime == false) {
                checkDrawTime = true;
            } else if (checkDrawTime == true) {
                checkDrawTime = false;
            }
        }
        if (code == KeyEvent.VK_R) {
            switch (gp.currentMap) {
                case 0:
                    gp.tileM.loadMap("src/maps/worldV2.txt", 0);
                    break;
                case 1:
                    gp.tileM.loadMap("src/maps/interior01.txt", 1);
                    break;

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
        if (code == KeyEvent.VK_ENTER) {
            gp.player.selectItem();// select item in inventory
            gp.playSE(8);
        }
        // move cursor in inventory frame
        playerInventory(code);

    }

    private void optionState(int code) {
        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }

        int maxCommandNumber = 0;
        switch (gp.ui.subState) {
            case 0:
                maxCommandNumber = 5;
                break;
            case 3:
                maxCommandNumber = 1;
                break;
        }

        if (code == KeyEvent.VK_UP) {
            gp.ui.commandNumber--;
            gp.playSE(9);
            if (gp.ui.commandNumber < 0) {
                gp.ui.commandNumber = maxCommandNumber;
            }
        }
        if (code == KeyEvent.VK_DOWN) {
            gp.ui.commandNumber++;
            gp.playSE(9);
            if (gp.ui.commandNumber > maxCommandNumber) {
                gp.ui.commandNumber = 0;
            }
        }
        if (code == KeyEvent.VK_LEFT) {
            if (gp.ui.subState == 0) {
                if (gp.ui.commandNumber == 1 && gp.music.volumeScale > 0) {
                    gp.music.volumeScale--;
                    gp.music.checkVolume();
                    gp.playSE(9);
                }
                // sound effect
                if (gp.ui.commandNumber == 2 && gp.soundEffect.volumeScale > 0) {
                    gp.soundEffect.volumeScale--;
                    gp.playSE(9);
                }
            }
        }
        if (code == KeyEvent.VK_RIGHT) {
            if (gp.ui.subState == 0) {
                if (gp.ui.commandNumber == 1 && gp.music.volumeScale < 5) {
                    gp.music.volumeScale++;
                    gp.music.checkVolume();
                    gp.playSE(9);
                }
                // sound effect
                if (gp.ui.commandNumber == 2 && gp.soundEffect.volumeScale < 5) {
                    gp.soundEffect.volumeScale++;
                    gp.playSE(9);
                }
            }
        }

    }

    public void gameOverState(int code) {
        if (code == KeyEvent.VK_UP) {
            gp.ui.commandNumber--;
            if (gp.ui.commandNumber < 0) {
                gp.ui.commandNumber = 1;
            }
            gp.playSE(9);
        }
        if (code == KeyEvent.VK_DOWN) {
            gp.ui.commandNumber++;
            if (gp.ui.commandNumber > 1) {
                gp.ui.commandNumber = 0;
            }
            gp.playSE(9);
        }
        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNumber == 0) {
                gp.gameState = gp.playState;
                gp.retry();
            } else if (gp.ui.commandNumber == 1) {
                gp.gameState = gp.titleState;
                gp.restart();
            }
        }

    }

    public void tradeState(int code) {
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        if (gp.ui.subState == 0) {
            if (code == KeyEvent.VK_UP) {
                gp.ui.commandNumber--;
                if (gp.ui.commandNumber < 0) {
                    gp.ui.commandNumber = 2;
                }
                gp.playSE(9);
            }
            if (code == KeyEvent.VK_DOWN) {
                gp.ui.commandNumber++;
                if (gp.ui.commandNumber > 2) {
                    gp.ui.commandNumber = 0;
                }
                gp.playSE(9);
            }
        }
        if (gp.ui.subState == 1) {
            merchantInventory(code);
            if (code == KeyEvent.VK_ESCAPE) {
                gp.ui.subState = 0;
                gp.ui.commandNumber = 0;
            }
        }
        if (gp.ui.subState == 2) {
            playerInventory(code);
            if (code == KeyEvent.VK_ESCAPE) {
                gp.ui.subState = 0;
                gp.ui.commandNumber = 0;
            }
        }
    }

    public void mapState(int code) {
        if (code == KeyEvent.VK_M) {
            gp.gameState = gp.mapState;
        }
    }

    public void playerInventory(int code) {
        // move cursor in inventory frame
        if (code == KeyEvent.VK_UP) {
            if (gp.ui.playerSlotRow != 0) {
                gp.ui.playerSlotRow--;
                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_DOWN) {
            if (gp.ui.playerSlotRow != 3) {
                gp.ui.playerSlotRow++;
                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_LEFT) {
            if (gp.ui.playerSlotCol != 0) {
                gp.ui.playerSlotCol--;
                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_RIGHT) {
            if (gp.ui.playerSlotCol != 4) {
                gp.ui.playerSlotCol++;
                gp.playSE(9);
            }
        }

    }

    public void merchantInventory(int code) {
        // move cursor in inventory frame
        if (code == KeyEvent.VK_UP) {
            if (gp.ui.merchantSlotRow != 0) {
                gp.ui.merchantSlotRow--;
                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_DOWN) {
            if (gp.ui.merchantSlotRow != 3) {
                gp.ui.merchantSlotRow++;
                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_LEFT) {
            if (gp.ui.merchantSlotCol != 0) {
                gp.ui.merchantSlotCol--;
                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_RIGHT) {
            if (gp.ui.merchantSlotCol != 4) {
                gp.ui.merchantSlotCol++;
                gp.playSE(9);
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
            upPressed = false;
        } else if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
            downPressed = false;
        } else if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
            leftPressed = false;
        } else if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_F) {
            enterPressed = false;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = false;
        }
        if (code == KeyEvent.VK_SPACE) {
            spacePressed = false;
        }

    }
}
