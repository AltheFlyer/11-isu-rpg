import java.awt.*;

public class OverworldTile {
    private int x;
    private int y;
    boolean isWalkable;
    private Rectangle boundingBox;

    OverworldTile(int x, int y, boolean isWalkable){
        this.x = x;
        this.y = y;
        this.isWalkable = isWalkable;
        this.boundingBox = new Rectangle(x,y,10,10); //again graphics particulars
    }
}
