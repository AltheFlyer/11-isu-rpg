import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class LevelScreen extends GameScreen{
    Player selectedPlayer;
    Ability selectedAbility;
    JointMap jointMap;
    Player kevin;
    Player allen;

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

        jointMap = new JointMap();

        kevin = new Player(10,"kevin",ability1);
        allen = new Player(10,"allen",heal);
        ack = new Enemy(10);

        //Add things onto the map
        //i is x, j is y
        jointMap.addEntity(1,2,kevin);
        jointMap.addEntity(2,1,allen);
        jointMap.addEntity(4,2,ack);

        players = new Player[3];
        players[0] = kevin;
        players[1] = allen;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar() == 's' ){
            System.out.println("down");
            jointMap.addEntity(1,2,kevin);
            kevin.setHealth(10);
            players[0] = kevin;
            jointMap.addEntity(4,2,ack);
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
                if (players[i] == null) {
                    selectedPlayer = null;
                } else if (players[i].getHealth() > 0) {
                    selectedPlayer = players[i];
                } else {
                    selectedPlayer = null;
                }
                selectedAbility = null;
                jointMap.unIndicateAll();
            }
        }

        //Use an ability here
        if (selectedPlayer != null && isFullyClicked(new Rectangle(30, 30, 263, 80))) {
            selectedAbility = selectedPlayer.getAbility1();
        }

        //Action attack!
        if (selectedPlayer != null && selectedAbility != null) {
            for (int j = 0; j < 3; j++) {
                for (int i = 0; i < 6; i++) {
                    if (isFullyClicked(new Rectangle(323 + 121 * i, 108 + 121 * j, 120, 120))) {
                        action(i, j);
                    }
                }
            }
        }
        //Moving move move move Actions!
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        jointMap.draw(g);
        drawPlayerProfiles(g);

        /*
        if (selectedAbility instanceof MoveAbility && selectedPlayer != null) {
            for (int j = 0; j < 3; j++) {
                for (int i = 0; i < 3; i++) {
                    if ((Math.abs(j - jointMap.findPlayerY(selectedPlayer)) + Math.abs(j - jointMap.findPlayerX(selectedPlayer))) <= selectedAbility.getMoves()) {
                        jointMap.indicate(i,j);
                    }
                }
            }
        } else
        */
        if (selectedAbility != null && selectedPlayer != null) {
            //Calculate Range of Ability!
            int rangeAhead = selectedPlayer.getXGrid() + selectedAbility.getXRange();
            int rangeBehind = selectedPlayer.getXGrid() - selectedAbility.getXRange();
            int rangeDown = selectedPlayer.getYGrid() + selectedAbility.getYRange();
            int rangeUp = selectedPlayer.getYGrid() - selectedAbility.getYRange();

            //Create Indications for ability
            for (int j = rangeUp; j <= rangeDown; j++) {
                for (int i = rangeBehind; i <= rangeAhead; i++) {
                    if (jointMap.tileExists(i, j)) {
                        if (jointMap.getTileType(i,j) == 'e' && !selectedAbility.getPlayerOnly()) {
                            jointMap.indicate(i, j);
                        } else if (jointMap.getTileType(i,j) == 'p' && !selectedAbility.getEnemyOnly()) {
                            jointMap.indicate(i, j);
                        }
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
                g.setColor(new Color(0, 0, 0, 100));
                g.fillRect(30, 30, 263, 80);
            }
            //selectedPlayer = playerMap.findPlayer(selectedPlayer);
        }

        repaint();
    }

    //FIX THIS LATER ESPECIALLY THE SPECIFIC ACTS
    public void action (int i, int j){
        if (jointMap.getIndication(i, j) && !jointMap.isEmpty(i, j) || jointMap.getIndication(i, j) && selectedAbility instanceof AOEAbility) {
            for (int k = j - selectedAbility.getYAOE(); k <= j + selectedAbility.getYAOE(); k++) {
                for (int l = i - selectedAbility.getXAOE(); l <= i + selectedAbility.getXAOE(); l++) {
                    if (jointMap.tileExists(l, k)) {
                        //Here I know it's the same but it's just a proof of concept that you can act on enemies or players
                        selectedAbility.act(jointMap, l, k);
                    }
                }
            }
            System.out.println("bam!");
            System.out.println(selectedPlayer.getHealth());
            selectedAbility = null;
            jointMap.unIndicateAll();
        }
    }

    //MOVE ENEMY AND MOVE PLAYER HERE!!!

    public void drawHoverAttack(Graphics g){
        //Magic number storage
        int playerGridX = 323;
        int playerGridY = 108;

        //int enemyGridX = 686;
        //int enemyGridY = 108;

        int gridWidth = 120;
        int gridHeight = 120;

        int gridWidthSpace = 121;
        int gridHeightSpace = 121;

        //getX or getY
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 6; i++) {
                if (isMouseOver(new Rectangle(playerGridX + gridWidthSpace * i, playerGridY + gridHeightSpace * j, gridWidth, gridHeight))) {
                    if (jointMap.getIndication(i, j)) {
                        for (int k = j-selectedAbility.getYAOE(); k <= j+selectedAbility.getYAOE(); k++){
                            for (int l = i-selectedAbility.getXAOE(); l <= i+selectedAbility.getXAOE(); l++){
                                if (jointMap.tileExists(l, k)){
                                    g.setColor(Color.GREEN);
                                    g.drawRect(playerGridX + gridWidthSpace * l, playerGridY + gridHeightSpace * k, gridWidth, gridHeight);
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
