import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * [OverworldMap.java]
 * Abstract class for any overworld map
 * @version 1.1
 * @author Jasmine Chu & Ethan Kwan
 * @since May 22, 2019
 */
abstract public class OverworldMap {

    private OverworldTile[][] map;
    private int tileSize = 100;
    private BufferedImage[][] sprites;

    public OverworldMap(GameIO fileManager, String mapPath, String walkabilityKey){
        fileManager.readTileWalkability(walkabilityKey);
        map = fileManager.getMap(mapPath, tileSize);
        sprites = new BufferedImage[map[0].length][map.length];
        for (int i = 0; i < map.length; ++i) {
            for (int j = 0; j < map[0].length; ++j) {
                try {
                    sprites[j][i] = ImageIO.read(new File("assets/map sprites/" +
                            this.getMap()[i][j].getTileName() + ".png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void draw(Graphics g, OverworldPlayer player){ }

    public OverworldTile[][] getMap(){
        return map;
    }

    public int getTileSize(){
        return tileSize;
    }

    public BufferedImage getSprite(int i, int j) { return sprites[j][i]; }

}
