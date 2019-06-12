import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

/**
 * [Sweller.java]
 * Class for circular object that swells in size at equal intervals and interacts upon colliding
 * @version 1.1
 * @author Jasmine Chu
 * @since June 09, 2019
 */
public class Sweller extends OverworldObject {

    private int maxRadius;
    private int radius;
    private int velocity;
    private Rectangle boundingBox;
    private int respawnX;
    private int respawnY;

    public Sweller(int x, int y, int maxRadius, int respawnX, int respawnY) {
        super(x, y);
        this.maxRadius = maxRadius;
        this.velocity = 2;
        this.respawnX = respawnX;
        this.respawnY = respawnY;
        this.boundingBox = new Rectangle(x,y,0,0);
    }

    /**
     * [setVelocity]
     * sets the object's velocity as the new velocity
     * @return void
     */
    public void setVelocity(int newVelocity) { this.velocity = newVelocity; }

    /**
     * [getRespawnX]
     * returns the player's x coordinate of the respawn point in this map
     * @return int respawnX the player's y coordinate of the respawn point in this map
     */
    public int getRespawnX() {
        return this.respawnX;
    }

    /**
     * [getRespawnY]
     * returns the player's y coordinate of the respawn point in this map
     * @return int respawnY the player's y coordinate of the respawn point in this map
     */
    public int getRespawnY() {
        return this.respawnY;
    }

    /**
     * [getRadius]
     * returns the object's current radius
     * @return int radius the object's current radius
     */
    public int getRadius() {
        return this.radius;
    }

    /**
     * [updateRadius]
     * updates the radius and velocity according to the elapsed time
     * @param elapsedTime elapsed time between last time check and current time in seconds
     * @return void
     */
    public void updateRadius(double elapsedTime) {
        if ((this.radius > this.maxRadius) || (this.radius < 0)) {
            setVelocity(-this.velocity);
        }
        this.radius += (this.velocity * elapsedTime * 100);
        System.out.println(radius);
    }

    /**
     * [calcNewX]
     * calculates the object's new x coordinate according to the elapsed time and radius
     * @return int new value of the object's x coordinate according to the elapsed time
     */
    public int calcNewX() {
        return (this.getX() - this.radius);
    }

    /**
     * [calcNewY]
     * calculates the object's new y coordinate according to the elapsed time and radius
     * @return int new value of the object's y coordinate according to the elapsed time
     */
    public int calcNewY() {
        return (this.getY() - this.radius);
    }

    /**
     * [move]
     * moves the object's coordinates and sets a new bounding box accordingly
     * @param elapsedTime elapsed time between last time check and current time in seconds
     * @return void
     */
    public void move(double elapsedTime) {
        updateRadius(elapsedTime);
        this.setX(calcNewX());
        this.setY(calcNewY());
        this.setBoundingBox();
    }

    /**
     * [setBoundingBox]
     * changes the x and y position of the object's bounding box
     * @return void
     */
    public void setBoundingBox() {
        this.boundingBox.x = this.getX();
        this.boundingBox.y = this.getY();
        this.boundingBox.width = this.getRadius() * 2;
        this.boundingBox.height = this.getRadius() * 2;
    }

    /**
     * [checkCollisions]
     * changes player's x and y coordinates if intersects with an object
     * @return Rectangle boundingBox, the entity's bounding box
     */
    public void checkCollisions(Rectangle playerBounds, OverworldPlayer player) {
        if (playerBounds.intersects(this.collisionWindow())) {
            player.setX(getRespawnX());
            player.setY(getRespawnY());
        }
    }

}
