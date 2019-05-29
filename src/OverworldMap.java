import java.awt.*;

abstract public class OverworldMap {

    private OverworldTile[][] map;
    private int tileSize = 100;

    public OverworldMap(String path) {
        GameIO.readTileWalkability("five_by_five_walkability.txt");
        map = GameIO.getMap(path);
    }

    public OverworldMap(String mapPath, String walkabilityKey){
        GameIO.readTileWalkability(walkabilityKey);
        map = GameIO.getMap(mapPath);
    }

    public void draw(Graphics g, OverworldPlayer player){
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

    public int getTileSize(){
        return tileSize;
    }

}
