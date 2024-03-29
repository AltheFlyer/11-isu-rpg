import java.awt.Graphics;
import java.awt.Color;

/**
 * [Receiver.java]
 * Class for the receiver object
 * @version 1.1
 * @author Ethan Kwan
 * @since June 12, 2019
 */
public class Receiver extends OverworldObject {

    public Receiver(int x, int y) {
        super(x, y);
        this.setXVelocity(0);
        this.setYVelocity(0);
    }

    /**
     * [draw]
     * draws this object to the map
     * @param g the graphics object to draw with
     * @param map the OverworldMap the object is inhabiting
     * @param player the player inhabiting the same map
     */
    @Override
    public void draw(Graphics g, OverworldMap map, OverworldPlayer player) {
        g.setColor(Color.BLUE);
        int xDifference = player.getX() - this.getX();
        int yDifference = player.getY() - this.getY();
        int xLocation = 683 - xDifference;
        int yLocation = 384 - yDifference;
        g.fillRect(xLocation, yLocation, 50, 50);
    }

}
