import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

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

        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);

        if (isFullyClicked(new Rectangle(323, 468, 360, 100))) {
            selectedPlayer = playerMap.findPlayer(kevin);
        }
        if (isFullyClicked(new Rectangle(323, 588, 360, 100))) {
            selectedPlayer = null;
        }
        if (selectedPlayer != null && isFullyClicked(new Rectangle(10, 40, 60, 15))) {
            System.out.println("bam!");
            enemyMap.target(1,1,3,1);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        playerMap.draw(g);
        enemyMap.draw(g);
        //Profile of person 1
        if (isMouseOver(new Rectangle(323, 468, 360, 100))) {
            g.setColor(Color.GREEN);
            g.fillRect(323, 468, 360, 100);
            g.setColor(Color.BLACK);
            g.drawRect(323, 468, 360, 100);
        } else {
            //Profile of player should be here!
            g.setColor(Color.RED);
            g.fillRect(323, 468, 360, 100);
            g.setColor(Color.BLACK);
            g.drawRect(323, 468, 360, 100);
            //(Separator) look above
        }
        if (selectedPlayer != null) {
            selectedPlayer.drawAbilities(g);
            selectedPlayer = playerMap.findPlayer(selectedPlayer);
            //Ability selection
            if (isMouseOver(new Rectangle(10, 40, 60, 15))) {
                g.setColor(Color.GREEN);
                g.fillRect(10, 40, 60, 15);
                g.setColor(Color.BLACK);
                g.drawRect(10, 40, 60, 15);
            }
        }
        repaint();
    }

    public void attack(){
        playerMap.target(1,1,1,0);
        System.out.println("attacked!");
    }
}
