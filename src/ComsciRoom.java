public class ComsciRoom extends MovingMap {

    private int tileSize = 100;

    public ComsciRoom(GameIO fileManager, String mapPath, String walkabilityKey) {
        super(fileManager, mapPath,walkabilityKey);
    }
}
