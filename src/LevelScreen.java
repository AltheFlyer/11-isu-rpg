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
    AOEAbility heal;

    LevelScreen(GameManager game){
        super(game);
        ability1 = new SingleAbility("basic",6,0,1,2);
        heal = new AOEAbility("heal",0,3,0,1,1,-2.0);
        playerMap = new PlayerMap();
        enemyMap = new EnemyMap();
        kevin = new Player(10,"kevin",ability1);
        allen = new Player(10,"allen",heal);
        ack = new Enemy(10);


        //Add things onto the map
        playerMap.addPlayer(1,1,kevin);
        playerMap.addPlayer(2,1,allen);
        enemyMap.addEnemy(1,1,ack);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar() == 's' ){
            System.out.println("down");
            playerMap.addPlayer(1,1,kevin);
            kevin.setHealth(10);
            enemyMap.addEnemy(1,1,ack);
            ack.setHealth(10);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);

        if (isFullyClicked(new Rectangle(323, 468, 360, 80))) {
            selectedPlayer = playerMap.findPlayer(kevin);
            selectedAbility = null;
            enemyMap.unIndicateAll();
            playerMap.unIndicateAll();
        }

        if (isFullyClicked(new Rectangle(323, 548, 360, 80))) {
            selectedPlayer = playerMap.findPlayer(allen);
            selectedAbility = null;
            enemyMap.unIndicateAll();
            playerMap.unIndicateAll();
        }

        if (isFullyClicked(new Rectangle(323, 628, 360, 80))) {
            selectedPlayer = null;
            selectedAbility = null;
            enemyMap.unIndicateAll();
            playerMap.unIndicateAll();
        }

        //Use an ability here
        if (selectedPlayer != null && isFullyClicked(new Rectangle(10, 40, 60, 15))) {
            selectedAbility = playerMap.findPlayer(selectedPlayer).getAbility1();
        }

        //Action attack!
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (selectedPlayer != null && selectedAbility != null && isFullyClicked(new Rectangle(686 + 121 * j, 108 + 121 * i, 120, 120))) {
                    actionEnemy(j,i);
                } else if (selectedPlayer != null && selectedAbility != null && isFullyClicked(new Rectangle(323 + 121 * j, 108 + 121 * i, 120, 120))) {
                    actionPlayer(j,i);
                }
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        playerMap.draw(g);
        enemyMap.draw(g);
        drawPlayerProfiles(g);


        if (selectedAbility != null && selectedPlayer != null){
            //Calculate Range of Ability!
            int rangeAhead = playerMap.findPlayerX(selectedPlayer) + selectedAbility.getXRange();
            int rangeBehind = playerMap.findPlayerX(selectedPlayer) - selectedAbility.getXRange();
            int rangeDown = playerMap.findPlayerY(selectedPlayer) + selectedAbility.getYRange();
            int rangeUp = playerMap.findPlayerY(selectedPlayer) - selectedAbility.getYRange();

            //Create Indications for ability
            for (int i = rangeUp; i <= rangeDown; i++) {
                for (int j = rangeBehind; j <= rangeAhead; j++) {
                    if (enemyMap.tileExists(i,j-3)){
                        enemyMap.indicate(j-3,i);
                    }
                }
            }

            for (int i = rangeUp; i <= rangeDown; i++) {
                for (int j = rangeBehind; j <= rangeAhead; j++) {
                    if (playerMap.tileExists(i, j)) {
                        playerMap.indicate(j, i);
                    }
                }
            }

            //Draw hover if you hover a spot you can attack
            drawHoverAttack(g);
        }

        //Profile of person 1
        if (selectedPlayer != null) {
            selectedPlayer.drawAbilities(g, selectedAbility == selectedPlayer.getAbility1());
            //selectedPlayer = playerMap.findPlayer(selectedPlayer);
            //Ability selection
            if (isMouseOver(new Rectangle(10, 40, 60, 15))) {
                g.setColor(Color.GREEN);
                g.fillRect(10, 40, 60, 15);
                g.setColor(Color.BLACK);
                g.drawRect(10, 40, 60, 15);
            }
            selectedPlayer = playerMap.findPlayer(selectedPlayer);
        }

        repaint();
    }

    public void actionEnemy(int j, int i) {
        if (enemyMap.getIndication(j, i) && !enemyMap.isEmpty(j, i)) {
            for (int k = i-selectedAbility.getYAOE(); k <= i+selectedAbility.getYAOE(); k++){
                for (int l = j-selectedAbility.getXAOE(); l <= j+selectedAbility.getXAOE(); l++){
                    if (enemyMap.tileExists(k,l)){
                        ability1.actEnemy(enemyMap, l,k);
                    }
                    if (playerMap.tileExists(k,l+3)){
                        ability1.actPlayer(playerMap, l+3,k);
                    }
                }
            }
            System.out.println("bam!");
            selectedAbility = null;
            enemyMap.unIndicateAll();
            playerMap.unIndicateAll();
        }
    }

    public void actionPlayer(int j, int i) {
        if (playerMap.getIndication(j, i) && !playerMap.isEmpty(j, i) || playerMap.getIndication(j,i) && (selectedAbility instanceof AOEAbility)) {
            for (int k = i-selectedAbility.getYAOE(); k <= i+selectedAbility.getYAOE(); k++){
                for (int l = j-selectedAbility.getXAOE(); l <= j+selectedAbility.getXAOE(); l++){
                    if (playerMap.tileExists(k,l)){
                        selectedAbility.actPlayer(playerMap, l,k);
                    }
                    if (enemyMap.tileExists(k,l-3)){
                        ability1.actEnemy(enemyMap, l-3,k);
                    }
                }
            }
            System.out.println("bam?");
            selectedAbility = null;
            System.out.println(selectedPlayer.getHealth());
            enemyMap.unIndicateAll();
            playerMap.unIndicateAll();
        }
    }


    public void drawHoverAttack(Graphics g){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (isMouseOver(new Rectangle(323 + 121 * j, 108 + 121 * i, 120, 120))) {
                    if (playerMap.getIndication(j, i)) {
                        for (int k = i-selectedAbility.getYAOE(); k <= i+selectedAbility.getYAOE(); k++){
                            for (int l = j-selectedAbility.getXAOE(); l <= j+selectedAbility.getXAOE(); l++){
                                if (playerMap.tileExists(k,l)){
                                    g.setColor(Color.GREEN);
                                    g.drawRect(323 + 121 * l, 108 + 121 * k, 120, 120);
                                }
                                if (enemyMap.tileExists(k,l-3)){
                                    g.setColor(Color.GREEN);
                                    g.drawRect(686 + 121 * (l-3), 108 + 121 * k, 120, 120);
                                }
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (isMouseOver(new Rectangle(686+121*j, 108+121*i, 120, 120))) {
                    if (enemyMap.getIndication(j,i)) {
                        for (int k = i-selectedAbility.getYAOE(); k <= i+selectedAbility.getYAOE(); k++){
                            for (int l = j-selectedAbility.getXAOE(); l <= j+selectedAbility.getXAOE(); l++){
                                if (enemyMap.tileExists(k,l)){
                                    g.setColor(Color.GREEN);
                                    g.drawRect(686 + 121 * l, 108 + 121 * k, 120, 120);
                                }
                                if (playerMap.tileExists(k,l+3)){
                                    g.setColor(Color.GREEN);
                                    g.drawRect(323 + 121 * (l+3), 108 + 121 * k, 120, 120);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void drawPlayerProfiles(Graphics g){
        // profile of person 1
        if (isMouseOver(new Rectangle(323, 471, 363, 80))) {
            g.setColor(Color.GREEN);
            g.fillRect(323, 471, 363, 80);
            g.setColor(Color.BLACK);
            g.drawRect(323, 471, 363, 80);
        } else {
            //Profile of player should be here!
            g.setColor(Color.RED);
            g.fillRect(323, 471, 363, 80);
            g.setColor(Color.BLACK);
            g.drawRect(323, 471, 363, 80);
            //(Separator) look above
        }
        //Profile of player 2
        if (isMouseOver(new Rectangle(323, 551, 363, 80))) {
            g.setColor(Color.GREEN);
            g.fillRect(323, 551, 363, 80);
            g.setColor(Color.BLACK);
            g.drawRect(323, 551, 363, 80);
        } else {
            //Profile of player should be here!
            g.setColor(Color.RED);
            g.fillRect(323, 551, 363, 80);
            g.setColor(Color.BLACK);
            g.drawRect(323, 551, 363, 80);
            //(Separator) look above
        }
        //profile of player 3
        if (isMouseOver(new Rectangle(323, 631, 363, 80))) {
            g.setColor(Color.GREEN);
            g.fillRect(323, 631, 363, 80);
            g.setColor(Color.BLACK);
            g.drawRect(323, 631, 363, 80);
        } else {
            //Profile of player should be here!
            g.setColor(Color.RED);
            g.fillRect(323, 631, 363, 80);
            g.setColor(Color.BLACK);
            g.drawRect(323, 631, 363, 80);
            //(Separator) look above
        }
        //profile of player 3
        if (isMouseOver(new Rectangle(323, 711, 363, 30))) {
            g.setColor(Color.GREEN);
            g.fillRect(323, 711, 363, 80);
            g.setColor(Color.BLACK);
            g.drawRect(323, 711, 363, 80);
        } else {
            //Profile of player should be here!
            g.setColor(Color.RED);
            g.fillRect(323, 711, 363, 80);
            g.setColor(Color.BLACK);
            g.drawRect(323, 711, 363, 80);
            //(Separator) look above
        }
    }
}
