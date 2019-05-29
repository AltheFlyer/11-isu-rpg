import java.awt.*;

abstract public class OverworldEntity {

    private int x;
    private int y;
    private Rectangle boundingBox;
    private String direction;

    public OverworldEntity(int x, int y){
        this.x = x;
        this.y = y;
        this.boundingBox = new Rectangle(x,y,50,50); //modify size as we decide on graphics
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

    public Rectangle collisionWindow(){
        return this.boundingBox;
    }

    public void setBoundingBox(int x, int y) {
        this.boundingBox.x = x;
        this.boundingBox.y = y;
    }

    public int getSize(){
        return this.boundingBox.height;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String d) {
        this.direction = d;
    }
}
