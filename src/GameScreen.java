import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * [GameScreen.java]
 * Abstract class for game screens
 * @version 1.1
 * @author Allen Liu
 * @since May 22, 2019
 */
abstract public class GameScreen extends JPanel implements MouseListener, KeyListener, MouseMotionListener, MouseWheelListener {

    private GameManager game;

    private int mouseX, mouseY;
    private int clickX, clickY;

    public GameScreen(GameManager game) {
        this.game = game;

        setFocusable(true);
        requestFocus();

        addMouseListener(this);
        addKeyListener(this);
        addMouseMotionListener(this);
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

    /**
     * [setScreen]
     * Sets the currently active screen
     * @param s The game screen to set as the visible panel
     */
    public void setScreen(GameScreen s) {
        game.setScreen(s);
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

    @Override
    public void mousePressed(MouseEvent e) {
        clickX = e.getX();
        clickY = e.getY();

        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

    }


}
