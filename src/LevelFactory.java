/**
 * [LevelFactory.java]
 * generates preset levels using strings for the encounter name
 * @version 1.0
 * @author Allen Liu
 * @since June 13, 2019
 */
public class LevelFactory {

    GameManager game;

    public LevelFactory(GameManager game) {
        this.game = game;
    }

    /**
     * [getLevel]
     * gets a level preset given the a name
     * @param name the name of the level to generate
     * @return LevelScreen, a level screen with preset enemies based on the name provided
     */
    public GameScreen getLevel(String name) {
        LevelScreen nextLevel;
        switch (name) {
            case "decaAndBunsen" :
                nextLevel = new LevelScreen(game, new Enemy[] {
                        new BunsenBurnerEnemy(5, 1),
                        new DecaEnemy(4, 1),
                        new FlaskEnemy(5,0),
                        new ProcessingCloudEnemy(3, 1)
                });
                break;
            case "ChemLevelA" :
                nextLevel = new LevelScreen(game, new Enemy[] {
                        new BunsenBurnerEnemy(5, 0),
                        new FlaskEnemy(3, 1),
                        new DecaEnemy(5,1),
                        new FlaskEnemy(4, 1),
                        new BunsenBurnerEnemy(5, 2)
                });
                break;
            case "PhysicsA" :
                nextLevel = new LevelScreen(game, new Enemy[] {
                        new CAPMEnemy(3, 1)
                });
                break;
            case "CSLevel" :
                nextLevel = new LevelScreen(game, new Enemy[] {
                        new ProcessingCloudEnemy(3, 1)
                });
                break;
            case "DramaLevel" :
                nextLevel = new LevelScreen(game, new Enemy[] {
                        new TwoFaceEnemy(4, 1)
                });
                break;
            case "MusicLevel" :
                nextLevel = new LevelScreen(game, new Enemy[] {
                        new MusicManEnemy(4, 0),
                        new MusicManEnemy(4, 2),
                });
                break;
            case "MangatLevel":
                nextLevel = new LevelScreen(game, new Enemy[] {
                        new MangatEnemy(3, 1)
                });
                break;
            default :
                //No select screen here, go right to battle!
                return new TutorialLevel(game);
        }

        return new BattleLayoutScreen(game, nextLevel);
    }

}
