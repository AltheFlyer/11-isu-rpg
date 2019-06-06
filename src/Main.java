public class Main {

    public static void main(String[] args) throws InterruptedException {
        GameManager game = new GameManager();

        //game.setScreen(new DebugScreen(game));
        //game.setScreen(new LevelScreen(game));

        //game.setScreen(new BattleLayoutScreen(game));
        //game.setScreen(new TutorialLevel(game));


        game.setScreen(new LevelScreen(game, new Enemy[] {
                new TutorialEnemy(3, 0),
                new TutorialEnemy(3, 1),
                new TutorialEnemy(3, 2)
        }));

    }
}
