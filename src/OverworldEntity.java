import java.awt.*;

/**
 * [OverworldEntity.java]
 * Abstract class for any entity on any overworld map
 * @version 1.1
 * @author Jasmine Chu & Ethan Kwan
 * @since May 22, 2019
 */
abstract public class OverworldEntity {

    private int x;
    private int y;
    private double xVelocity;
    private double yVelocity;
    private Rectangle boundingBox;
    private String direction;
    private int size = 50;

    public OverworldEntity(int x, int y){
        this.x = x;
        this.y = y;
        this.xVelocity = 0;
        this.yVelocity = 0;
        this.boundingBox = new Rectangle(x,y,size,size); //modify size as we decide on graphics
        direction = "down";
    }

    /**
     * [getX]
     * returns the entity's x coordinate on the map
     * @return int x the entity's x coordinate on the map
     */
    public int getX() {
        return this.x;
    }

    /**
     * [getY]
     * returns the entity's y coordinate on the map
     * @return int x the entity's x coordinate on the map
     */
    public int getY() {
        return this.y;
    }

    /**
     * [setX]
     * sets the entity's x coordinate as the new x coordinate passed in
     * @param newX the entity's new x coordinate on the map
     * @return void
     */
    public void setX(int newX) {
        this.x = newX;
    }

    /**
     * [setY]
     * sets the entity's y coordinate as the new y coordinate passed in
     * @param newY the entity's new y coordinate on the map
     * @return void
     */
    public void setY(int newY) {
        this.y = newY;
    }

    /**
     * [getXVelocity]
     * returns the entity's x velocity
     * @return double xVelocity the entity's x velocity
     */
    public double getXVelocity() {
        return this.xVelocity;
    }

    /**
     * [getYVelocity]
     * returns the entity's y velocity
     * @return double yVelocity the entity's y velocity
     */
    public double getYVelocity() {
        return this.yVelocity;
    }

    /**
     * [setXVelocity]
     * returns the entity's x velocity
     * @param xVelocity the entity's x velocity
     * @return void
     */
    public void setXVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    /**
     * [setYVelocity]
     * returns the entity's y velocity
     * @param yVelocity the entity's y velocity
     * @return void
     */
    public void setYVelocity(double yVelocity) { this.yVelocity = yVelocity; }

    /**
     * [calcNewX]
     * calculates the entity's new x coordinate according to the elapsed time
     * @param elapsedTime elapsed time between last time check and current time in seconds
     * @return int new value of the entity's x coordinate according to the elapsed time
     */
    public int calcNewX(double elapsedTime) {
        return (int) (this.x + this.getXVelocity() * elapsedTime * 100);
    }

    /**
     * [calcNewY]
     * calculates the entity's new y coordinate according to the elapsed time
     * @param elapsedTime elapsed time between last time check and current time in seconds
     * @return int new value of the entity's y coordinate according to the elapsed time
     */
    public int calcNewY(double elapsedTime) {
        return (int) (this.y + this.getYVelocity() * elapsedTime * 100);
    }

    /**
     * [move]
     * moves the entity's coordinates and sets a new bounding box accordingly
     * @param elapsedTime elapsed time between last time check and current time in seconds
     * @return void
     */
    public void move(double elapsedTime) {
        this.x = calcNewX(elapsedTime);
        this.y = calcNewY(elapsedTime);
        this.setBoundingBox(this.getX(),this.getY());
    }

    /**
     * [collisionWindow]
     * returns the entity's bounding box
     * @return Rectangle boundingBox, the entity's bounding box
     */
    public Rectangle collisionWindow(){
        return this.boundingBox;
    }

    /**
     * [setBoundingBox]
     * changes the x and y position of the entity's bounding box
     * @param x the new x value of the entity's bounding box
     * @param y the new y value of the entity's bounding box
     * @return void
     */
    public void setBoundingBox(int x, int y) {
        this.boundingBox.x = x;
        this.boundingBox.y = y;
    }

    /**
     * [getDirection]
     * returns the direction that the entity is currently facing
     * @return String direction, the direction that the entity is currently facing
     */
    public String getDirection() {
        return direction;
    }

    /**
     * [setDirection]
     * sets the direction that the entity is currently facing
     * @param d the direction the entity is currently facing
     * @return void
     */
    public void setDirection(String d) {
        this.direction = d;
    }

    /**
     * [getSize]
     * returns the vertical size of the player's bounding box
     * @return int boundingBox.height, the vertical size of the player's bounding box
     */
    public int getSize() {
        return this.boundingBox.height;
    }
}
