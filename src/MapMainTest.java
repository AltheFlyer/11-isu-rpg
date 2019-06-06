public class MapMainTest {

    public static void main(String[] args) throws InterruptedException {
        GameManager game = new GameManager();

        //game.setScreen(new DebugScreen(game));
        game.setScreen(new MapScreen(game,"moving_hallway.txt","walkability.txt"));
    }
}
