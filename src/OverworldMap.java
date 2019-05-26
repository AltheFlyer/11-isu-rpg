import java.awt.*;

public class OverworldMap {

    private OverworldTile[][] map;

    public OverworldMap(){
        //File IO here
    }

    public void draw(Graphics g){
        int tileSize = 10; //change for visible areas and also arbitrary sizing
        for (int i = 0; i < map.length-1; i++){
            for (int j = 0; j < map[0].length-1; j++){
                if (map[i][j].isWalkable){
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillRect(i*tileSize,j*tileSize,tileSize,tileSize);
                } else {
                    g.setColor(Color.DARK_GRAY);
                    g.fillRect(i*tileSize,j*tileSize,tileSize,tileSize)
                }
            }
        }
    }

}
