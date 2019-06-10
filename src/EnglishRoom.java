import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * [EnglishRoom.java]
 * Class for English room and its interactions
 * @version 1.1
 * @author Ethan Kwan
 * @since May 23, 2019
 */
public class EnglishRoom extends RoomMap {

     private int tileSize = 100;

     public EnglishRoom(GameIO fileManager, String mapPath, String walkabilityKey) {
         super(fileManager, mapPath,walkabilityKey);
     }

}
