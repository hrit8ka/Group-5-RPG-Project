package Main;

import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    Clip clip;
    URL soundURL[]= new URL[30];
    
    public Sound(){
        /*soundURL[0] = getClass().getResource("src/sound/themesong.wav");
        soundURL[1] = getClass().getResource("src/sound/coin.wav");
        soundURL[2] = getClass().getResource("src/sound/powerup.wav");
        soundURL[3] = getClass().getResource("src/sound/unlock.wav");
        soundURL[4] = getClass().getResource("src/sound/fanfare.wav"); */

        try {
            soundURL[0] = new URL("file:src/sound/themesong.wav");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            soundURL[1] = new URL("file:src/sound/coin.wav");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            soundURL[2] = new URL("file:src/sound/powerup.wav");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            soundURL[3] = new URL("file:src/sound/unlock.wav");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            soundURL[4] = new URL("file:src/sound/fanfare.wav");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }

    public void setFile(int i){
        try{
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL[i]);
            clip=AudioSystem.getClip();
            clip.open(audioInputStream);
        }catch(Exception e){
            
        }
    }
    public void play(){
        clip.start();

    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);

    }
    public void stop(){
        clip.stop();
    }
    
}
