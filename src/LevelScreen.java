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
        ability1 = new SingleAbility("basic",6,0,1,2,true);
        heal = new AOEAbility("heal",0,3,0,1,1,-2.0,false);
        playerMap = new PlayerMap();
        enemyMap = new EnemyMap();
        kevin = new Player(10,"kevin",ability1);
        allen = new Player(10,"allen",heal);
        ack = new Enemy(10);


        //Add things onto the map
        playerMap.addPlayer(1,2,kevin);
        playerMap.addPlayer(2,1,allen);
        enemyMap.addEnemy(1,2,ack);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar() == 's' ){
            System.out.println("down");
            playerMap.addPlayer(1,2,kevin);
            kevin.setHealth(10);
            enemyMap.addEnemy(1,2,ack);
            ack.setHealth(10);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);

        //Yay for hardcoding
        if (isFullyClicked(new Rectangle(323, 468, 360, 80))) {
            selectedPlayer = kevin;
            selectedAbility = null;
            enemyMap.unIndicateAll();
            playerMap.unIndicateAll();
        }

        if (isFullyClicked(new Rectangle(323, 548, 360, 80))) {
            selectedPlayer = allen;
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
            selectedAbility = selectedPlayer.getAbility1();
        }

        //Action attack!
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                if (selectedPlayer != null && selectedAbility != null && isFullyClicked(new Rectangle(686 + 121 * i, 108 + 121 * j, 120, 120))) {
                    actionEnemy(i,j);
                } else if (selectedPlayer != null && selectedAbility != null && isFullyClicked(new Rectangle(323 + 121 * i, 108 + 121 * j, 120, 120))) {
                    actionPlayer(i,j);
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
            for (int j = rangeUp; j <= rangeDown; j++) {
                for (int i = rangeBehind; i <= rangeAhead; i++) {
                    if (enemyMap.tileExists(i-3,j)){
                        enemyMap.indicate(i-3, j);
                    }
                }
            }

            for (int j = rangeUp; j <= rangeDown; j++) {
                for (int i = rangeBehind; i <= rangeAhead; i++) {
                    if (playerMap.tileExists(i, j) && !selectedAbility.getEnemyOnly()) {
                        playerMap.indicate(i, j);
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
            //selectedPlayer = playerMap.findPlayer(selectedPlayer);
        }

        repaint();
    }

    public void actionEnemy(int i, int j) {
        if (enemyMap.getIndication(i, j) && !enemyMap.isEmpty(i, j)) {
            for (int k = j-selectedAbility.getYAOE(); k <= j+selectedAbility.getYAOE(); k++){
                for (int l = i-selectedAbility.getXAOE(); l <= i+selectedAbility.getXAOE(); l++){
                    if (enemyMap.tileExists(l, k)){
                        ability1.actEnemy(enemyMap, l, k);
                    }
                    if (playerMap.tileExists(k+3,l)){
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

    public void actionPlayer(int i, int j) {
        if (playerMap.getIndication(i, j) && !playerMap.isEmpty(i, j) || playerMap.getIndication(i, j) && (selectedAbility instanceof AOEAbility)) {
            for (int k = j-selectedAbility.getYAOE(); k <= j+selectedAbility.getYAOE(); k++){
                for (int l = i-selectedAbility.getXAOE(); l <= i+selectedAbility.getXAOE(); l++){
                    if (playerMap.tileExists(l, k)){
                        selectedAbility.actPlayer(playerMap, l, k);
                    }
                    if (enemyMap.tileExists(l-3,k)){
                        ability1.actEnemy(enemyMap, l-3, k);
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
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                if (isMouseOver(new Rectangle(323 + 121 * i, 108 + 121 * j, 120, 120))) {
                    if (playerMap.getIndication(i, j)) {
                        for (int k = j-selectedAbility.getYAOE(); k <= j+selectedAbility.getYAOE(); k++){
                            for (int l = i-selectedAbility.getXAOE(); l <= i+selectedAbility.getXAOE(); l++){
                                if (playerMap.tileExists(l, k)){
                                    g.setColor(Color.GREEN);
                                    g.drawRect(323 + 121 * l, 108 + 121 * k, 120, 120);
                                }
                                if (enemyMap.tileExists(l,k-3)){
                                    g.setColor(Color.GREEN);
                                    g.drawRect(686 + 121 * (l-3), 108 + 121 * k, 120, 120);
                                }
                            }
                        }
                    }
                }
            }
        }

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                if (isMouseOver(new Rectangle(686+121*i, 108+121*j, 120, 120))) {
                    if (enemyMap.getIndication(i,j)) {
                        for (int k = j-selectedAbility.getYAOE(); k <= j+selectedAbility.getYAOE(); k++){
                            for (int l = i-selectedAbility.getXAOE(); l <= i+selectedAbility.getXAOE(); l++){
                                if (enemyMap.tileExists(l,k)){
                                    g.setColor(Color.GREEN);
                                    g.drawRect(686 + 121 * l, 108 + 121 * k, 120, 120);
                                }
                                if (playerMap.tileExists(l,k+3)){
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
