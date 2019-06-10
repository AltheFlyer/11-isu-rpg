import java.awt.*;

/**
 * [Orbiter.java]
 * Class for orbiter object that travels in a circular path on the map
 * @version 1.1
 * @author Jasmine Chu & Ethan Kwan
 * @since May 22, 2019
 */
public class Orbiter extends OverworldObject {

    private int orbitRadius;
    private double angle = 0;
    private int velocity;
    private int orbitCenterX;
    private int orbitCenterY;
    private Rectangle boundingBox;
    private int size = 50;
    private int respawnX;
    private int respawnY;

    public Orbiter(int x, int y, int orbitCenterX, int orbitCenterY, int respawnX, int respawnY) {
        super(x, y);
        this.orbitRadius = 200;
        this.velocity = 3;
        this.orbitCenterX = orbitCenterX;
        this.orbitCenterY = orbitCenterY;
        this.respawnX = respawnX;
        this.respawnY = respawnY;
        this.boundingBox = new Rectangle(x,y,size,size);
    }

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
     * [updateAngle]
     * updates the angle according to the elapsed time
     * @param elapsedTime elapsed time between last time check and current time in seconds
     * @return void
     */
    public void updateAngle(double elapsedTime) {
        this.angle += (this.velocity * elapsedTime * 100);
        if (this.angle >= 360) {
            this.angle -= 360;
        }
    }

    /**
     * [calcNewX]
     * calculates the object's new x coordinate according to the elapsed time
     * @return int new value of the object's x coordinate according to the elapsed time
     */
    public int calcNewX() {
        return (int) (orbitCenterX + orbitRadius * Math.cos(Math.toRadians(angle)));
    }

    /**
     * [calcNewY]
     * calculates the object's new y coordinate according to the elapsed time
     * @return int new value of the object's y coordinate according to the elapsed time
     */
    public int calcNewY() {
        return (int) (orbitCenterY + orbitRadius * Math.sin(Math.toRadians(angle)));
    }

    /**
     * [move]
     * moves the object's coordinates and sets a new bounding box accordingly
     * @param elapsedTime elapsed time between last time check and current time in seconds
     * @return void
     */
    public void move(double elapsedTime) {
        updateAngle(elapsedTime);
        this.setX(calcNewX());
        this.setY(calcNewY());
        this.setBoundingBox(this.getX(),this.getY());
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
