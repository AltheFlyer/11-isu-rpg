import java.awt.*;

/**
 * [OverworldTile.java]
 * Class for individual tiles on overworld maps
 * @version 1.1
 * @author Jasmine Chu & Ethan Kwan
 * @since May 23, 2019
 */
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

    /**
     * [isNotWalkable]
     * gets whether the
     * @return int x the tile's x coordinate on the map
     */
    public boolean isNotWalkable() {
        if (walkable) {
            return false;
        }
        return true;
    }

    /**
     * [getX]
     * gets the tile's x coordinate on the map
     * @return int x the tile's x coordinate on the map
     */
    public int getX(){ return this.x; }

    /**
     * [getX]
     * gets the tile's x coordinate on the map
     * @return int x the tile's x coordinate on the map
     */
    public int getY(){
        return this.y;
    }

    /**
     * [getTileName]
     * gets the tile name of the tile
     * @return String tileName the name of the type of tile
     */
    public String getTileName() {return this.tileName; }

    /**
     * [getBoundingBox]
     * gets the tile's bounding box for interactions with the player
     * @return Rectangle the tile's bounding box
     */
    public Rectangle getBoundingBox() {return this.boundingBox; }

    /**
     * [checkCollisions]
     * changes player's x and y velocity if it intersects with the tile's bounding box and the tile is not walkable
     * @return void
     */
    public void checkCollisions(Rectangle playerBounds, OverworldPlayer player, GameManager game) {
        if (playerBounds.intersects(this.boundingBox) && (isNotWalkable())) {
            player.setXVelocity(0);
            player.setYVelocity(0);
        }
    }

}
