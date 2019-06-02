import java.awt.*;

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

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int newX) {
        this.x = newX;
    }

    public void setY(int newY) {
        this.y = newY;
    }

    public double getXVelocity() {
        return this.xVelocity;
    }

    public double getYVelocity() {
        return this.yVelocity;
    }

    public void setXVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    public void setYVelocity(double yVelocity) { this.yVelocity = yVelocity; }

    public void move(double elapsedTime) {
        this.x = (int) (this.x + this.getXVelocity() * elapsedTime * 100);
        this.y = (int) (this.y + this.getYVelocity() * elapsedTime * 100);
        this.setBoundingBox(this.getX(),this.getY());
    }

    public Rectangle collisionWindow(){
        return this.boundingBox;
    }

    public void setBoundingBox(int x, int y) {
        this.boundingBox.x = x;
        this.boundingBox.y = y;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String d) {
        this.direction = d;
    }

    public int getSize() {
        return this.boundingBox.height;
    }
}
