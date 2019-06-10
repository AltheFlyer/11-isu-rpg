public class MapMainTest {

    public static void main(String[] args) throws InterruptedException {
        GameManager game = new GameManager();

        //game.setScreen(new DebugScreen(game));
        game.setScreen(new MapScreen(game,"english_room.txt","walkability.txt",
                "english_room_npcs.txt", 400, 400));
    }
}
