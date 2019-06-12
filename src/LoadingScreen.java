import java.awt.Graphics;
import java.awt.Color;

/**
 * [LoadingScreen.java]
 * Class for loading screen that will appear between loading map or battle screens
 * @version 1.1
 * @author Ethan Kwan
 * @since June 03, 2019
 */
public class LoadingScreen extends GameScreen {

    String[] tips = {
            "Talk to everyone you meet! You never know what you could learn!",
            "battle tip 1",
            "battle tip 2",
            "battle tip 3"
    };

    int tipOfTheDay = (int)(Math.random()*tips.length);

    public LoadingScreen(GameManager game) {
        super(game);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);
        g.setColor(Color.WHITE);
        g.drawString("Now loading...",500,500);
        g.drawString(tips[tipOfTheDay],600,600);
        repaint();
    }

}
