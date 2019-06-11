public class MapMainTest {

    public static void main(String[] args) throws InterruptedException {
        GameManager game = new GameManager();

        //game.setScreen(new DebugScreen(game));

        game.setScreen(new MapScreen(game,"moving_hallway1.txt","walkability.txt",
                "moving_hallway1_npcs.txt", "moving_hallway1_objects.txt", 400, 400));

        //game.setScreen(new MapScreen(game,"physics_room.txt","walkability.txt",
        //        "physics_room_npcs.txt", "physics_room_objects.txt", 400, 400));

    }
}
