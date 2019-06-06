import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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

    public MovingMap(GameIO fileManager, String mapPath, String walkabilityKey){
        super(fileManager, mapPath,walkabilityKey);
    }

    @Override
/**
 * [draw]
 * draws the moving map by tile around the player
 * @param g
 * @param player
 * @return void
 */
    public void draw(Graphics g, OverworldPlayer player){
        BufferedImage sprite;
        int leftmostVisible = player.getX() - (visibleWidth / 2);
        int leftmostTile = leftmostVisible / tileSize;
        int highestVisible = player.getY() - (visibleHeight / 2);
        int highestTile = highestVisible / tileSize;

//        for (int i = 0; i < visibleHeight / tileSize + 1; i++){
//            for (int j = 0; j < visibleWidth / tileSize + 1; j++){
//                if ((0 > leftmostTile + j) || (leftmostTile + j >= getMap().length) ||
//                        (0 > highestTile + i) || (highestTile + i >= getMap()[0].length)) {
//                    g.setColor(Color.BLACK);
//                } else if (this.getMap()[leftmostTile + j][highestTile + i].isWalkable()){
//                    g.setColor(Color.LIGHT_GRAY);
//                } else {
//                    g.setColor(Color.DARK_GRAY);
//                }
//                g.fillRect(-(leftmostVisible - leftmostTile * tileSize) + j * tileSize,
//                        -(highestVisible - highestTile * tileSize) + i * tileSize, tileSize, tileSize);
//            }

        for (int i = 0; i < visibleWidth / tileSize + 1; i++){
            for (int j = 0; j < visibleHeight / tileSize + 1; j++){
                if ((0 > leftmostTile + i) || (leftmostTile + i >= getMap().length) ||
                        (0 > highestTile + j) || (highestTile + j >= getMap()[0].length)) {
//                    g.setColor(Color.BLACK);
//                    g.fillRect(-(leftmostVisible - leftmostTile * tileSize) + j * tileSize,
//                            -(highestVisible - highestTile * tileSize) + i * tileSize, tileSize, tileSize);
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