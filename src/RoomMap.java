import java.awt.*;

public class RoomMap extends OverworldMap{

    private int tileSize = 100;

    public RoomMap(GameIO fileManager, String mapPath, String walkabilityKey){
        super(fileManager, mapPath,walkabilityKey);
    }

    @Override
/**
 * [draw]
 * draws the room map by tile
 * @param g
 * @param player
 * @return void
 */
    public void draw(Graphics g, OverworldPlayer player){
        for (int i = 0; i < this.getMap().length; i++){
            for (int j = 0; j < this.getMap()[0].length; j++){
                if (this.getMap()[i][j].isWalkable()){
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillRect(i*tileSize,j*tileSize,tileSize,tileSize);
                } else {
                    g.setColor(Color.DARK_GRAY);
                    g.fillRect(i*tileSize,j*tileSize,tileSize,tileSize);
                }
            }
        }
    }

}
