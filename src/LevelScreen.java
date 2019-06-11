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
    Ability animatedAbility;
    int animatedX;
    int animatedY;

    Enemy selectedEnemy;

    JointMap jointMap;
    Player kevin;
    Player allen;
    Player bryan;
    Player[] players;

    Enemy[] enemies = new Enemy[9];

    //The animationTime is for enemy turn, for those abilities without animations
    long animationTime = 1000;


    Clock playerClock, enemyClock;
    boolean enemyTurn = false;
    //Counter for amount of enemies
    int counter = 0;
    int turnNumber = 1;

    /**
     * [LevelScreen]
     * @param game the currently running game
     * @param enemySet the list of enemies to add to the encounter
     */
    LevelScreen(GameManager game, Enemy[] enemySet){
        super(game);

        jointMap = new JointMap();

        players = GameIO.getBattleLayout();

        //Adds players onto map
        for (int i = 0; i < players.length; ++i) {
            jointMap.addEntity(players[i].getXGrid(), players[i].getYGrid(), players[i]);
        }

        //Add enemies to grid
        enemies = enemySet;

        for (int i = 0; i < enemies.length; i++){
            jointMap.addEntity(enemies[i].getXGrid(), enemies[i].getYGrid(), enemies[i]);
        }

        playerClock = new Clock(5);
        enemyClock = new Clock(5);

        //0th turn processing
        for (int i = 0; i < enemies.length; ++i) {
            jointMap.generateEnemyDecisions(enemies[i]);
        }
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
                        selectedAbility = null;
                        selectedPlayer = ((Player) jointMap.getEntity(gridX, gridY));
                        jointMap.unIndicateAll();
                        jointMap.unTargetableAll();
                        //Enemy selection on the right x = {3, 4, 5}
                    } else {
                        selectedEnemy = ((Enemy) jointMap.getEntity(gridX, gridY));
                        jointMap.unIndicateAll();
                        jointMap.unTargetableAll();
                    }
                //Ability use
                } else if ((selectedPlayer != null) && (selectedAbility != null) && animatedAbility == null) {
                    if (jointMap.getTargetable(gridX, gridY)) {

                        //attacks will use up energy!

                        if (selectedAbility.getAnimation() != null) {
                            //Setting up the ability to be animated
                            animatedAbility = selectedAbility;
                            animatedX = gridX;
                            animatedY = gridY;
                            animatedAbility.getAnimation().reset();
                        } else {
                            //Use the ability here if there is no animation
                            selectedAbility.action(jointMap, gridX, gridY);
                            jointMap.checkAlive();
                        }

                        enemyClock.resetElapsed();

                        selectedPlayer.useEnergy(selectedAbility.getEnergyCost());
                        selectedAbility.resetCooldown();
                        System.out.println(selectedAbility.getName());
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

        //Animating abilities
        if (animatedAbility != null) {
            if (animatedAbility.getAnimation() != null) {
                jointMap.animateAttack(g, animatedAbility.getAnimation(), animatedX, animatedY);
                coverUp(g);
                if (enemyClock.getElapsedMilli() >= animatedAbility.getAnimation().getTotalTime()) {
                    animatedAbility.action(jointMap, animatedX, animatedY);
                    jointMap.checkAlive();
                    animatedAbility = null;
                    animatedX = 0;
                    animatedY = 0;
                }
            }
        }

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
        
        //Testing with playerClock and enemy turn
        if (enemyTurn) {
            if (counter >= enemies.length) {
                //skip
            } else if (enemies[counter] == null) {
                counter++;
            } else if (!enemies[counter].isAlive()){
                counter++;
            } else if (playerClock.getElapsedMilli() > animationTime) {
                //Reset the targeting for the last enemy
                selectedEnemy.resetTargeted();
                //Cool indication thing for the player to see so it's like the enemies are taking their turn
                selectedEnemy = enemies[counter];

                //The enemy acts, targetedX and Y are used for animation of the enemy's attack
                jointMap.runEnemyActions(enemies[counter]);
                jointMap.checkAlive();
                selectedEnemy.getTargetedX();
                selectedEnemy.getTargetedY();
                selectedEnemy.getDecide().indicateValidTiles(jointMap);

                //Reset time and the animation drawn
                if (selectedEnemy.getDecide().getAnimation() != null) {
                    selectedEnemy.getDecide().getAnimation().reset();
                    animationTime = selectedEnemy.getDecide().getAnimation().getTotalTime();
                } else {
                    animationTime = 1000;
                }
                playerClock.resetElapsed();
                counter++;
            }

            //images
            if (playerClock.getElapsedMilli() < animationTime && selectedEnemy.getDecide().getAnimation() != null && selectedEnemy.getTargetedX()>=0 && selectedEnemy.getTargetedY()>=0){
                jointMap.animateAttack(g,selectedEnemy.getDecide().getAnimation(),selectedEnemy.getTargetedX(),selectedEnemy.getTargetedY());
            }

            if (counter >= enemies.length && playerClock.getElapsedMilli() > animationTime){
                enemyTurn = false;
                counter = 0;
                //End of enemy turn
                jointMap.procEnemyStatus();

                //Start of new player turn
                turnNumber++;
                for (int i = 0; i < players.length; i++){
                    players[i].gainEnergy(40);
                    players[i].endTurnLowerCooldown();
                }

                for (int i = 0; i < enemies.length; i++){
                    if (enemies[i] != null) {
                        jointMap.generateEnemyDecisions(enemies[i]);
                        enemies[i].endTurnLowerCooldown();
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

        playerClock.updateElapsed();
        enemyClock.updateElapsed();


       //Check if all players are still alive
        if (arePlayersDead()) {
            loseBattle(g);
        } else if (areEnemiesDead()) {
            winBattle(g);
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
     * [coverUp]
     * @param g
     * Creates a border around the main battle that covers up any stray animations
     */
    public void coverUp(Graphics g){
        g.setColor(new Color(238,238,238));
        g.fillRect(0,0,323,768);
        g.fillRect(0,0,1366,108);
        g.fillRect(1049,0,317,768);
        g.fillRect(0,471,1366,337);
    }

    /**
     * [endTurn]
     * runs enemy actions, generates intents and resets player and enemy values for their respective turns
     */
    public void endTurn() {
        if (!enemyTurn) {
            //Proc the player Status effect and makes it so it doesn't happen over and over if you press end turn
            jointMap.procPlayerStatus();

            //End of player turn
            selectedAbility = null;

            System.out.println("End turn enemy time!");

            selectedEnemy = enemies[0];
            //EnemyTurn is true
            enemyTurn = true;
        }
    }

    /**
     * [winBattle]
     * handles end of battle operations (saving inventory and progression) if all enemies are defeated with at least 1
     * live player
     * @param g the graphics object to draw with
     */
    public void winBattle(Graphics g) {
        //Save inventory, progression
        //This is just a test to see it works
        //getIO().setInventory(inventory);
        //getIO().writeInventory();
        System.out.println(getIO().getCurrentPeriod());
        getIO().setTimeState(getIO().getCurrentPeriod() + 1, getIO().getCurrentDay());
        getIO().writeTimeState();

        g.drawString("WINNER", 500, 500);
        setScreen(new LoadingScreen(getGame()));
    }

    /**
     * [loseBattle]
     * handles loss condition (if all players die) and give the player the option of restarting the encounter
     * or returning to map
     * @param g the graphics object to draw with
     */
    public void loseBattle(Graphics g) {
        //Go back to map OR restart level
        g.drawString("LOSER", 500, 500);
    }

    /**
     * [arePlayersDead]
     * checks if all players are dead
     * @return boolean, whether ALL players are dead or not
     */
    public boolean arePlayersDead() {
        for (int i = 0; i < players.length; ++i) {
            if (players[i].isAlive()) {
                return false;
            }
        }
        return true;
    }

    /**
     * [areEnemiesDead]
     * checks if all enemies are dead or not
     * @return boolean, if ALL enemies are dead
     */
    public boolean areEnemiesDead() {
        for (int i = 0; i < enemies.length; ++i) {
            if (enemies[i].isAlive()) {
                return false;
            }
        }
        return true;
    }
}
