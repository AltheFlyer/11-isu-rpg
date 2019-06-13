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

    private String[] tips = {
            "Talk to everyone you meet! You never know what you could learn!",
            "Movement is powerful! You can avoid a lot of damage by following enemy intents.",
            "If at first you don't succeed, try try again - there's no penalty for experimentation!",
            "You tend to be most powerful in the first turn of battle. Optimize your starting layout to make full use of it!"
    };

    private int tipOfTheDay = (int)(Math.random()*tips.length);

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
