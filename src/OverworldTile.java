import java.awt.*;

public class OverworldTile {

    private String name;
    private int x;
    private int y;
    private boolean walkable;
    private String tileName;
    private Rectangle boundingBox;

    public OverworldTile(int x, int y, boolean walkable, int tileSize, String tileName) {
        this.x = x;
        this.y = y;
        this.walkable = walkable;
        this.tileName = tileName;
        this.boundingBox = new Rectangle(x * tileSize, y * tileSize, tileSize, tileSize);
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

    public String getTileName() {return this.tileName; }

}
