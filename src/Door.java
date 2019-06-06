public class Door extends OverworldTile {

    private String doorPath;

    public Door(int x, int y, boolean walkable, int tileSize, String tileName, String doorPath) {
        super(x, y, walkable, tileSize, tileName);
        this.doorPath = doorPath;
    }

    public String getDoorPath() { return this.doorPath; }

}
