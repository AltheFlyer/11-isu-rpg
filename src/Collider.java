import java.awt.*;

/**
 * [Collider.java]
 * Class for orbiter object that travels in a circular path on the map and interacts with player upon collisions
 * @version 1.1
 * @author Jasmine Chu
 * @since June 07, 2019
 */
abstract public class Collider extends OverworldObject {

    private int orbitRadius;
    private double angle = 0;
    private int velocity;
    private int respawnX;
    private int respawnY;
    private Rectangle boundingBox;
    private int radius = 50;

    public Collider(int x, int y, int respawnX, int respawnY) {
        super(x, y);
        this.orbitRadius = 200;
        this.velocity = 5;
        this.respawnX = respawnX;
        this.respawnY = respawnY;
        this.boundingBox = new Rectangle(x, y,radius*2, radius*2);
    }

    /**
     * [draw]
     * draws objects on the map
     * @param g the graphics object to draw with
     * @param map the OverworldMap the object is inhabiting
     * @param player the player inhabiting the same map
     * @return void
     */
    public void draw(Graphics g, OverworldMap map, OverworldPlayer player) {
        g.setColor(Color.BLUE);
        if (map instanceof RoomMap) { //regular drawing
            g.fillOval(this.getX(), this.getY(), 50, 50); //modify size
        } else { //draw object in relation to player location in map and moving map
            int xDifference = player.getX() - this.getX();
            int yDifference = player.getY() - this.getY();
            int xLocation = 683 - xDifference;
            int yLocation = 384 - yDifference;
            g.fillOval(xLocation, yLocation, 50, 50);
        }
    }

    /**
     * [getRadius]
     * returns the current radius of the object
     * @return int radius the current radius of the object
     */
    public int getRadius() {
        return this.radius;
    }

    /**
     * [getRespawnX]
     * returns the player's x coordinate of the respawn point in this map
     * @return int respawnX the player's y coordinate of the respawn point in this map
     */
    public int getRespawnX() { return this.respawnX; }

    /**
     * [getRespawnY]
     * returns the player's y coordinate of the respawn point in this map
     * @return int respawnY the player's y coordinate of the respawn point in this map
     */
    public int getRespawnY() {
        return this.respawnY;
    }

    /**
     * [checkCollisions]
     * changes player's x and y coordinates if intersects with an object
     * @return Rectangle boundingBox, the entity's bounding box
     */
    @Override
    public void checkCollisions(Rectangle playerBounds, OverworldPlayer player) {
        if (playerBounds.intersects(this.collisionWindow())) {
            player.setX(getRespawnX());
            player.setY(getRespawnY());
        }
    }

}
