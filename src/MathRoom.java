import java.awt.*;

/**
 * [MathRoom.java]
 * Class for math room and its interactions
 * @version 1.1
 * @author Ethan Kwan
 * @since June 12, 2019
 */
public class MathRoom extends MovingMap {

    private int tileSize = 100;
    private boolean questCleared = false;

    public MathRoom(GameIO fileManager, String mapPath) {
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
        if (npcs[3].isTalking()) {
            questCleared = true;
            npcs[0].setMessage("Well, well, well, I see you've discovered our little secret. That's quite unfortunate" +
                    " for you - and here I was thinking we'd found another recruit! Now instead of the taste of" +
                    " sweet, sweet victory, you'll get a taste of my piping-hot Bunsen burner!         ");
        }
        if (questCleared) {
            if (npcs[0].isTalking()) {
                return true;
            }
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
        return "decaAndBunsen";
    }

}
