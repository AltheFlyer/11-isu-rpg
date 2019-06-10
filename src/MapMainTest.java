public class MapMainTest {

    public static void main(String[] args) throws InterruptedException {
        GameManager game = new GameManager();

        //game.setScreen(new DebugScreen(game));

        game.setScreen(new MapScreen(game,"english_room.txt","walkability.txt",
                "english_room_npcs.txt", "english_room_objects.txt", 400, 400));

        //game.setScreen(new MapScreen(game,"physics_room.txt","walkability.txt",
        //        "physics_room_npcs.txt", "physics_room_objects.txt", 400, 400));

    }
}
