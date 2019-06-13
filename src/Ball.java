import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

/**
 * [Ball.java]
 * Class for a laser that is created from the laser emitter
 * @version 1.1
 * @author Ethan Kwan
 * @since June 12, 2019
 */
public class Ball {

    private int x;
    private int y;
    private double xVelocity, yVelocity;
    private int bounces = 0;
    private Rectangle boundingBox;

    public Ball(int x, int y, double xVelocity, double yVelocity) {
        this.x = x;
        this.y = y;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.boundingBox = new Rectangle(x, y, 20, 20);
    }

    /**
     * [checkCollisions]
     * checks wall collisions for this ball and bounces off walls accordingly
     * @param tile, the current map tile the ball is on
     */
    public void checkCollisions(OverworldTile tile) {
        if ((this.x >= 1300)||(this.x <= 200)) {
            this.xVelocity = this.xVelocity * -1;
            bounces++;
        }
        if ((this.y >= 900)||(this.y <= 100)) {
            this.yVelocity = this.yVelocity * -1;
            bounces++;
        }
    }

    /**
     * [move]
     * moves the ball according to a time check
     * @param elapsedTime elapsed time between last time check and current time in seconds
     * @return void
     */
    public void move(double elapsedTime) {
        this.x = (int) (this.x + xVelocity * elapsedTime * 100);
        this.y = (int) (this.y + yVelocity * elapsedTime * 100);
        this.boundingBox.x = this.x;
        this.boundingBox.y = this.y;
    }

    /**
     * [draw]
     * draws the ball to the screen
     * @param g the graphics object used to draw with
     * @param player the player currently inhabiting the map
     */
    public void draw(Graphics g, OverworldPlayer player) {
        g.setColor(Color.GREEN);
        int xDifference = player.getX() - x;
        int yDifference = player.getY() - y;
        int xLocation = 683 - xDifference;
        int yLocation = 384 - yDifference;
        g.fillOval(xLocation, yLocation, 10, 10);
    }

    /**
     * [getBounces]
     * returns the current number of bounces that the ball has performed
     * @return the current number of bounces that the ball has performed
     */
    public int getBounces() {
        return this.bounces;
    }

    /**
     * [getX]
     * returns the ball's current x position
     * @return the ball's current x position
     */
    public int getX() {
        return this.x;
    }

    /**
     * [getY]
     * returns the ball's current y position
     * @return the ball's current y position
     */
    public int getY() {
        return this.y;
    }

}
