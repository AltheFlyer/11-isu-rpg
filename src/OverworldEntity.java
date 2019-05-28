import java.awt.*;

abstract public class OverworldEntity {

    private int x;
    private int y;
    private Rectangle boundingBox;

    public OverworldEntity(int x, int y){
        this.x = x;
        this.y = y;
        this.boundingBox = new Rectangle(x,y,50,50); //modify size as we decide on graphics
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

    public Rectangle interactionPane(){
        return this.boundingBox;
    }

}
