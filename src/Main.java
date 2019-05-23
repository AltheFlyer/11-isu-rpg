public class Main {

    public static void main(String[] args) throws InterruptedException {
        GameManager game = new GameManager();

        game.setScreen(new DebugScreen(game));
    }
}
