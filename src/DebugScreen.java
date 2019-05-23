import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * [DebugScreen.java]
 * IMPORTANT: THIS SCREEN SHOULD ONLY BE USED TO TEST BASIC LOGIC FEATURES OF GAMESCREEN
 * @version 0.1a
 * @author Allen Liu
 * @since May 22, 2019
 */
public class DebugScreen extends GameScreen {

    public DebugScreen(GameManager game) {
        super(game);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (isMouseOver(new Rectangle(100, 100, 100, 100))) {
            g.setColor(Color.GREEN);
            g.fillRect(100, 100, 100, 100);
        }

        if (isMouseOver(new Rectangle(100, 100, 100, 100))) {
            g.setColor(Color.GREEN);
            g.fillRect(200, 200, 100, 100);
        }

        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);

        if (isFullyClicked(new Rectangle(100, 100, 100, 100))) {
            System.out.println("Click 1");
            setMusic("assets/music/test.wav");
            System.out.println("Click 2");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

        System.out.println(e.getKeyChar());

    }
}
