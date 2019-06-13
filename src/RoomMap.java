import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * [RoomMap.java]
 * Class for still room maps
 * @version 1.1
 * @author Jasmine Chu & Ethan Kwan
 * @since May 23, 2019
 */
public class RoomMap extends OverworldMap{

    private int tileSize = 100;

    public RoomMap(GameIO fileManager, String mapPath){
        super(fileManager, mapPath);
    }

    /**
     * [draw]
     * draws the room map by tile
     * @param g the graphics object to draw with
     * @param player the current player
     * @return void
     */
    @Override
    public void draw(Graphics g, OverworldPlayer player){
        BufferedImage sprite;
        for (int i = 0; i < this.getMap().length; i++){
            for (int j = 0; j < this.getMap()[0].length; j++){
                sprite = getSprite(i, j);
                g.drawImage(sprite, i*tileSize, j*tileSize, tileSize, tileSize,null);
            }
        }
    }

}
