import java.awt.*;

public class RoomMap extends OverworldMap{

    private int tileSize = 100;

    public RoomMap(String mapPath, String walkabilityKey){
        super(mapPath,walkabilityKey);
    }

}