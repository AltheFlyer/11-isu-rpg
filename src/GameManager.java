import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;

import javax.swing.JFrame;

import java.io.File;
import java.io.IOException;

/**
 * [GameManager.java]
 * Manages the various screens of the game
 * @version 1.1
 * @author Allen Liu
 * @since June 3, 2019
 */
public class GameManager {

    private JFrame window;
    private GameScreen screen;

    private Clip music;

    private GameIO io;

    //I have a feeling it might be a good idea to store this:
    //TODO Add Map Position variables + methods

    public GameManager() {
        window = new JFrame();
        io = new GameIO();

        //Default window values
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Smallest window settings for fullscreen (Allen's PC)
        window.setSize(1366, 768);
        window.setVisible(true);

        setScreen(new LevelScreen(this));
        //Filler Panel
        //TODO change to whatever the start screen should be
    }

    /**
     * [setScreen]
     * Sets the active screen in the game
     * @param s The screen to set as the visible panel
     */
    public void setScreen(GameScreen s) {
        if (screen != null) {
            window.remove(screen);
        }

        screen = s;

        window.add(screen);

        //Allow for the panel to be added, then initialize any values that are dependent on the panel's attributes
        window.revalidate();
        screen.setVisible(true);

        //Prepare the window
        screen.requestFocusInWindow();
        screen.repaint();
    }

    /**
     * [setMusic]
     * Sets the music for the screen, and starts its loop
     * @param path the file path of the audio source
     */
    public void setMusic(String path) {
        if (music != null) {
            music.stop();
            music.close();
        }

        music = initializeClip(path);

        music.loop(Clip.LOOP_CONTINUOUSLY);

        music.start();
    }

    /**
     * [stopMusic]
     * stops the currently playing music
     */
    public void stopMusic() {
        if (music != null) {
            music.stop();
            music.close();
        }
    }

    /**
     * [initializeClip]
     * Loads an audio file and returns a Clip object generated from it
     * @param path the file path of the audio source
     * @return Clip, the Clip object that can then be used to play audio
     */
    public Clip initializeClip(String path) {
        AudioInputStream audioStream;

        Clip clip = null;

        try {
            audioStream = AudioSystem.getAudioInputStream(new File(path));
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }

        return clip;
    }

    /**
     * [getIO]
     * gets the file i/o manager for the running game
     * @return GameIO, the file manager for the game
     */
    public GameIO getIO() {
        return io;
    }
}
