public class MapMainTest {

    public static void main(String[] args) throws InterruptedException {
        GameManager game = new GameManager();

        //game.setScreen(new DebugScreen(game));
        game.setScreen(new MapScreen(game,"test_hallway.txt","five_by_five_walkability.txt"));
    }
}
