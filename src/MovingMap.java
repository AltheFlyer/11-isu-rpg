import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * [MovingMap.java]
 * Class for moving maps that move around the player
 * @version 1.1
 * @author Jasmine Chu & Ethan Kwan
 * @since May 24, 2019
 */
public class MovingMap extends OverworldMap{

    private int tileSize = 100;
    private int visibleWidth = 1366;
    private int visibleHeight = 768;

    public MovingMap(GameIO fileManager, String mapPath){
        super(fileManager, mapPath);
    }

    /**
     * [draw]
     * draws the moving map by tile around the player
     * @param g the graphics object to draw with
     * @param player the player that is on the map
     */
    @Override
    public void draw(Graphics g, OverworldPlayer player){
        BufferedImage sprite;
        int leftmostVisible = player.getX() - (visibleWidth / 2);
        int leftmostTile = leftmostVisible / tileSize;
        int highestVisible = player.getY() - (visibleHeight / 2);
        int highestTile = highestVisible / tileSize;

        for (int i = 0; i < visibleWidth / tileSize + 2; i++){
            for (int j = 0; j < visibleHeight / tileSize + 2; j++){
                if ((0 > leftmostTile + i) || (leftmostTile + i >= getMap().length) ||
                        (0 > highestTile + j) || (highestTile + j >= getMap()[0].length)) {
                } else {
                    sprite = getSprite(leftmostTile + i, highestTile + j);
                    g.drawImage(sprite, -(leftmostVisible - leftmostTile * tileSize) + i * tileSize,
                            -(highestVisible - highestTile * tileSize) + j * tileSize,
                            tileSize, tileSize,null);
                }
            }
        }
    }

}