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

    Player[] players;

    SingleAbility ability1;
    AOEAbility heal;
    MoveAbility move;

    LevelScreen(GameManager game){
        super(game);
        ability1 = new SingleAbility("basic",6,0,1,2,true);
        heal = new AOEAbility("heal",0,3,0,1,1,-2.0,false);
        move = new MoveAbility("step",1);
        playerMap = new PlayerMap();
        enemyMap = new EnemyMap();
        kevin = new Player(10,"kevin",ability1);
        allen = new Player(10,"allen",move);
        ack = new Enemy(10);

        //Add things onto the map
        //i is x, j is y
        playerMap.addPlayer(1,2,kevin);
        playerMap.addPlayer(2,1,allen);
        enemyMap.addEnemy(1,2,ack);

        players = new Player[3];
        players[0] = kevin;
        players[1] = allen;
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

        //Magic number storage
        int selectX = 323;
        int selectY = 468;
        int selectWidth = 360;
        int selectHeight = 80;

        for (int i = 0; i < players.length; ++i) {
            if (isFullyClicked(new Rectangle(selectX, selectY + i * selectHeight, selectWidth, selectHeight))) {
                selectedPlayer = players[i];
                selectedAbility = null;
                enemyMap.unIndicateAll();
                playerMap.unIndicateAll();
            }
        }

        //Use an ability here
        if (selectedPlayer != null && isFullyClicked(new Rectangle(10, 40, 60, 15))) {
            selectedAbility = selectedPlayer.getAbility1();
        if (selectedPlayer != null && isFullyClicked(new Rectangle(30, 30, 263, 80))) {
            selectedAbility = playerMap.findPlayer(selectedPlayer).getAbility1();
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

        //Moving move move move Actions!
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        playerMap.draw(g);
        enemyMap.draw(g);
        drawPlayerProfiles(g);

        if (selectedAbility instanceof MoveAbility && selectedPlayer != null) {
            for (int i = 0; i < 3; i++){
                for (int j = 0;j < 3; j++){
                    if ((Math.abs(i - playerMap.findPlayerY(selectedPlayer)) + Math.abs(j - playerMap.findPlayerX(selectedPlayer))) <= selectedAbility.getMoves()){
                        playerMap.indicate(j, i);
                    }
                }
            }
        } else if (selectedAbility != null && selectedPlayer != null){

            //Calculate Range of Ability!
            int rangeAhead = playerMap.findPlayerX(selectedPlayer) + selectedAbility.getXRange();
            int rangeBehind = playerMap.findPlayerX(selectedPlayer) - selectedAbility.getXRange();
            int rangeDown = playerMap.findPlayerY(selectedPlayer) + selectedAbility.getYRange();
            int rangeUp = playerMap.findPlayerY(selectedPlayer) - selectedAbility.getYRange();

            //Create Indications for ability
            //TODO FIX THIS
            for (int j = rangeUp; j <= rangeDown; j++) {
                for (int i = rangeBehind; i <= rangeAhead; i++) {
                    if (enemyMap.tileExists(i-3,j)){
                        enemyMap.indicate(i-3, j);
            for (int i = rangeUp; i <= rangeDown; i++) {
                for (int j = rangeBehind; j <= rangeAhead; j++) {
                    if (enemyMap.tileExists(i,j-3) && !selectedAbility.getPlayerOnly()){
                        enemyMap.indicate(j-3,i);
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
            if (isMouseOver(new Rectangle(30, 30, 263, 80))) {
                g.setColor(new Color(0,0,0,100));
                g.fillRect(30, 30, 263, 80);
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

    //MOVE ENEMY AND MOVE PLAYER HERE!!!

    public void drawHoverAttack(Graphics g){
        //Magic number storage
        int playerGridX = 323;
        int playerGridY = 108;

        int enemyGridX = 686;
        int enemyGridY = 108;

        int gridWidth = 120;
        int gridHeight = 120;

        int gridWidthSpace = 121;
        int gridHeightSpace = 121;

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                if (isMouseOver(new Rectangle(playerGridX + gridWidthSpace * i, playerGridY + gridHeightSpace * j, gridWidth, gridHeight))) {
                    if (playerMap.getIndication(i, j)) {
                        for (int k = j-selectedAbility.getYAOE(); k <= j+selectedAbility.getYAOE(); k++){
                            for (int l = i-selectedAbility.getXAOE(); l <= i+selectedAbility.getXAOE(); l++){
                                if (playerMap.tileExists(l, k)){
                                    g.setColor(Color.GREEN);
                                    g.drawRect(playerGridX + gridWidthSpace * l, playerGridY + gridHeightSpace * k, gridWidth, gridHeight);
                                }
                                if (enemyMap.tileExists(l,k-3)){
                                    g.setColor(Color.GREEN);
                                    g.drawRect(enemyGridX + gridWidthSpace * (l-3), enemyGridY + gridHeightSpace * k, gridWidth, gridHeight);
                                }
                            }
                        }
                    }
                }
            }
        }

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                if (isMouseOver(new Rectangle(enemyGridX+gridWidthSpace*i, enemyGridY+gridHeightSpace*j, gridWidth, gridHeight))) {
                    if (enemyMap.getIndication(i,j)) {
                        for (int k = j-selectedAbility.getYAOE(); k <= j+selectedAbility.getYAOE(); k++){
                            for (int l = i-selectedAbility.getXAOE(); l <= i+selectedAbility.getXAOE(); l++){
                                if (enemyMap.tileExists(l,k)){
                                    g.setColor(Color.GREEN);
                                    g.drawRect(enemyGridX + gridWidthSpace * l, enemyGridY + gridHeightSpace * k, gridWidth, gridHeight);
                                }
                                if (playerMap.tileExists(l,k+3)){
                                    g.setColor(Color.GREEN);
                                    g.drawRect(playerGridX + gridWidthSpace * (l+3), playerGridY + gridHeightSpace * k, gridWidth, gridHeight);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void drawPlayerProfiles(Graphics g){
        //Magic number storage
        int profileX = 323;
        int profileY = 471;
        int profileWidth = 363;
        int profileHeight = 80;

        //Draw a selection for each of the players
        for (int i = 0; i < players.length; ++i) {
            if (isMouseOver(new Rectangle(profileX, profileY + i * profileHeight, profileWidth, profileHeight))) {
                g.setColor(Color.GREEN);
                g.fillRect(profileX, profileY + i * profileHeight, profileWidth, profileHeight);
                g.setColor(Color.BLACK);
                g.drawRect(profileX, profileY + i * profileHeight, profileWidth, profileHeight);
            } else {
                g.setColor(Color.RED);
                g.fillRect(profileX, profileY + i * profileHeight, profileWidth, profileHeight);
                g.setColor(Color.BLACK);
                g.drawRect(profileX, profileY + i * profileHeight, profileWidth, profileHeight);
            }
        }
    }
}
