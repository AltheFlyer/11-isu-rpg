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
    private static HashMap<String, BufferedImage> entitySprites;

    private static String[] tileNames = {
            "bottom_left", "bottom_right", "computer_d", "computer_l", "computer_r", "computer_u",
            "desk", "door", "exit", "floor", "top_left", "top_right", "wall", "wall_down", "wall_left",
            "wall_right", "wall_up"
    };

    private static String[] entityNames = {
            "Student", "Mr. Mangat", "Jordan", "Sarah", "placeholder", "Mr. Cimetta", "Jasmine", "Shopkeeper Ken"
    };

    static {
        sprites = new HashMap<>();
        for (int i = 0; i < tileNames.length; ++i) {
            sprites.put(tileNames[i], getMapImage(tileNames[i]));
        }
    }

    static {
        entitySprites = new HashMap<>();
        for (int i = 0; i < entityNames.length; ++i) {
            entitySprites.put(entityNames[i], getEntityImage(entityNames[i]));
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
     * [getEntitySprite]
     * gets an entity sprite using its id
     * @param name the name of the sprite to get
     * @return BufferedImage, the sprite to get using the name
     */
    public static BufferedImage getEntitySprite(String name) {
        return entitySprites.get(name);
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


    /**
     * [getEntitySprite]
     * gets an image from the entity sprite path
     * @param fileName the file to get from dir assets/map sprites
     * @return BufferedImage, the image located at the file
     */
    private static BufferedImage getEntityImage(String fileName) {
        try {
            return ImageIO.read(new File("assets/overworld entity sprites/" + fileName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
