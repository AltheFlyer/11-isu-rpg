import java.awt.*;

public class RoomMap extends OverworldMap{

    private int tileSize = 100;

    public RoomMap(String mapPath, String walkabilityKey){
        super(mapPath,walkabilityKey);
    }

    public void draw(Graphics g){
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
