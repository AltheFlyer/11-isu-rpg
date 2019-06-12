package utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * [SpriteLoader.java]
 * preloads reused sprites for more efficient memory management
 * @version 1.0
 * @author Allen Liu
 * @since June 12, 2019
 */
public class SpriteLoader {

    //static static static
    private static HashMap<String, BufferedImage> sprites;

    private static String[] tileNames = {
            "bottom_left", "bottom_right", "computer_d", "computer_l", "computer_r", "computer_u",
            "desk", "door", "exit", "floor", "top_left", "top_right", "wall", "wall_down", "wall_left",
            "wall_right", "wall_up"
    };

    static {
        for (int i = 0; i < tileNames.length; ++i) {
            sprites.put(tileNames[i], getMapImage(tileNames[i]));
        }
    }

    /**
     * [getSprite]
     * gets a sprite using its debug id
     * @param name the name of the sprite to get
     * @return BufferedImage, the sprite to get using the debug name
     */
    public static BufferedImage getSprite(String name) {
        return sprites.get(name);
    }

    /**
     * [getMapImage]
     * gets an image from the map sprite path
     * @param fileName the file to get from dir assets/map sprites
     * @return BufferedImage, the image located at the file
     */
    private static BufferedImage getMapImage(String fileName) {
        try {
            return ImageIO.read(new File("assets/map sprites/" + fileName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
