import javax.swing.*;
import java.awt.*;

/**
 * [GameManager.java]
 * Manages the various screens of the game
 * @version 1.0
 * @author Allen Liu
 * @since May 22, 2019
 */
public class GameManager {

    JFrame window;
    GameScreen screen;

    public GameManager() {
        window = new JFrame();

        //Default window values
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Smallest window settings for fullscreen (Allen's PC)
        window.setSize(1366, 768);
        window.setVisible(true);
    }

    /**
     * [setScreen]
     * Sets the active screen in the game
     * @param s The screen to set as the visible panel
     */
    public void setScreen(GameScreen s) {
        window.remove(screen);
        screen = s;

        window.add(screen, BorderLayout.CENTER);

        //Allow for the panel to be added, then initialize any values that are dependent on the panel's attributes
        window.revalidate();
    }
}
