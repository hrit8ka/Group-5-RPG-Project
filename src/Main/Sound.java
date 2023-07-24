package Main;

import java.net.URL;

import javax.sound.sampled.Clip;

public class Sound {
    Clip clip;
    URL soundURL[]= new URL[30];
    
    public Sound(){
        soundURL[0] = getClass().getResource("src/sound/themesong.wav");
        soundURL[1] = getClass().getResource("src/sound/coin.wav");
        soundURL[2] = getClass().getResource("src/sound/powerup.wav");
        soundURL[3] = getClass().getResource("src/sound/unlock.wav");
        soundURL[4] = getClass().getResource("src/sound/fanfare.wav");
    }

    public void setFile(){

    }
    public void play(){

    }
    public void loop(){

    }
    public void stop(){

    }
    
}
