import utils.SpriteLoader;
import utils.TextDrawer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * [OverworldMap.java]
 * Abstract class for any overworld map
 * @version 1.1
 * @author Ethan Kwan
 * @since May 22, 2019
 */
abstract public class OverworldMap {

    private OverworldTile[][] map;
    private int tileSize = 100;
    private BufferedImage[][] sprites;
    private String mapName;

    public OverworldMap(GameIO fileManager, String mapPath){
        fileManager.readTileWalkability("walkability.txt");
        mapName = mapPath;
        map = fileManager.getMap(mapPath, tileSize);
        sprites = new BufferedImage[map.length][map[0].length];
        for (int i = 0; i < map.length; ++i) {
            for (int j = 0; j < map[0].length; ++j) {
                sprites[i][j] = SpriteLoader.getSprite(map[i][j].getTileName());
            }
        }
    }

    /**
     * [draw]
     * draws the map by tile, overridden in subclasses
     * @param g the graphics object to draw with
     * @param player the current player
     */
    public void draw(Graphics g, OverworldPlayer player){ }

    /**
     * [getMap]
     * returns the current map that is inhabited by the player
     * @return OverworldTile[][] map, the current map that is inhabited by the player
     */
    public OverworldTile[][] getMap(){
        return map;
    }

    /**
     * [getTileSize]
     * returns the tile size of the tiles in this map
     * @return int tileSize, the tile size of the tiles in this map
     */
    public int getTileSize(){
        return tileSize;
    }

    /**
     * [getSprite]
     * returns a specific sprite at [x][y] in the array of map sprites
     * @param x the x position of the sprite in the array
     * @param y the y position of the sprite in the array
     * @return BufferedImage sprites[x][y], the sprite at the position [x][y] in the array
     */
    public BufferedImage getSprite(int x, int y) { return sprites[x][y]; }

    /**
     * [runEvent]
     * runs the event leading up to battle that is happening in the current room, some rooms have no events
     * @param player the player inhabiting the room
     * @param npcs the array of NPCs inhabiting the room
     * @return boolean true when battle can start
     */
    public boolean runEvent(OverworldPlayer player, OverworldNPC[] npcs) {
        return false;
    }

    /**
     * [getMapName]
     * returns this map's debug name
     * @return String, this map's debug name
     */
    public String getMapName() {
        return mapName;
    }

    /**
     * [getLevelName]
     * returns the name of the level that events in this map will lead to
     * most maps do not have a level and will not call this method
     * @return String, the name of the level that events in this map will lead to
     */
    public String getLevelName() {
        return "";
    }

}
