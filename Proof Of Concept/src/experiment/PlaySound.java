package experiment;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class PlaySound {

    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File("src/experiment/test.wav"));

        Clip clip = AudioSystem.getClip();


        clip.open(audioStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.loop(1);
        /*
        clip.stop();
        clip.close();
        */
        clip.start();
        while (1 == 1) {

        }

    }

}
