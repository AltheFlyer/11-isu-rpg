import java.awt.*;

/**
 * [Ball]
 * Class for a laser that is created from the laser emitter
 * @version 1.1
 * @author Ethan Kwan
 * @since June 12, 2019
 */
public class Ball {

    private int x;
    private int y;
    private int xVelocity, yVelocity;
    private int bounces = 0;
    private Rectangle boundingBox;

    public Ball(int x, int y, int xVelocity, int yVelocity) {
        this.x = x;
        this.y = y;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.boundingBox = new Rectangle(x, y, 20, 20);
    }

    public void checkCollisions(OverworldTile tile) {
        if (tile.isNotWalkable()) {
            bounces++;
            if ((this.boundingBox.intersects(new Rectangle(tile.getX()*100,tile.getY()*100,1,100)))||
                    (this.boundingBox.intersects(new Rectangle(tile.getX()*100+100,tile.getY()*100,1,100)))) {
                this.xVelocity = this.xVelocity * -1;
            }
            if ((this.boundingBox.intersects(new Rectangle(tile.getX()*100,tile.getY()*100,100,1)))||
                    (this.boundingBox.intersects(new Rectangle(tile.getX()*100,tile.getY()*100+100,100,1)))){
                this.yVelocity = this.yVelocity * -1;
            }
        }
    }

    public void move() {
        this.x = this.x + xVelocity;
        this.y = this.y + yVelocity;
        this.boundingBox.x = this.x;
        this.boundingBox.y = this.y;
    }

    public void draw(Graphics g, OverworldPlayer player) {
        g.setColor(Color.GREEN);
        int xDifference = player.getX() - x;
        int yDifference = player.getY() - y;
        int xLocation = 683 - xDifference;
        int yLocation = 384 - yDifference;
        g.fillOval(xLocation, yLocation, 10, 10);
    }

    public void ricochet() {
        this.xVelocity = this.xVelocity * -1;
        this.yVelocity = this.yVelocity * -1;
    }

    public int getBounces() {
        return this.bounces;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
