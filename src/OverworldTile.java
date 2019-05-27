import java.awt.*;

public class OverworldTile {
    private int x;
    private int y;
    private boolean walkable;
    private Rectangle boundingBox;

    OverworldTile(int x, int y, boolean walkable){
        this.x = x;
        this.y = y;
        this.walkable = walkable;
        this.boundingBox = new Rectangle(x,y,10,10); //again graphics particulars
    }

    public boolean isWalkable() {
        return walkable;
    }
}