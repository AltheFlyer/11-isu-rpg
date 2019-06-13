public class MapMainTest {

    public static void main(String[] args) throws InterruptedException {
        GameManager game = new GameManager();

        //game.setScreen(new DebugScreen(game));
        
        game.setScreen(new MapScreen(game,"math_room.txt",
                "math_room_npcs.txt", "math_room_objects.txt", 400, 400));

//        game.setScreen(new MapScreen(game,"chemistry_room.txt",
//                "chemistry_room_npcs.txt", "chemistry_room_objects.txt", 400, 400));

//        game.setScreen(new MapScreen(game,"moving_hallway1.txt",
//                "moving_hallway1_npcs.txt", "moving_hallway1_objects.txt", 400, 400));

//        game.setScreen(new MapScreen(game,"english_room.txt",
//                "english_room_npcs.txt", "english_room_objects.txt", 400, 400));

//        game.setScreen(new MapScreen(game,"physics_room.txt",
//                "physics_room_npcs.txt", "physics_room_objects.txt", 400, 400));

    }
}
