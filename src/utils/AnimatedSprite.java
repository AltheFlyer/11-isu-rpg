package utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * [AnimatedSprite.java]
 * Controls animated sprites based on a time delay
 * @version 1.0
 * @author Allen Liu
 * @since May 24, 2019
 */
public class AnimatedSprite {

    private static final String SRC = "assets/";

    private BufferedImage[] sprites;
    private int frame;
    private long frameDelay;
    private long lastFrame;

    /**
     * [AnimatedSprite]
     * @param path the relative file path, not including universal source folder for all animated sprites
     * @param rows the number of rows in the file
     * @param cols the number of columns in the file
     * @param width the width of each sprite
     * @param height the height of each sprite
     * @param frameDelay the time in milliseconds between each animation frame
     */
    public AnimatedSprite(String path, int rows, int cols, int width, int height, long frameDelay) {
        BufferedImage sheet = null;

        try {
            sheet = ImageIO.read(new File(SRC + path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        sprites = new BufferedImage[rows * cols];

        for (int y = 0; y < rows; ++y) {
            for (int x = 0; x < cols; ++x) {
                sprites[y * cols + x] = sheet.getSubimage(x * width, y * height, width, height);
            }
        }

        frame = 0;
        this.frameDelay = frameDelay;
        lastFrame = System.currentTimeMillis();
    }

    /**
     * [draw]
     * draws a appropriate frame of the animated sprite
     * @param g the graphics object to draw with
     * @param x the x position to draw the frame from
     * @param y the y position to draw the frame from
     */
    public void draw(Graphics g, int x, int y) {
        tick();
        g.drawImage(sprites[frame], x, y, null);
    }

    /**
     * [getSprite]
     * Returns the current frame from of the animated sprite, for use with any sprite manipulation.
     * If drawing is the only required operation, use draw() instead.
     * @return BufferedImage, the current frame of the animated sprite
     */
    public BufferedImage getSprite() {
        tick();
        return sprites[frame];
    }

    /**
     * [tick]
     * increments the animated sprite's internal timer, changing frame when appropriate
     */
    public void tick() {
        //TODO Check how screwy this code is
        if (System.currentTimeMillis() - lastFrame > frameDelay) {
            lastFrame += frameDelay;
            frame++;
            if (frame >= sprites.length) {
                frame = 0;
            }
        }
    }
}
