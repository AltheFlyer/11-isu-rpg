import java.awt.*;

public class MovingMap extends OverworldMap{

    private int tileSize = 100;
    private int visibleWidth = this.getMap()[0].length * tileSize;
    private int visibleHeight = this.getMap().length * tileSize;
    private int mapWidth = 1366 / tileSize;
    private int mapHeight = 768 / tileSize;
//    private int visibleWidth = 1366;
//    private int visibleHeight = 768;

    public MovingMap(String mapPath, String walkabilityKey){
        super(mapPath,walkabilityKey);
    }

    public void draw(Graphics g, OverworldPlayer player){
        int leftmostVisible = player.getX() - ((mapWidth / 2) * tileSize);
        int leftmostTile = leftmostVisible / tileSize;
        int highestVisible = player.getY() - ((mapHeight / 2) * tileSize);
        int highestTile = highestVisible / tileSize;

        for (int i = 0; i < mapHeight + 1; i++){
            for (int j = 0; j < mapWidth + 1; j++){
                if ((0 > leftmostTile + j) || (leftmostTile + j >= getMap()[0].length) || (0 > highestTile + i) || (highestTile + i >= getMap().length)) {
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