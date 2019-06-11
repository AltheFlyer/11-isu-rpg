import java.awt.*;

/**
 * [LoadingScreen.java]
 * Class for loading screen that will appear between loading map or battle screens
 * @version 1.1
 * @author Ethan Kwan
 * @since June 03, 2019
 */
public class LoadingScreen extends GameScreen {

    public LoadingScreen(GameManager game) {
        super(game);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);
        g.setColor(Color.WHITE);
        g.drawString("Now loading...",10,10);

        repaint();
    }

}
