/**
 * [ComsciRoom.java]
 * Class for computer science room and its interactions
 * @version 1.1
 * @author Ethan Kwan
 * @since June 12, 2019
 */
public class ComsciRoom extends MovingMap {

    private int tileSize = 100;
    private int musicKids = 5;

    public ComsciRoom(GameIO fileManager, String mapPath, String walkabilityKey) {
        super(fileManager, mapPath,walkabilityKey);
    }

    public void checkEncounters(OverworldPlayer player, OverworldNPC[] npcs) {
        //if (player.collisionWindow().intersects(new Rectangle(400,100,200,200))) {
        if (npcs[0].isTalking()) {
            System.out.println("works");
        }
    }

}
