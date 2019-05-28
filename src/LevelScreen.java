import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * This class is only used for creating dupes of level screen and testing, this class will not be used in the future
 */

public class LevelScreen extends GameScreen{
    Player selectedPlayer;
    Ability selectedAbility;
    JointMap jointMap;
    Player kevin;
    Player allen;
    Player bryan;

    Enemy ack;
    Player[] players;

    LevelScreen(GameManager game){
        super(game);
        /*
        ability1 = new SingleAbility("basic",6,0,1,2,true, false);
        heal = new AOEAbility("heal",2,0,0,1,1,-2.0,false, true);
        move = new MoveAbility("step",1);
        */
        jointMap = new JointMap();

        // TODO There is probably a better way to do this just saying
        kevin = new Player(10,"kevin",new SingleAbility("basic",6,0,1,2,true, false));
        //allen = new Player(10,"allen",new AOEAbility("heal",2,0,0,1,1,-2.0,false, true));
        allen = new Player(10,"allen",new StarAbility("star",3,0,1,2.0,true, false));
        bryan = new Player(10,"bryan",new AOEAbility("die",3,2,6, 0,1,5,true, false));

        ack = new Enemy(10, "ack", new SingleAbility("basic",6,0,1,2,true, false));

        //Add things onto the map
        //i is x, j is y
        jointMap.addEntity(1,2,kevin);
        jointMap.addEntity(0,0,bryan);
        jointMap.addEntity(2,1,allen);
        jointMap.addEntity(4,2,ack);

        players = new Player[3];
        players[0] = kevin;
        players[1] = allen;
        players[2] = bryan;
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

        //Click on the player profiles to select them and use abilities
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
                jointMap.unTargetableAll();
            }
        }

        //TODO yay i'm yellow so remove this right now it's for ending turn and letting enemies act
        //Also right now they are targeting all indicated tiles, change this, this is 100% experiment
        if (isFullyClicked(new Rectangle(323, 708, selectWidth, selectHeight))) {
            selectedAbility = null;
            jointMap.unIndicateAll();
            jointMap.unTargetableAll();
            System.out.println("End turn enemy time!");
            ack.getAbility1().indicateValidTiles(jointMap);
            for (int j = 0; j < 3; j++) {
                for (int i = 0; i < 6; i++) {
                    if (ack.getAbility1().action(jointMap, i, j)){
                        System.out.println("bam!");
                    }
                }
            }
            jointMap.unIndicateAll();
            jointMap.unTargetableAll();
        }

        //Use an ability here, Click on the ability to select it for use, it will bring up indications
        if (selectedPlayer != null && isFullyClicked(new Rectangle(30, 30, 263, 80))) {
            selectedAbility = selectedPlayer.getAbility1();
        }

        //Attempt to run an action when clicking on a certain tile,
        if (selectedPlayer != null && selectedAbility != null) {
            for (int j = 0; j < 3; j++) {
                for (int i = 0; i < 6; i++) {
                    if (isFullyClicked(new Rectangle(323 + 121 * i, 108 + 121 * j, 120, 120))) {
                        if (selectedAbility.action(jointMap, i, j)){
                            System.out.println("bam!");
                            System.out.println(selectedPlayer.getHealth());
                            //Deselect the ability
                            selectedAbility = null;
                            jointMap.unIndicateAll();
                            jointMap.unTargetableAll();
                        }
                    }
                }
            }
        }
        //Moving move move move Actions!
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Draw out the map and all of the tiles on it
        jointMap.draw(g);

        //Draw out the profile selection area
        drawPlayerProfiles(g);

        //Ending turn
        g.drawString("end turn",323,724);


        //Calculate the range for certain abilities and create indications telling you where it will hit based on the currently selected ability
        if (selectedAbility != null){
            selectedAbility.indicateValidTiles(jointMap);
            drawHoverAttack(g);
        }

        //Draw the profile of the player who is selected
        if (selectedPlayer != null) {
            selectedPlayer.drawAbilities(g, selectedAbility == selectedPlayer.getAbility1());

            //Ability selection (Only first one works right now!)
            if (isMouseOver(new Rectangle(30, 30, 263, 80))) {
                g.setColor(new Color(0, 0, 0, 100));
                g.fillRect(30, 30, 263, 80);
            }
        }

        repaint();
    }

    //MOVE ENEMY AND MOVE PLAYER HERE!!!

    //If you are hovering over a space you can attack, this will highlight those spaces
    public void drawHoverAttack(Graphics g){
        //Magic number storage
        int gridX = 323;
        int gridY = 108;

        int gridWidth = 120;
        int gridHeight = 120;

        int gridWidthSpace = 121;
        int gridHeightSpace = 121;

        //getX or getY
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 6; i++) {
                if (isMouseOver(new Rectangle(gridX + gridWidthSpace * i, gridY + gridHeightSpace * j, gridWidth, gridHeight))) {
                    if (jointMap.getIndication(i, j)) {
                        selectedAbility.drawHoverAttack(i,j,g,jointMap);
                    }
                }
            }
        }
    }

    //If you hover over a player profile, it will highlight and turn green!
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
