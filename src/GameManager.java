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
 * @version 1.2
 * @author Allen Liu
 * @since June 13, 2019
 */
public class GameManager {

    private JFrame window;
    private GameScreen screen;

    private Clip music;

    private GameIO io;
    LevelFactory levelGenerator;

    public GameManager() {
        window = new JFrame();
        io = new GameIO();
        levelGenerator = new LevelFactory(this);

        //Default window values
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Smallest window settings for fullscreen (Allen's PC)
        window.setSize(1366, 768);
        window.setResizable(false);
        window.setVisible(true);

        setScreen(new LoadingScreen(this));

        //Set tutorial screen if beginning of game
        if ((io.getCurrentDay() == 1) && (io.getCurrentPeriod() == 0)) {
            setScreen(new TutorialLevel(this));
        } else {
            enterMapScreen();
        }
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
     * [setLevel]
     * sets the battle for the currently running game manager, defined by a string that can generate a level screen
     * @param level the string name for the level screen to generate
     */
    public void setLevel(String level) {
        setScreen(levelGenerator.getLevel(level));
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
     * [enterMapScreen]
     * enters the map screen based on the saved map position
     */
    public void enterMapScreen() {
        setScreen(new MapScreen(this, io.getActiveMap(),
                io.getMapNPCPath(), io.getMapObjectPath(), io.getMapX(), io.getMapY()));
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
