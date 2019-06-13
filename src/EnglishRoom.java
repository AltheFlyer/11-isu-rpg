/**
 * [EnglishRoom.java]
 * Class for English room and its interactions
 * @version 1.1
 * @author Ethan Kwan
 * @since May 23, 2019
 */
public class EnglishRoom extends RoomMap {

     private int tileSize = 100;
     private boolean[] opinionsGathered = {false,false,false,false,false,false};
     private boolean questStart = false;

     public EnglishRoom(GameIO fileManager, String mapPath) {
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
            npcs[1].setMessage("Well, you see, Mr. Cimetta, I don't really have an opinion, because I don't care!");
            npcs[2].setMessage("What kind of enemy do you think you'll face next? I hope you're prepared! ... ..." +
                    "My opinion? It's 3 AM, do you expect me to be able to think?");
            npcs[3].setMessage("I find this quote very interesting!");
            npcs[4].setMessage("I don't think he can hear what we're talking about.");
            npcs[5].setMessage("I'd rather be in the music room right now...");
            npcs[6].setMessage("I believe that this book is just the author's way of trying to live out his" +
                    " fantasies! ...Wait, what do you mean I'm overthinking it?");
        }
        if (questStart) {
            for (int i = 1; i < 7; ++i) {
                if (npcs[i].isTalking()) {
                    opinionsGathered[i-1] = true;
                }
            }
        }
        if (questCleared()) {
            npcs[0].setMessage("So now you know what everyone else thinks. Now, what about your opinion? ... ..." +
                    "I'm only joking. Here, take what you really came for.         ");
            if (npcs[0].isTalking()) {
                return true;
            }
        }
         return false;
     }

    /**
     * [questCleared]
     * checks if a room-specific quest has been cleared
     * in this case, if you have gathered the 'opinions' of everyone in the class
     * @return boolean true when this task has been completed
     */
    private boolean questCleared() {
        for (int i = 0; i < 6; ++i) {
            if (!opinionsGathered[i]) {
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
        return "DramaLevel";
    }
}
