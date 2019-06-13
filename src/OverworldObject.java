import java.awt.*;

/**
 * [MapScreen.java]
 * Class for all overworld objects
 * @version 1.1
 * @author Jasmine Chu
 * @since June 07, 2019
 */
abstract public class OverworldObject {

    private int x;
    private int y;
    private double xVelocity;
    private double yVelocity;
    private Rectangle boundingBox;
    private int size = 50;

    public OverworldObject(int x, int y) {
        this.x = x;
        this.y = y;
        this.xVelocity = 5;
        this.yVelocity = 5;
        this.boundingBox = new Rectangle(x,y,size,size);
    }

    public OverworldObject(int x, int y) {
        this.x = x;
        this.y = y;
        this.boundingBox = new Rectangle(x,y,size,size);
        this.xVelocity = 0;
        this.yVelocity = 0;
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
     * [setX]
     * sets the object's x coordinate as the new x coordinate passed in
     * @param newX the object's new x coordinate on the map
     * @return void
     */
    public void setX(int newX) {
        this.x = newX;
    }

    /**
     * [setY]
     * sets the object's y coordinate as the new y coordinate passed in
     * @param newY the object's new y coordinate on the map
     * @return void
     */
    public void setY(int newY) {
        this.y = newY;
    }

    /**
     * [getX]
     * returns the object's x coordinate on the map
     * @return int x the object's x coordinate on the map
     */
    public int getX() { return this.x; }

    /**
     * [getY]
     * returns the object's y coordinate on the map
     * @return int x the object's x coordinate on the map
     */
    public int getY() {
        return this.y;
    }

    /**
     * [setXVelocity]
     * sets the object's x velocity
     * @param xVelocity the object's new x velocity
     * @return void
     */
    public void setXVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    /**
     * [getXVelocity]
     * returns the object's x velocity
     * @return double xVelocity the object's x velocity
     */
    public double getXVelocity() {
        return this.xVelocity;
    }

    /**
     * [setYVelocity]
     * sets the object's y velocity
     * @param yVelocity the object's new y velocity
     * @return void
     */
    public void setYVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }

    /**
     * [getYVelocity]
     * returns the object's y velocity
     * @return double yVelocity the object's y velocity
     */
    public double getYVelocity() {
        return this.yVelocity;
    }

    /**
     * [calcNewX]
     * calculates the object's new x coordinate according to the elapsed time
     * @param elapsedTime elapsed time between last time check and current time in seconds
     * @return int new value of the object's x coordinate according to the elapsed time
     */
    public int calcNewX(double elapsedTime) {
        return (int) (this.x + this.getXVelocity() * elapsedTime * 100);
    }

    /**
     * [calcNewY]
     * calculates the object's new y coordinate according to the elapsed time
     * @param elapsedTime elapsed time between last time check and current time in seconds
     * @return int new value of the object's y coordinate according to the elapsed time
     */
    public int calcNewY(double elapsedTime) {
        return (int) (this.y + this.getYVelocity() * elapsedTime * 100);
    }

    /**
     * [move]
     * moves the object's coordinates and sets a new bounding box accordingly
     * @param elapsedTime elapsed time between last time check and current time in seconds
     * @return void
     */
    public void move(double elapsedTime) {
        this.x = calcNewX(elapsedTime);
        this.y = calcNewY(elapsedTime);
        this.setBoundingBox(this.getX(),this.getY());
    }

    /**
     * [setBoundingBox]
     * changes the x and y position of the object's bounding box
     * @param x the new x value of the object's bounding box
     * @param y the new y value of the object's bounding box
     * @return void
     */
    public void setBoundingBox(int x, int y) {
        this.boundingBox.x = x;
        this.boundingBox.y = y;
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
     * [checkCollisions]
     * changes player's x and y coordinates if intersects with an object
     * @return void
     */
    public void checkCollisions(Rectangle playerBounds, OverworldPlayer player) {
        if (playerBounds.intersects(this.collisionWindow())) {
            player.setXVelocity(0);
            player.setYVelocity(0);
        }
    }

    /**
     * [checkInteractions]
     * checks if player's interaction hitbox is intersecting with this object
     * empty for most Overworld objects, but some types have an interaction
     * @param hitbox, the player's ineraction hitbox
     * @return void
     */
    public void checkInteractions(Rectangle hitbox) {

    }

}
