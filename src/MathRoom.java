/**
 * [MathRoom.java]
 * Class for math room and its interactions
 * @version 1.1
 * @author Ethan Kwan
 * @since June 12, 2019
 */
public class MathRoom extends MovingMap {

    private int tileSize = 100;

    public MathRoom(GameIO fileManager, String mapPath, String walkabilityKey) {
        super(fileManager, mapPath,walkabilityKey);
    }

}
