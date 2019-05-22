import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MainMenuScreen extends GameScreen {

    private long timeCount;

    private Rectangle levelSelectButton;

    public MainMenuScreen(GameManager game) {
        super(game);

        timeCount = 0;

        System.out.println("Main Menu");
    }

    @Override
    public void postInitialize() {
        levelSelectButton = new Rectangle(this.getWidth() - 100, 0, 100, this.getHeight());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setDoubleBuffered(true);

        //levelSelectButton = new Rectangle(this.getWidth() - 100, 0, 100, this.getHeight());

        timeCount += Clock.getLastTime();

        g.setColor(Color.BLACK);
        g.drawString("Start Game " + timeCount, 300, 300);

        if (isMouseOver(levelSelectButton)) {
            g.setColor(Color.GREEN);
            System.out.println("A");
            g.fillRect(levelSelectButton.x, levelSelectButton.y, levelSelectButton.width, levelSelectButton.height);
        }

        this.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (isFullyClicked(levelSelectButton)) {
            getGame().setScreen(new LevelSelectScreen(getGame()));
        }
    }
}
