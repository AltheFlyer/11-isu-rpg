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
public class Sweller extends Collider {

    private int maxRadius, minRadius;
    private int radius;
    private int velocity;
    private Rectangle boundingBox;
    private int centerX, centerY;

    public Sweller(int x, int y, int respawnX, int respawnY, int maxRadius) {
        super(x, y, respawnX, respawnY);
        this.velocity = 5;
        this.centerX = x;
        this.centerY = y;
        this.maxRadius = maxRadius;
        this.boundingBox = new Rectangle(x,y,radius * 2,radius * 2);
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
            g.fillOval(this.getX(), this.getY(), this.radius, this.radius); //modify size
//            g.setColor(Color.BLACK);
//            g.drawRect(this.boundingBox.x, this.boundingBox.y, this.boundingBox.width, this.boundingBox.height);
        } else { //draw object in relation to player location in map and moving map
            int xDifference = player.getX() - this.getX();
            int yDifference = player.getY() - this.getY();
            int xLocation = 683 - xDifference;
            int yLocation = 384 - yDifference;
            g.fillOval(xLocation, yLocation, this.radius, this.radius);
        }
    }

    /**
     * [setVelocity]
     * sets the object's velocity as the new velocity
     * @return void
     */
    public void setVelocity(int newVelocity) { this.velocity = newVelocity; }

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
    }

    /**
     * [calcNewX]
     * calculates the object's new x coordinate according to the current radius and center x coordiante
     * @return int new value of the object's x coordinate
     */
    public int calcNewX() {
        return (this.centerX - this.radius / 2);
    }

    /**
     * [calcNewY]
     * calculates the object's new y coordinate according to the current radius and center y coordiante
     * @return int new value of the object's y coordinate
     */
    public int calcNewY() {
        return (this.centerY - this.radius / 2);
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
        this.boundingBox.width = this.getRadius();
        this.boundingBox.height = this.getRadius();
    }

    /**
     * [collisionWindow]
     * returns the object's bounding box
     * @return Rectangle boundingBox, the object's bounding box
     */
    @Override
    public Rectangle collisionWindow(){
        return this.boundingBox;
    }

}
