import java.awt.*;

public class MovingMap extends OverworldMap{

    private int tileSize = 200;
    private int mapWidth = this.getMap()[0].length * tileSize;
    private int mapHeight = this.getMap().length * tileSize;
    private int visibleWidth = 8;
    private int visibleHeight = 6;
//    private int visibleWidth = 1366;
//    private int visibleHeight = 768;

    public MovingMap(String mapPath, String walkabilityKey){
        super(mapPath,walkabilityKey);
    }

    public void draw(Graphics g, OverworldPlayer player){
        int leftmostVisible = player.getX() - ((visibleWidth / 2) * tileSize);
        int leftmostTile = leftmostVisible / tileSize;
        int highestVisible = player.getY() - ((visibleHeight / 2) * tileSize);
        int highestTile = highestVisible / tileSize;

        for (int i = 0; i < visibleHeight + 1; i++){
            for (int j = 0; j < visibleWidth + 1; j++){
                if ((0 > leftmostTile + j) || (leftmostTile + j >= getMap()[0].length) || (0 > highestTile + i) || (highestTile + i >= getMap().length)) {
//                if (((leftmostVisible + j * tileSize < 0) || (leftmostVisible + j * tileSize > mapWidth - tileSize))
//                || ((highestVisible + i * tileSize < 0) || (highestVisible + i * tileSize > mapHeight - tileSize))) {
                    g.setColor(Color.BLACK);
                } else if (this.getMap()[highestTile + i][leftmostTile + j].isWalkable()){
                    g.setColor(Color.LIGHT_GRAY);
                } else {
                    g.setColor(Color.DARK_GRAY);
                }
                g.fillRect(-(leftmostVisible - leftmostTile * tileSize) + j * tileSize,
                        -(highestVisible - highestTile * tileSize) + i * tileSize, tileSize, tileSize);
            }
        }
    }

}