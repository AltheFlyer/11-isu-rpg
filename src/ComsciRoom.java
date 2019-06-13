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

    public boolean runEvent(OverworldPlayer player, OverworldNPC[] npcs) {
        if (npcs[0].isTalking()) {
            questStart = true;
            npcs[1].setMessage("going");
            npcs[2].setMessage("going");
            npcs[3].setMessage("going");
            npcs[4].setMessage("going");
            npcs[5].setMessage("going");
            npcs[6].setMessage("going");
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

    public boolean questCleared() {
        for (int i = 0; i < 6; ++i) {
            if (!musicKidsTalkedTo[i]) {
                return false;
            }
        }
        return true;
    }

}
