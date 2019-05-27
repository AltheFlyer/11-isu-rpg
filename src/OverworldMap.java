import java.awt.*;

abstract public class OverworldMap {

    private OverworldTile[][] map;

    public OverworldMap(String path){
        GameIO.setTileWalkability("five_by_five_walkability.txt");
        map = GameIO.getMap(path);
    }

    public void draw(Graphics g){
        int tileSize = 100; //change for visible areas and also arbitrary sizing
        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[0].length; j++){
                if (map[i][j].isWalkable()){
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillRect(i*tileSize,j*tileSize,tileSize,tileSize);
                } else {
                    g.setColor(Color.DARK_GRAY);
                    g.fillRect(i*tileSize,j*tileSize,tileSize,tileSize);
                }
            }
        }
    }

    public OverworldTile[][] getMap(){
        return map;
    }

}
