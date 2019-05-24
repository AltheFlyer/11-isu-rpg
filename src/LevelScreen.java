import java.awt.*;
import java.awt.event.KeyEvent;

public class LevelScreen extends GameScreen{
    Player selectedPlayer;
    PlayerMap playerMap;
    Player kevin;
    EnemyMap enemyMap;
    Enemy ack;

    LevelScreen(GameManager game){
        super(game);
        playerMap = new PlayerMap();
        enemyMap = new EnemyMap();
        kevin = new Player(10,"kevin");
        ack = new Enemy(10);
        playerMap.addPlayer(1,1,kevin);
        enemyMap.addEnemy(1,1,ack);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar() == 'a' ){    //Good time to use a Switch statement
            System.out.println("left");
            attack();
            System.out.println(kevin.getHealth());

        } else if(e.getKeyChar() == 's' ){
            System.out.println("down");
            playerMap.addPlayer(1,1,kevin);
            kevin.setHealth(10);
            enemyMap.addEnemy(1,1,ack);
            ack.setHealth(10);

        } else if(e.getKeyChar() == 'd' ){
            System.out.println("right");
            selectedPlayer = playerMap.findPlayer(kevin); /////////////////////////////////////////

        } else if(e.getKeyChar() == 'w' ){
            System.out.println("up");
            selectedPlayer = playerMap.findPlayer(kevin); /////////////////////////////////////////
            
        } else if(e.getKeyChar() == 'x' ){
            System.out.println("bam!");
            enemyMap.target(1,1,5,1);
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        playerMap.draw(g);
        enemyMap.draw(g);
        if (selectedPlayer != null) {
            selectedPlayer.drawAbilities(g);
            selectedPlayer = playerMap.findPlayer(selectedPlayer);
        }
        repaint();
    }

    public void attack(){
        playerMap.target(1,1,1,0);
        System.out.println("attacked!");
    }
}
