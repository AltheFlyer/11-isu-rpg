import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class LevelScreen extends GameScreen{
    Player selectedPlayer;
    Ability selectedAbility;
    PlayerMap playerMap;
    Player kevin;
    Player allen;
    EnemyMap enemyMap;
    Enemy ack;

    SingleAbility ability1;

    LevelScreen(GameManager game){
        super(game);
        ability1 = new SingleAbility("basic",3,6,1,2);
        playerMap = new PlayerMap();
        enemyMap = new EnemyMap();
        kevin = new Player(10,"kevin",ability1);
        allen = new Player(10,"allen",ability1);
        ack = new Enemy(10);


        //Add things onto the map
        playerMap.addPlayer(1,1,kevin);
        playerMap.addPlayer(1,2,allen);
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

        if (isFullyClicked(new Rectangle(323, 468, 360, 80))) {
            selectedPlayer = playerMap.findPlayer(kevin);
        }

        if (isFullyClicked(new Rectangle(323, 548, 360, 80))) {
            selectedPlayer = playerMap.findPlayer(allen);
        }

        if (isFullyClicked(new Rectangle(323, 628, 360, 80))) {
            selectedPlayer = null;
        }
        //Use an ability here
        if (selectedPlayer != null && isFullyClicked(new Rectangle(10, 40, 60, 15))) {
            selectedAbility = playerMap.findPlayer(selectedPlayer).getAbility1();
        }

        //CLEAN UP!!!!!
        if (selectedAbility != null && isFullyClicked(new Rectangle(803, 228, 120, 120))) {
            ability1.act(enemyMap,1,1);
            System.out.println("bam!");
            selectedAbility = null;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        playerMap.draw(g);
        enemyMap.draw(g);
        drawPlayerProfiles(g);
        if (selectedAbility != null){
            if (isMouseOver(new Rectangle(803, 228, 120, 120))) {
                g.setColor(Color.GREEN);
                g.drawRect(803, 228, 120, 120);
            }
        }
        //Profile of person 1
        if (selectedPlayer != null) {
            selectedPlayer.drawAbilities(g, selectedAbility == ability1);
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

    public void drawPlayerProfiles(Graphics g){
        // profile of person 1
        if (isMouseOver(new Rectangle(323, 468, 360, 80))) {
            g.setColor(Color.GREEN);
            g.fillRect(323, 468, 360, 80);
            g.setColor(Color.BLACK);
            g.drawRect(323, 468, 360, 80);
        } else {
            //Profile of player should be here!
            g.setColor(Color.RED);
            g.fillRect(323, 468, 360, 80);
            g.setColor(Color.BLACK);
            g.drawRect(323, 468, 360, 80);
            //(Separator) look above
        }
        //Profile of player 2
        if (isMouseOver(new Rectangle(323, 548, 360, 80))) {
            g.setColor(Color.GREEN);
            g.fillRect(323, 548, 360, 80);
            g.setColor(Color.BLACK);
            g.drawRect(323, 548, 360, 80);
        } else {
            //Profile of player should be here!
            g.setColor(Color.RED);
            g.fillRect(323, 548, 360, 80);
            g.setColor(Color.BLACK);
            g.drawRect(323, 548, 360, 80);
            //(Separator) look above
        }
        //profile of player 3
        if (isMouseOver(new Rectangle(323, 628, 360, 80))) {
            g.setColor(Color.GREEN);
            g.fillRect(323, 628, 360, 80);
            g.setColor(Color.BLACK);
            g.drawRect(323, 628, 360, 80);
        } else {
            //Profile of player should be here!
            g.setColor(Color.RED);
            g.fillRect(323, 628, 360, 80);
            g.setColor(Color.BLACK);
            g.drawRect(323, 628, 360, 80);
            //(Separator) look above
        }
        //profile of player 3
        if (isMouseOver(new Rectangle(323, 708, 360, 30))) {
            g.setColor(Color.GREEN);
            g.fillRect(323, 708, 360, 80);
            g.setColor(Color.BLACK);
            g.drawRect(323, 708, 360, 80);
        } else {
            //Profile of player should be here!
            g.setColor(Color.RED);
            g.fillRect(323, 708, 360, 80);
            g.setColor(Color.BLACK);
            g.drawRect(323, 708, 360, 80);
            //(Separator) look above
        }
    }
}
