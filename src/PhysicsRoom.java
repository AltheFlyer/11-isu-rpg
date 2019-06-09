import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * [PhysicsRoom.java]
 * Class for physics room and its interactions
 * @version 1.1
 * @author Jasmine Chu
 * @since May 23, 2019
 */
public class PhysicsRoom extends RoomMap{

    private int tileSize = 100;

    public PhysicsRoom(GameIO fileManager, String mapPath, String walkabilityKey){
        super(fileManager, mapPath,walkabilityKey);
    }

}
