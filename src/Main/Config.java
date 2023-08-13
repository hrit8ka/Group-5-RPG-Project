package Main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
    GamePanel gp;

    public Config(GamePanel gp) {
        this.gp = gp;
    }

    public void saveConfig() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));
            // Full Screen
            if (gp.fullScreenOn == true) {
                bw.write("On");
            }
            if (gp.fullScreenOn == false) {
                bw.write("Off");
            }
            bw.newLine(); // line break

            // Music
            bw.write(String.valueOf(gp.music.volumeScale));
            bw.newLine(); // line break

            // Sound effects
            bw.write(String.valueOf(gp.soundEffect.volumeScale));
            bw.newLine(); // line break

            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadConfig() {
        try{
            BufferedReader br = new BufferedReader(new FileReader("config.txt"));
            String s = br.readLine();

            // Full Screen
            if (s.equals("On")) {
                gp.fullScreenOn = true;
            }
            if (s.equals("Off")) {
                gp.fullScreenOn = false;
            }

            // Music
            s = br.readLine();
            gp.music.volumeScale = Integer.parseInt(s);

            // Sound effects
            s = br.readLine();
            gp.soundEffect.volumeScale = Integer.parseInt(s);

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
