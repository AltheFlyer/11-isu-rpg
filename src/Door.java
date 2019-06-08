import java.awt.*;

public class Door extends OverworldTile {

    private String doorPath;

    public Door(int x, int y, boolean walkable, int tileSize, String tileName, String doorPath) {
        super(x, y, walkable, tileSize, tileName);
        this.doorPath = doorPath;
    }

    public String getDoorPath() { return this.doorPath; }

    public void checkCollisions(Rectangle playerBounds, OverworldPlayer player, GameManager game) {
        if (playerBounds.intersects(this.getBoundingBox())) {
            game.setScreen(new MapScreen(game, this.getDoorPath() + ".txt",
                    "walkability.txt", this.getDoorPath() + "_npcs.txt"));
        }
    }

}
