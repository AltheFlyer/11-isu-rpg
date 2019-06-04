import javax.sound.sampled.Clip;

import javax.swing.JPanel;

import java.awt.Rectangle;

import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.KeyEvent;

/**
 * [GameScreen.java]
 * Abstract class for all game screens
 * @version 1.1
 * @author Allen Liu
 * @since May 22, 2019
 */
abstract public class GameScreen extends JPanel
        implements MouseListener, KeyListener, MouseMotionListener, MouseWheelListener {

    private GameManager game;

    private int mouseX, mouseY;
    private int clickX, clickY;

    public GameScreen(GameManager game) {
        this.game = game;

        setFocusable(true);
        requestFocusInWindow();

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
     * Checks if a rectangular region has both a starting press and release within its bounds.
     * This should only be called during a mouseReleased method, or continuous triggering will occur.
     * @param rect the rectangular region to check clicks for
     * @return boolean, whether the region has been clicked in and contains the mouse when the method is called.
     */
    public boolean isFullyClicked(Rectangle rect) {
        return rect.contains(mouseX, mouseY) && rect.contains(clickX, clickY);
    }

    /**
     * [playSound]
     * Plays a sound from an audio file
     * @param path the file path of the audio source
     */
    public void playSound(String path) {
        Clip clip = game.initializeClip(path);
        clip.start();
    }

    /**
     * [setMusic]
     * Sets the music for the screen, and starts its loop.
     * @param path the file path of the audio source
     */
    public void setMusic(String path) {
        game.setMusic(path);
    }

    /**
     * [setScreen]
     * Sets the currently active screen for the currently active GameManager.
     * This will close the current active screen.
     * @param s The game screen to set as the visible panel
     */
    public void setScreen(GameScreen s) {
        game.setScreen(s);
    }

    /**
     * [getGame]
     * gets the currently running GameManager
     * @return GameManager, the currently running manager for the game
     */
    public GameManager getGame() {
        return game;
    }

    /**
     * [getIO]
     * gets the current game's file i/o manager
     * @return GameIO, the currently running io manager
     */
    public GameIO getIO() {
        return game.getIO();
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
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {}

}
