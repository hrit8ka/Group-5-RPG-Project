/**
 * The Sound class in Java is used to play various sound effects and music in a game or application.
 */
package Main;

import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

// Sound class is used to play sounds
public class Sound {
    Clip clip;// clip for sound
    URL soundURL[] = new URL[30]; // URL is used to store the location of the sound file
    FloatControl fc; // float control is used to control the volume of the sound
    int volumeScale = 3; // volume scale is used to scale the volume of the sound
    float volume; // volume is used to store the volume of the sound

    public Sound() {

        try {
            soundURL[0] = new URL("file:src/sound/zelda_title.wav");// sound URL for theme song
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            soundURL[1] = new URL("file:src/sound/collectcoin.wav");// sound URL for coin
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            soundURL[2] = new URL("file:src/sound/powerup.wav");// sound URL for power up
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            soundURL[3] = new URL("file:src/sound/unlock.wav");// sound URL for unlock
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            soundURL[4] = new URL("file:src/sound/fanfare.wav");// sound URL for soundtrack music
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            soundURL[5] = new URL("file:src/sound/hitmonster.wav");// sound URL for hit monster
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            soundURL[6] = new URL("file:src/sound/receivedamage.wav");// sound URL for receive damage
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            soundURL[7] = new URL("file:src/sound/swingingweapon.wav");// sound URL for swinging weapon
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            soundURL[8] = new URL("file:src/sound/teleport.wav");// sound URL for teleport sound
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            soundURL[9] = new URL("file:src/sound/select.wav");// sound URL for select sound effect
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            soundURL[10] = new URL("file:src/sound/fireball.wav");// sound URL for fireball sound
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            soundURL[11] = new URL("file:src/sound/cat.wav");// sound URL for cat sound
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            soundURL[12] = new URL("file:src/sound/cutTree.wav");// sound URL for cutting tree
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            soundURL[13] = new URL("file:src/sound/gameover.wav");// sound URL for game over
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            soundURL[14] = new URL("file:src/sound/runningingrass.wav");// sound URL for door
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            soundURL[15] = new URL("file:src/sound/sleep.wav");// sound URL for sleep
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            soundURL[16] = new URL("file:src/sound/win.wav");// sound URL for picking diamond
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            soundURL[17] = new URL("file:src/sound/guard.wav");// sound URL for guard
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    // setFile method is used to set the audio file
    public void setFile(int i) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL[i]);// get audio input stream
            clip = AudioSystem.getClip();// get clip
            clip.open(audioInputStream);// open clip
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);// get float control
            checkVolume();
        } catch (Exception e) {

        }
    }

    // play method is used to play the sound
    public void play() {
        clip.start();// start clip

    }

    // loop method is used to loop the sound
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);// loop clip

    }

    // stop method is used to stop the sound
    public void stop() {
        clip.stop();// stop clip
    }

    // checkVolume method is used to check the volume of the sound
    public void checkVolume() {
        switch (volumeScale) {
            case 0:
                volume = -80.0f;
                break;
            case 1:
                volume = -20.0f;
                break;
            case 2:
                volume = -12.0f;
                break;
            case 3:
                volume = -5.0f;
                break;
            case 4:
                volume = 1.0f;
                break;
            case 5:
                volume = 6.0f;
                break;
        }
        fc.setValue(volume);// set value of float control
    }

}
