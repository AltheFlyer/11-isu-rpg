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

    public OverworldMap(GameIO fileManager, String mapPath, String walkabilityKey){
        fileManager.readTileWalkability(walkabilityKey);
        map = fileManager.getMap(mapPath, tileSize);
        sprites = new BufferedImage[map.length][map[0].length];
        for (int i = 0; i < map.length; ++i) {
            for (int j = 0; j < map[0].length; ++j) {
                try {
                    sprites[i][j] = ImageIO.read(new File("assets/map sprites/" +
                            map[i][j].getTileName() + ".png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

}
