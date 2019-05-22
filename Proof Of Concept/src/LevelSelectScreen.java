import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LevelSelectScreen extends GameScreen {

    Rectangle mainMenuButton;
    Rectangle levelButton;

    BufferedImage debugImage;

    public LevelSelectScreen(GameManager game) {
        super(game);
        System.out.println("Level Select");

        try {
            debugImage = ImageIO.read(new File("src/Untitled.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void postInitialize() {
        mainMenuButton = new Rectangle(0, 0, 100, this.getHeight());
        levelButton = new Rectangle(this.getWidth() - 100, 0, 100, this.getHeight());
        System.out.println("INITIALIZED");
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setDoubleBuffered(true);

        g.setColor(Color.BLACK);
        g.drawString("HELLO", 200, 200);

        if (isMouseOver(mainMenuButton)) {
            g.setColor(Color.RED);
            g.fillRect(mainMenuButton.x, mainMenuButton.y, mainMenuButton.width, mainMenuButton.height);
        }
        if (isMouseOver(levelButton)) {
            g.setColor(Color.GREEN);
            g.fillRect(levelButton.x, levelButton.y, levelButton.width, levelButton.height);
        }

        g.drawImage(debugImage, getMouseX() - 40, getMouseY() - 40, 80, 80, this);
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (isFullyClicked(mainMenuButton)) {
            getGame().setScreen(new MainMenuScreen(getGame()));
        }

        if (isFullyClicked(levelButton)) {
            getGame().setScreen(new Level(getGame()));
        }
    }
}
