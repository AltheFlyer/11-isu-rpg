import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * This class is only used for creating dupes of level screen and testing, this class will not be used in the future
 */

public class LevelScreen extends GameScreen{
    Player selectedPlayer;
    Ability selectedAbility;

    Enemy selectedEnemy;

    JointMap jointMap;
    Player kevin;
    Player allen;
    Player bryan;

    Enemy ack;
    Enemy bck;
    Enemy cck;
    Player[] players;

    LevelScreen(GameManager game){
        super(game);
        /*
        ability1 = new SingleAbility("basic",6,0,1,2,true, false);
        heal = new AOEAbility("heal",2,0,0,1,1,-2.0,false, true);
        move = new MoveAbility("step",1);
        new AOEAbility("die",3,2,6, 0,1,5,true, false)
        new StarAbility("star",3,0,1,2.0,true, false)
        new CombinationAbility("back",5,2,1,4,true, false);
        */
        jointMap = new JointMap();

        Ability[] kevinAbilities = new Ability[]{
            new SingleAbility("basic","a basic attack that will hit an enemy in front of you", 20,1,6,0,1,2,true, false),
            new BasicMoveAbility("step","movement to an adjacent tile", 30,1,1),
            new AOEAbility("heal","a vertical AOE heal on allies",50,2,2,0,0,2,1,-2.0,false, true),
            new SingleAbility("basic2","a basic attack that will hit an enemy in front of you", 20,1,6,0,1,2,true, false),
            new SingleAbility("basic3","a basic attack that will hit an enemy in front of you", 20,1,6,0,1,2,true, false),
        };

        Ability[] allenAbilities = new Ability[]{
            new CombinationAbility("back","A single target attack that also pushes you back by 1", 60,3,5,2,1,4,true, false),
            new BasicMoveAbility("step","movement to an adjacent tile",30,1,1),
            new AOEAbility("sacrifice","A sacrifice that will deal massive damage to everyone",50,4,0,0,6,2,1,5,true, true)
        };

        Ability[] bryanAbilities = new Ability[]{
            new StarAbility("star","An AOE ability that will hit in a star shaped area",50,2,5,0,1,3.0,true, false),
            new BasicMoveAbility("step","movement to an adjacent tile",30,1,1),
            new SpearAbility("spear","A very deadly single target spear with short range",100,10, 100)
        };

        Ability[] ackAbilities = new Ability[]{
            new SingleAbility("basic","",0,0,6,0,1,2,true, false),
            new SingleAbility("healSelf","",0,0,0,0,1,-3,false, true)
        };

        Ability[] bckAbilities = new Ability[]{
            new SingleAbility("basic","",0,0,6,0,1,2,true, false),
            new SingleAbility("healSelf","",0,0,0,0,1,-3,false, true)
        };

        Ability[] cckAbilities = new Ability[]{
                new SingleAbility("basic","",0,0,6,0,1,2,true, false),
                new SingleAbility("healSelf","",0,0,0,0,1,-3,false, true)
        };

        // TODO There is probably a better way to do this just saying
        kevin = new Player(10,100,"magenta",kevinAbilities);
        //allen = new Player(10,"allen",new AOEAbility("heal",2,0,0,1,1,-2.0,false, true));
        allen = new Player(10,100,"yellow",allenAbilities);
        bryan = new Player(10,100,"cyan",bryanAbilities);

        ack = new Enemy(10, "ack",ackAbilities);
        bck = new Enemy(10, "bck",bckAbilities);
        cck = new Enemy(10, "cck",cckAbilities);

        //Add things onto the map
        //i is x, j is y
        jointMap.addEntity(1,2,kevin);
        jointMap.addEntity(0,0,bryan);
        jointMap.addEntity(2,1,allen);
        jointMap.addEntity(4,2,ack);
        jointMap.addEntity(3,1,bck);
        jointMap.addEntity(4,0,cck);

        players = new Player[3];
        players[0] = kevin;
        players[1] = allen;
        players[2] = bryan;

        allen.statuses.add(new CursedStatus(allen, 1));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar() == 's' ){
            System.out.println("down");
            if (jointMap.isEmpty(1,2)){
                jointMap.addEntity(1,2,kevin);
            }

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

        //If you click on player on map with no selected abilities, you can swap players
        //You can also select enemies this way
        if (selectedAbility == null){
            for (int j = 0; j < 3; j++) {
                for (int i = 0; i < 3; i++) {
                    if (isFullyClicked(new Rectangle(323 + 121 * i, 108 + 121 * j, 120, 120))) {
                        selectedPlayer = ((Player)jointMap.getEntity(i,j));
                        jointMap.unIndicateAll();
                        jointMap.unTargetableAll();
                    }
                }
            }

            for (int j = 0; j < 3; j++) {
                for (int i = 3; i < 6; i++) {
                    if (isFullyClicked(new Rectangle(323 + 121 * i, 108 + 121 * j, 120, 120))) {
                        selectedEnemy = ((Enemy)jointMap.getEntity(i,j));
                        jointMap.unIndicateAll();
                        jointMap.unTargetableAll();
                    }
                }
            }
        }

        //TODO end turn!
        //Also right now they are targeting all indicated tiles, change this, this is 100% experiment
        if (isFullyClicked(new Rectangle(323, 8, selectWidth, selectHeight))) {
            //End of player turn
            jointMap.procPlayerStatus();

            selectedAbility = null;
            System.out.println("End turn enemy time!");
            //Enemy turn run through
            jointMap.runEnemyTurnActions();

            //End of enemy turn
            jointMap.procEnemyStatus();

            //Start of new player turn
            for (int i = 0; i < players.length; i++){
                players[i].gainEnergy(30);
                players[i].endTurnLowerCooldown();
            }
            selectedPlayer = null;
            selectedAbility = null;
        }

        //Use an ability here, Click on the ability to select it for use, it will bring up indications
        if (selectedPlayer != null) {
            for (int i = 0; i < selectedPlayer.totalAbilities(); i++) {
                if (isFullyClicked(new Rectangle(30, 30+90*i, 263, 80))) {
                    jointMap.unIndicateAll();
                    jointMap.unTargetableAll();

                    //You can click on the ability again to deselect it
                    if (selectedAbility == selectedPlayer.getAbility(i)) {
                        selectedAbility = null;
                    } else {
                        selectedAbility = selectedPlayer.getAbility(i);
                    }
                }
            }
        }

        //Attempt to run an action when clicking on a certain tile,
        if (selectedPlayer != null && selectedAbility != null) {
            for (int j = 0; j < 3; j++) {
                for (int i = 0; i < 6; i++) {
                    if (isFullyClicked(new Rectangle(323 + 121 * i, 108 + 121 * j, 120, 120))) {
                        if (selectedAbility.action(jointMap, i, j)){
                            //attacks will use up energy!
                            selectedPlayer.useEnergy(selectedAbility.getEnergyCost());
                            selectedAbility.resetCooldown();
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
        g.drawString("end turn",323,/*7*/24);

        //Calculate the range for certain abilities and create indications telling you where it will hit based on the currently selected ability
        if (selectedAbility != null){
            selectedAbility.indicateValidTiles(jointMap);
            drawHoverAttack(g);
        }

        //Draw the abilities of the profile of the player who is selected
        if (selectedPlayer != null) {
            selectedPlayer.drawAbilities(g, selectedAbility);

            for (int i = 0; i < selectedPlayer.totalAbilities(); ++i) {
                if (isMouseOver(new Rectangle(30, 15+105*i, 263, 100))) {
                    g.setColor(new Color(0, 0, 0, 100));
                    g.fillRect(30, 15+105*i, 263, 100);
                    //Will make it easier to see which tiles can be targetable
                    if (selectedAbility == null) {
                        jointMap.unIndicateAll();
                        jointMap.unTargetableAll();
                        selectedPlayer.getAbility(i).indicateValidTiles(jointMap);

                    }
                }
            }
        }

        //Enemy info
        if (selectedEnemy != null) {
            selectedEnemy.drawAbilities(g);
        }

        //Draw icons from entities (enemy intents, etc)
        jointMap.drawIcons(g, getMouseX(), getMouseY());

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
            } else {
                g.setColor(Color.RED);
            }
            g.fillRect(profileX, profileY + i * profileHeight, profileWidth, profileHeight);
            g.setColor(Color.BLACK);
            g.drawRect(profileX, profileY + i * profileHeight, profileWidth, profileHeight);
            g.drawString(players[i].getName(),profileX+10, profileY + 15 + i*80);
        }
    }
}
