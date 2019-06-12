import java.awt.Rectangle;

/**
 * [Door.java]
 * Class for tile type door that the player can collide with to move between maps
 * @version 1.1
 * @author Jasmine Chu
 * @since May 30, 2019
 */
public class Door extends OverworldTile {

    private String doorPath;
    private int newX;
    private int newY;

    public Door(int x, int y, boolean walkable, int tileSize, String tileName, String doorPath, int newX, int newY) {
        super(x, y, walkable, tileSize, tileName);
        this.doorPath = doorPath;
        this.newX = newX;
        this.newY = newY;
    }

    public String getDoorPath() { return this.doorPath; }

    public void checkCollisions(Rectangle playerBounds, OverworldPlayer player, GameManager game) {
        if (playerBounds.intersects(this.getBoundingBox())) {
            game.setScreen(new LoadingScreen(game));
            game.setScreen(new MapScreen(game, this.getDoorPath() + ".txt",
                    this.getDoorPath() + "_npcs.txt", this.getDoorPath() + "_objects.txt",
                    newX, newY));
        }
    }

}
