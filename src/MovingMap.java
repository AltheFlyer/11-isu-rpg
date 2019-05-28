import java.awt.*;

public class MovingMap extends OverworldMap{

    private int tileSize = 50;
    private int mapWidth = this.getMap()[0].length * tileSize;
    private int mapHeight = this.getMap().length * tileSize;
    private int visibleWidth = 5;
    private int visibleHeight = 5;
//    private int visibleWidth = 1366;
//    private int visibleHeight = 768;

    public MovingMap(String mapPath, String walkabilityKey){
        super(mapPath,walkabilityKey);
    }

    public void draw(Graphics g, OverworldPlayer player){
        int leftmostVisible = player.getX() - visibleWidth * tileSize;
        int leftmostTile = leftmostVisible / tileSize;
        int highestVisible = player.getY() - visibleHeight * tileSize;
        int highestTile = highestVisible / tileSize;

        for (int i = 0; i < visibleHeight + 1; i++){
            for (int j = 0; j < visibleWidth + 1; j++){
                if (this.getMap()[highestTile + i][leftmostTile + j].isWalkable()){
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillRect(leftmostVisible + j * tileSize,highestVisible + i * tileSize,tileSize,tileSize);
                } else {
                    g.setColor(Color.DARK_GRAY);
                    g.fillRect(leftmostVisible + j * tileSize,highestVisible + i * tileSize,tileSize,tileSize);
                }
            }
        }
    }

}

//    With the player's coordinates, you find the upper and leftmost coordinates that should be seen
//        From those coordinates, you need to turn them into which number tile they are on the bigger map, so you know which one to look for for walkability when you're actually drawing it out
//        And then we draw them into the negatives or whatever based on their tile with their tile value