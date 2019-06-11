public class Main {

    public static void main(String[] args) throws InterruptedException {
        GameManager game = new GameManager();

        //game.setScreen(new DebugScreen(game));
        //game.setScreen(new LevelScreen(game));

        //game.setScreen(new BattleLayoutScreen(game));
        //game.setScreen(new TutorialLevel(game));
        
        //My args setup, Use custom run configurations for different encounters
        if (args.length != 0) {
            switch (args[0]) {
                case "decaAndBunsen" :
                    game.setScreen(new LevelScreen(game, new Enemy[] {
                            new BunsenBurnerEnemy(5, 0),
                            new DecaEnemy(4, 1),
                            new FlaskEnemy(5,1),
                            new CAPMEnemy(3,2)
                    }));
                    break;
                case "ChemLevelA" :
                    game.setScreen(new LevelScreen(game, new Enemy[] {
                            new BunsenBurnerEnemy(5, 0),
                            new FlaskEnemy(3, 1),
                            new DecaEnemy(5,1),
                            new FlaskEnemy(4, 1),
                            new BunsenBurnerEnemy(5, 2)
                    }));
                    break;
                case "PhysicsA" :
                    game.setScreen(new LevelScreen(game, new Enemy[] {
                            new CAPMEnemy(3, 1)
                    }));
                    break;
                case "CSLevel" :
                    game.setScreen(new LevelScreen(game, new Enemy[] {
                            new ProcessingCloudEnemy(3, 1)
                    }));
                    break;
                case "Tutorial" :
                    game.setScreen(new TutorialLevel(game));
                    break;
            }
        }

/*
        game.setScreen(new LevelScreen(game, new Enemy[] {
                new BunsenBurnerEnemy(5, 0),
                new BunsenBurnerEnemy(5,2),
                new TutorialEnemy(4, 0),
                new TutorialEnemy(3, 2),
                new TutorialEnemy(4, 2),
                new TutorialEnemy(3, 0),
        }));
*/

        /*
        game.setScreen(new LevelScreen(game, new Enemy[] {
                new BunsenBurnerEnemy(3, 0),
                new BunsenBurnerEnemy(3, 1),
                new BunsenBurnerEnemy(3, 2)
        }));
        */

    }
}
