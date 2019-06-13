/**
 * [ComsciRoom.java]
 * Class for computer science room and its interactions
 * @version 1.1
 * @author Ethan Kwan
 * @since June 12, 2019
 */
public class ComsciRoom extends MovingMap {

    private int tileSize = 100;
    private boolean[] musicKidsTalkedTo = {false,false,false,false,false,false};
    private boolean questStart = false;

    public ComsciRoom(GameIO fileManager, String mapPath) {
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
        if (npcs[0].isTalking()) {
            questStart = true;
            npcs[1].setMessage("What's that I found? Holy sheep it's - MR. MANGAT!!");
            npcs[2].setMessage("We have to go? Awwwww man!");
            npcs[3].setMessage("Whaaat? I'm just advertising for our department! Hey, you! Come to Coffee House!");
            npcs[4].setMessage("But - I - no - Mr. Mangat, I'm innocent!");
            npcs[5].setMessage("Never mind my music mark, my computer science mark is way worse!");
            npcs[6].setMessage("Oh boy, it sure is getting hot in here...");
        }
        if (questStart) {
            for (int i = 1; i < 7; ++i) {
                if (npcs[i].isTalking()) {
                    musicKidsTalkedTo[i-1] = true;
                }
            }
        }
        if (questCleared()) {
            npcs[0].setMessage("Thanks for doing that. They were getting real annoying. Kinda sad I have to do this" +
                    " after what you just did for me, but, well, a game's a game, right?         ");
            if (npcs[0].isTalking()) {
                return true;
            }
        }
        return false;
    }

    /**
     * [questCleared]
     * checks if a room-specific quest has been cleared
     * in this case, if all the music kids have been spoken to after hearing Mr. Mangat's request
     * @return boolean true when this task has been completed
     */
    private boolean questCleared() {
        for (int i = 0; i < 6; ++i) {
            if (!musicKidsTalkedTo[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * [getLevelName]
     * returns the name of the level that events in this map will lead to
     * @return String, the name of the level that events in this map will lead to
     */
    @Override
    public String getLevelName() {
        return "MangatLevel";
    }
}
