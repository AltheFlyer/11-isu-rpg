import java.awt.Rectangle;

/**
 * [Orbiter.java]
 * Class for orbiter object that travels in a circular path on the map and interacts with player upon collisions
 * @version 1.1
 * @author Jasmine Chu
 * @since June 07, 2019
 */
public class Orbiter extends OverworldObject {

    private int orbitRadius;
    private double angle = 0;
    private int velocity;
    private int orbitCenterX;
    private int orbitCenterY;
    private Rectangle boundingBox;
    private int radius = 50;

    public Orbiter(int x, int y, int respawnX, int respawnY, int orbitCenterX, int orbitCenterY) {
        super(x, y, respawnX, respawnY);
        this.orbitRadius = 200;
        this.velocity = 5;
        this.orbitCenterX = orbitCenterX;
        this.orbitCenterY = orbitCenterY;
        this.boundingBox = new Rectangle(x, y,radius*2, radius*2);
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

}
