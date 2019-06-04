import java.awt.*;

/**
 * [FrameRate.java]
 * Abstract class for any overworld map
 * @version 1.1
 * @author Jasmine Chu & Ethan Kwan
 * @since May 22, 2019
 */
abstract public class OverworldMap {

    private OverworldTile[][] map;
    private int tileSize = 100;

    public OverworldMap(GameIO fileManager, String mapPath, String walkabilityKey){
        fileManager.readTileWalkability(walkabilityKey);
        map = fileManager.getMap(mapPath, tileSize);
    }

    public void draw(Graphics g, OverworldPlayer player){ }

    public OverworldTile[][] getMap(){
        return map;
    }

    public int getTileSize(){
        return tileSize;
    }

}
