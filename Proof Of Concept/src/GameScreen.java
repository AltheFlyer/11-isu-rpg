import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * [GameScreen.java]
 * @version 0.1b
 * @author Allen Liu
 * @since May 12, 2019
 * General class for game screens
 */
abstract public class GameScreen extends JPanel implements MouseListener, MouseMotionListener, KeyListener {

    private GameManager game;

    //Mouse positions
    private int mouseX, mouseY;

    //Mouse click positions
    private int clickX, clickY;

    public GameScreen(GameManager game) {
        this.game = game;
        mouseX = 0;
        mouseY = 0;
        clickX = 0;
        clickY = 0;

        setFocusable(true);
        requestFocusInWindow();

        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);

        System.out.println("Screen made");
    }

    /**
     * [postInitialize]
     * used to run any methods that need the panel to be initialized first
     */
    public void postInitialize() {}

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        clickX = e.getX();
        clickY = e.getY();
    }

    /**
     * [getMouseX]
     * gets the x position of the mouse
     * @return int mouseX, the x position of the mouse
     */
    public int getMouseX() {
        return mouseX;
    }

    /**
     * [getMouseY]
     * gets the y position of the mouse
     * @return int mouseY, the y position of the mouse
     */
    public int getMouseY() {
        return mouseY;
    }

    /**
     * [getClickX]
     * gets the x position of the last mouse press
     * @return int clickX, the x position of the last mouse press
     */
    public int getClickX() {
        return clickX;
    }

    /**
     * [getClickY]
     * gets the y position of the last mouse press
     * @return int clickY, the y position of the last mouse press
     */
    public int getClickY() {
        return clickY;
    }

    /**
     * [getGame]
     * gets the currently running game
     * @return GameManager game, the game manager that this screen is running on
     */
    public GameManager getGame() {
        return game;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    /**
     * [isMouseOver]
     * checks if the mouse is within the specified rectangle
     * @param rect the rectangle to check the bounds of
     * @return boolean, whether the mouse is within the rectangle or not
     */
    public boolean isMouseOver(Rectangle rect) {
        return rect.contains(mouseX, mouseY);
    }

    /**
     * [isFullyClicked]
     * checks if a rectangular region has both a starting press and release within its bounds
     * @param rect the rectangular region to check clicks for
     * @return boolean, whether the region has been fully clicked
     */
    public boolean isFullyClicked(Rectangle rect) {
        return rect.contains(mouseX, mouseY) && rect.contains(clickX, clickY);
    }


    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}
