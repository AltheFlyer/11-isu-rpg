import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class LevelScreen extends GameScreen{
    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);
    }

    Player selectedPlayer;
    Ability selectedAbility;

    Enemy selectedEnemy;

    JointMap jointMap;
    Player kevin;
    Player allen;
    Player bryan;
    Player[] players;

    Enemy[] enemies = new Enemy[9];


    Clock clock;
    boolean enemyTurn = false;
    //Counter for amount of enemies
    int counter = 0;
    int turnNumber = 1;

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
            new SingleAbility("basic","A basic attack that hits a random target in front",0,0,6,0,1,2,true, false),
            new SingleAbility("healSelf","A basic self heal",0,0,0,0,1,-3,false, true)
        };

        Ability[] bckAbilities = new Ability[]{
            new SingleAbility("basic","A basic attack that hits a random target in front",0,0,6,0,1,2,true, false),
            new SingleAbility("healSelf","A basic self heal",0,0,0,0,1,-3,false, true)
        };

        Ability[] cckAbilities = new Ability[]{
                new SingleAbility("basic","A basic attack that hits a random target in front",0,0,6,0,1,2,true, false),
                new SingleAbility("healSelf","A basic self heal",0,0,0,0,1,-3,false, true)
        };

        //TESTING GAME IO
        //Generates array of players to be place on the grid
        players = GameIO.getBattleLayout();


        // TODO There is probably a better way to do this just saying
        //kevin = new Player(10,100,"magenta",kevinAbilities);
        kevin = players[0];//GameIO.generatePlayer("players/kevin.txt");
        //allen = new Player(10,"allen",new AOEAbility("heal",2,0,0,1,1,-2.0,false, true));
        //allen = new Player(10,100,"yellow",allenAbilities);
        allen = players[1];//GameIO.generatePlayer("players/allen.txt");
        //bryan = new Player(10,100,"cyan",bryanAbilities);
        bryan = players[2];//GameIO.generatePlayer("players/bryan.txt");

        for (int i = 0; i < 3; i++){
            enemies[i] = new TestEnemy();
        }
        enemies[3] = new TutorialEnemy(5, 0);

        /*
        ack = new TestEnemy();//10, "ack",ackAbilities);
        bck = new TestEnemy();//10, "bck",bckAbilities);
        cck = new TestEnemy();//10, "cck",cckAbilities);
        */

        //Add things onto the map
        //i is x, j is y
        /*
        jointMap.addEntity(1,2,kevin);
        jointMap.addEntity(0,0,bryan);
        jointMap.addEntity(2,1,allen);
         */

        //Adds players onto map
        for (int i = 0; i < players.length; ++i) {
            jointMap.addEntity(players[i].getXGrid(), players[i].getYGrid(), players[i]);
        }

        jointMap.addEntity(4,2,enemies[0]);
        jointMap.addEntity(3,1,enemies[1]);
        jointMap.addEntity(4,0,enemies[2]);
        jointMap.addEntity(5, 0, enemies[3]);

        //players = new Player[3];
        //players[0] = kevin;
        //players[1] = allen;
        //players[2] = bryan;

        allen.statuses.add(new CursedStatus(allen, 1));
        clock = new Clock();
    }

    /**
     * [LevelScreen]
     * @param game the currently running game
     * @param enemySet the list of enemies to add to the encounter
     */
    LevelScreen(GameManager game, Enemy[] enemySet){
        super(game);

        jointMap = new JointMap();

        players = GameIO.getBattleLayout();
        kevin = players[0];
        allen = players[1];
        bryan = players[2];

        //Adds players onto map
        for (int i = 0; i < players.length; ++i) {
            jointMap.addEntity(players[i].getXGrid(), players[i].getYGrid(), players[i]);
        }

        //Add enemies to grid
        enemies = enemySet;

        for (int i = 0; i < enemies.length; i++){
            jointMap.addEntity(enemies[i].getXGrid(), enemies[i].getYGrid(), enemies[i]);
        }

        clock = new Clock();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar() == 's' ){
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

        //Check for a click within the main grid to perform actions: entity selection or ability use
        if (isFullyClicked(new Rectangle(323, 108, 121 * 6, 121 * 3))) {
            //Get grid x and y within the 6x3 grid
            int gridX = (getMouseX() - 323) / 121;
            int gridY = (getMouseY() - 108) / 121;

            //Confirm that the tile was specifically chosen
            if (isFullyClicked(new Rectangle(323 + gridX * 121, 108 + gridY * 121, 120, 120))) {
                //Entity selection
                if (!jointMap.getTargetable(gridX, gridY)){
                    //Player selection on the left x = {0, 1, 2}
                    if (gridX < 3) {
                        selectedPlayer = ((Player) jointMap.getEntity(gridX, gridY));
                        jointMap.unIndicateAll();
                        jointMap.unTargetableAll();
                        selectedAbility = null;
                        //Enemy selection on the right x = {3, 4, 5}
                    } else {
                        selectedEnemy = ((Enemy) jointMap.getEntity(gridX, gridY));
                        jointMap.unIndicateAll();
                        jointMap.unTargetableAll();
                    }
                //Ability use
                } else if ((selectedPlayer != null) && (selectedAbility != null)) {
                    if (selectedAbility.action(jointMap, gridX, gridY)) {
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

        //End of turn
        if (isFullyClicked(new Rectangle(323, 8, selectWidth, selectHeight))) {
            endTurn();
        }

        //Use an ability here, Click on the ability to select it for use, it will bring up indications
        if (selectedPlayer != null) {
            for (int i = 0; i < selectedPlayer.totalAbilities(); i++) {
                if (isFullyClicked(new Rectangle(30, 15+105*i, 263, 100))) {
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
            jointMap.unIndicateAll();
            selectedAbility.indicateValidTiles(jointMap);
            drawHoverAttack(g);
        }

        int gridX = 323;
        int gridY = 108;

        int gridWidth = 120;
        int gridHeight = 120;

        int gridWidthSpace = 121;
        int gridHeightSpace = 121;

        //getX or getY
        for (int j = 0; j < 3; j++) {
            for (int i = 3; i < 6; i++) {
                if (!jointMap.isEmpty(i,j)) {
                    if (isMouseOver(new Rectangle(gridX + gridWidthSpace * i, gridY + gridHeightSpace * j, gridWidth, gridHeight))) {
                    }
                }
            }
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
        if (selectedEnemy != null && selectedEnemy.isAlive()) {
            selectedEnemy.drawAbilities(g);
            g.setColor(Color.GREEN);
            g.drawRect(323+selectedEnemy.getXGrid()*121, 108+selectedEnemy.getYGrid()*121, 120,120);

            for (int i = 0; i < selectedEnemy.totalAbilities(); ++i) {
                if (isMouseOver(new Rectangle(1069, 15+105*i, 263, 100))) {
                    g.setColor(new Color(0, 0, 0, 100));
                    g.fillRect(1069, 15+105*i, 263, 100);
                    //Will make it easier to see which tiles can be targetable
                    if (selectedAbility == null) {
                        jointMap.unIndicateAll();
                        jointMap.unTargetableAll();
                        selectedEnemy.getAbility(i).indicateValidTiles(jointMap);
                    }
                }
            }
        }

        //Testing with clock and enemy turn
        if (enemyTurn) {
            if (counter >= enemies.length) {
                //skip
            } else if (enemies[counter] == null) {
                counter++;
            } else if (!enemies[counter].isAlive()){
                counter++;
            } else if (clock.getElapsedMilli() > 1000) {
                //Cool indication thing for the player to see so it's like the enemies are taking their turn
                selectedEnemy = enemies[counter];

                //selec = enemies[counter].getDecide();

                clock.update();

                //The enemy acts
                jointMap.runEnemyActions(enemies[counter], g);
                selectedEnemy.getDecide().indicateValidTiles(jointMap);
                jointMap.runEnemyIntent(enemies[counter]);
                counter++;
            }

            if (counter >= enemies.length && clock.getElapsedMilli() > 1000){
                enemyTurn = false;
                counter = 0;
                //End of enemy turn
                jointMap.procEnemyStatus();

                //Start of new player turn
                turnNumber++;
                for (int i = 0; i < players.length; i++){
                    players[i].gainEnergy(30);
                    players[i].endTurnLowerCooldown();
                    //Execute if dies to status effect at the end of turn
                    if (!players[i].isAlive()){
                        jointMap.target(players[i].getXGrid(), players[i].getYGrid(),0,0);
                    }
                }
                selectedPlayer = null;
                selectedEnemy = null;
                selectedAbility = null;
                jointMap.unIndicateAll();
                jointMap.unTargetableAll();
            }
        }

        //Draw icons from entities (enemy intents, etc)
        jointMap.drawIcons(g, getMouseX(), getMouseY());
        clock.updateElapsed();
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

    /**
     * [endTurn]
     * runs enemy actions, generates intents and resets player and enemy values for their respective turns
     */
    public void endTurn() {
        //End of player turn
        jointMap.procPlayerStatus();
        selectedAbility = null;
        System.out.println("End turn enemy time!");

        //EnemyTurn is true
        enemyTurn = true;
    }
}
