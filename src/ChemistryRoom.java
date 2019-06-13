/**
 * [PhysicsRoom.java]
 * Class for chemistry room and its interactions
 * @version 1.1
 * @author Jasmine Chu
 * @since May 09, 2019
 */
public class ChemistryRoom extends RoomMap {

    public ChemistryRoom(GameIO fileManager, String mapPath){
        super(fileManager, mapPath);
    }

    /**
     * [runEvent]
     * a sequence of events that happens in the room
     * returns true when all the events have been completed and the battle is ready to load
     * @param npcs the array of NPCs inhabiting the room
     * @return boolean true when all the events have been completed and the battle is ready to load
     */
    @Override
    public boolean runEvent(OverworldNPC[] npcs) {
        if (npcs[1].isTalking()) {
            return true;
        }
        return false;
    }

    /**
     * [getLevelName]
     * returns the name of the level that events in this map will lead to
     * @return String, the name of the level that events in this map will lead to
     */
    @Override
    public String getLevelName() {
        return "ChemLevelA";
    }

}
