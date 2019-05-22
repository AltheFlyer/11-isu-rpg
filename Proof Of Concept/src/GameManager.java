import javax.swing.*;
import java.awt.*;

public class GameManager {

    private GameScreen screen;
    private JFrame window;
    private Dimension screenSize;

    private double frame;

    public GameManager() {
        //aspectRatio = 16.0 / 9.0;
        frame = 0;

        //Initialize the jframe
        window = new JFrame("Game");
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        window.setSize(screenSize.width, screenSize.height - 50);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //window.setUndecorated(true);

        screen = new MainMenuScreen(this);
        window.add(screen, BorderLayout.CENTER);

        window.setVisible(true);


        screen.postInitialize();




    }

    /**
     * [setScreen]
     * sets the GameScreen for the game
     * @param s the GameScreen to set the game window's focus on
     */
    public void setScreen(GameScreen s) {
        window.remove(screen);
        screen = s;

        window.add(screen, BorderLayout.CENTER);

        //Allow for the panel to be added, then initialize any values that are dependent on the panel\s attributes
        window.revalidate();
        screen.postInitialize();
    }

    /**
     * [refresh]
     * refreshes the game window
     */
    public void refresh() {
        frame = Clock.getDeltaTime();
        //window.repaint();
        screen.repaint();
    }

    /**
     * [getFrame]
     * gets the time since the last frame in seconds
     * @return double frame, the time since the last frame in seconds
     */
    public double getFrame() {
        return frame;
    }

}

/*

 */