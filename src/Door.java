import java.awt.Rectangle;
import java.util.HashMap;

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
    private String itemNeeded;

    public Door(int x, int y, boolean walkable, int tileSize, String tileName, String doorPath, int newX, int newY, String itemNeeded) {
        super(x, y, walkable, tileSize, tileName);
        this.doorPath = doorPath;
        this.newX = newX;
        this.newY = newY;
        this.itemNeeded = itemNeeded;
    }


    /**
     * [getDoorPath]
     * gets the door's path, or what room it links to
     * @return String, the string for the room the door links to
     */
    public String getDoorPath() { return this.doorPath; }

    /**
     * [checkCollisions]
     * checks collisions with a player
     * @param playerBounds the bounding box for the player
     * @param player the player on the overworld
     * @param game the running game manager
     * @param inventory the inventory of the player
     */
    public void checkCollisions(Rectangle playerBounds, OverworldPlayer player, GameManager game, HashMap<String, Integer> inventory) {
        super.checkCollisions(playerBounds,player,game,inventory);
        if ((playerBounds.intersects(this.getBoundingBox())) && ((inventory.containsKey(this.itemNeeded)) || this.itemNeeded == null)) {
            game.setScreen(new LoadingScreen(game));
            game.setScreen(new MapScreen(game, this.getDoorPath() + ".txt",
                    this.getDoorPath() + "_npcs.txt", this.getDoorPath() + "_objects.txt",
                    newX, newY));
        }
    }

}
