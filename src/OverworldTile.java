import java.awt.*;

public class OverworldTile {

    private String name;
    private int x;
    private int y;
    private boolean walkable;
    private Rectangle boundingBox;

    public OverworldTile(int x, int y, boolean walkable){
        this.x = x;
        this.y = y;
        this.walkable = walkable;
        this.boundingBox = new Rectangle(x,y,100,100); //again graphics particulars
    }

    public Rectangle collisionWindow() {
        return this.boundingBox;
    }

    public boolean isWalkable() {
        return this.walkable;
    }

    public boolean isNotWalkable() {
        if (walkable) {
            return false;
        }
        return true;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getBBY(){
        return boundingBox.y;
    }

}
