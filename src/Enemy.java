import utils.AnimatedSprite;

import java.awt.Graphics;
import java.awt.Color;

/**
 * [Enemy.java]
 * The abstract constructor for the basic enemy with health, stats, abilities and simple AI
 * @version 1.0
 * @author Kevin Liu
 * @since May 23, 2019
 */
abstract public class Enemy extends Entity {

    private Icon intent;
    private Ability decide;
    private AnimatedSprite gif;
    private int targetedX = -1;
    private int targetedY = -1;

    /**
     * Creates an enemy entity which doesn't need energy
     * @param x the x Location on the grid that the enemy is located on
     * @param y the y location on the grid that the enemy is located
     * @param health health of the enemy
     * @param attack the base attack value of the enemy
     * @param defence base defence value of the enemy
     * @param name the name of the enemy
     * @param animation the animation of the enemy sprite
     * @param abilities the list of the abilities for the enemy
     */
    Enemy(int x, int y, double health, double attack, double defence, String name, AnimatedSprite animation, Ability[] abilities) {
        super(health, attack, defence, name, abilities);
        setXGrid(x);
        setYGrid(y);
        gif = animation;
        for (int i = 0; i < abilities.length; i++){
            abilities[i].setEntitySource(this);
        }
    }

    /**
     * [getAbility]
     * will get the ability from the list for the enemy to use
     * @param index the index of the ability on the abilities list
     * @return the ability for th eenmy to use
     */
    public Ability getAbility(int index){
        return abilities[index];
    }

    /**
     * [drawAbilities]
     * draws the list of abilities on the main battle screen when an enemy is selected
     * @param g the graphics used to draw with
     */
    public void drawAbilities(Graphics g){
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(1049,0,317,768);
        g.setColor(Color.BLACK);
        g.drawRect(1049,0,317,768);

        //Draw the player profile at the bottom so you know!
        g.setColor(Color.ORANGE);
        g.fillRect(1069,15+105*abilities.length,60,60);
        drawHealthBar(g);
        drawEnergyBar(g);

        //Display stats
        g.drawString("Attack power: " + getAttack(), 1139, 70+105*abilities.length);
        g.drawString("Percent defence: " + (int) (getDefence() * 100) + "%", 1139, 85+105*abilities.length);

        //ABILITY ICONS
        for (int i = 0; i < abilities.length; i++) {
            //draw the abilities cyan if usable
            abilities[i].drawInfoBox(g, 1069, 105 * i);
        }
    }

    /**
     * [draw]
     * draws the enemy itself using its animation
     * @param x the x grid location of the enemy
     * @oaram y the y grid location of the enemy
     * @param g the graphics used to draw with
     * @param indicated sees if the enemy is indicated
     */
    public void draw(int x, int y, Graphics g, boolean indicated){
        g.setColor(Color.ORANGE);
        g.fillRect(x,y,120,120);

        //Animation testing
        gif.draw(g,x,y);

        g.setColor(Color.BLACK);
        if (indicated){
            g.setColor(new Color(0, 0, 0, 100));
            g.fillRect(x,y,120,120);
        }
        g.drawRect(x, y, 120, 120);
    }

    /**
     * [decide]
     * generates a next move (Ability and intent) - that the player can then react to (should be overriden).
     * @param map the map that the entities are in
     */
    abstract public void decide(JointMap map);

    /**
     * [act]
     * lets the enemy perform an action using an ability
     * @param map the map that the entities are in
     */
    abstract public void act(JointMap map);

    /**
     * [selectRandomTile]
     * performs an action with the selected ability using RANDOM targeting
     * @param ability the ability to do a random selection with
     * @param map the map that the entities are in
     */
    public void selectRandomTile(JointMap map, Ability ability) {
        int[] xTargets;
        int[] yTargets;
        int validTargets = 0;
        int counter = 0;
        int choice;

        ability.indicateValidTiles(map);

        //count up the number of targetable tiles
        for (int k = 0; k < 3; k++) {
            for (int l = 0; l < 6; l++) {
                if (map.getTargetable(l,k)){
                    validTargets += 1;
                }
            }
        }
        //Run the attack if there are valid targets
        if (validTargets > 0){
            //Create the new arrays to store all targetable tiles
            xTargets = new int[validTargets];
            yTargets = new int[validTargets];


            //Store the targetable tile locations
            for (int k = 0; k < 3; k++) {
                for (int l = 0; l < 6; l++) {
                    if (map.getTargetable(l,k)){
                        xTargets[counter] = l;
                        yTargets[counter] = k;
                        counter ++;
                    }
                }
            }

            //Choose a random tile to target out of the targetable tiles
            choice = (int)(Math.random()*counter);

            //Use the chosen ability on the selected tile
            ability.action(map, xTargets[choice], yTargets[choice]);
            targetedX = xTargets[choice];
            targetedY = yTargets[choice];
        }
    }

    /**
     * [selectRowWithMostPlayers]
     * @param map the map that all the entities are on
     * @param ability the ability that is being used
     * targets the row with the most players
     */
    public void selectRowWithMostPlayers(JointMap map, Ability ability) {
        //The abilities used are swapped from the abilities decided
        //move to row with most players
        int optimalRow = 0;
        int maxRowPlayers = 0;

        for (int y = 0; y < 3; ++y) {
            int playerCount = 0;
            for (int x = 0; x < 3; ++x) {
                if (!map.isEmpty(x, y)) {
                    playerCount++;
                    if (playerCount > maxRowPlayers) {
                        optimalRow = y;
                        maxRowPlayers = playerCount;
                    }
                }
            }
        }

        ability.indicateValidTiles(map);
        ability.action(map, getXGrid(), optimalRow);
    }

    /**
     * [getTargetedX]
     * returns the X target it is about to hit for the animations to play on the enemy turn
     * @return the X target it is about to hit
     */
    public int getTargetedX(){
        return targetedX;
    }

    /**
     * [getTargetedY]
     * returns the Y target it is about to hit for the animations to play on the enemy turn
     * @return the Y target it is about to hit
     */
    public int getTargetedY(){
        return targetedY;
    }

    /**
     * [getTargetedX]
     * sets the XTargeting for the enemy for getting for animation
     * @param targetedX the X target it is about to hit
     */
    public void setTargetedX(int targetedX){
        this.targetedX =  targetedX;
    }

    /**
     * [getTargetedY]
     * sets the YTargeting for the enemy for getting for animation
     * @param targetedY the Y target it is about to hit
     */
    public void setTargetedY(int targetedY){
        this.targetedY =  targetedY;
    }

    /**
     * [resetTargeted]
     * resets the targeted tiles that the enemies are going to hit
     */
    //-1 acts as a null check
    public void resetTargeted(){
        targetedX = -1;
        targetedY = -1;
    }

    /**
     * [setIntent]
     * selects the correct icon to draw based on the enemy's intent
     * @param icon the icon to draw on the entity
     */
    public void setIntent(Icon icon) {
        this.intent = icon;
    }

    /**
     * [getIntent]
     * returns the correct intent to draw with
     * @return intent the icon that will be drawn
     */
    public Icon getIntent() {
        return intent;
    }

    /**
     * [setDecide]
     * sets the decision of the enemy, what it will use next turn
     * @param ability the ability that the enemy is planning to use
     */
    public void setDecide(Ability ability) {
        decide = ability;
    }

    /**
     * [getDecide]
     * gets the decision of the enemy for drawing and acting
     * @return decide the ability the enemy is planning to use
     */
    public Ability getDecide() {
        return decide;
    }

    /**
     * [drawHealthBar]
     * drawing the health bar for the enemy
     * @param g the graphic used to draw with
     */
    public void drawHealthBar(Graphics g){
        //Drawing a health bar here to make it nicer
        double ratio = getHealth() / getMaxHealth();
        //Grey backing bar
        g.setColor(Color.GRAY);
        g.fillRect(1139, 33+105*abilities.length,190, 12);

        //Set healthbar color based on if friendly or not
        g.setColor(Color.GREEN);

        //Draw at bottom of screen, -10 is for the height of the bar
        g.fillRect(1139, 33+105*abilities.length, (int) (190 * ratio), 12);
        g.setColor(Color.BLACK);
        g.drawRect(1139, 33+105*abilities.length,190, 12);
        g.drawString(Math.ceil(getHealth()) + "/" + getMaxHealth(), 1169, 44+105*abilities.length);
    }

    /**
     * [drawEnergyBar]
     * drawing the energy bar of the enemy
     * @param g the graphic used to draw with
     */
    public void drawEnergyBar(Graphics g){
        double ratio = getEnergy() / getMaxEnergy();
        g.setColor(Color.GRAY);
        g.fillRect(1139, 45+105*abilities.length,190, 12);

        //Set healthbar color based on if friendly or not
        g.setColor(new Color(0,200,255));

        //Draw at bottom of screen, -10 is for the height of the bar
        g.fillRect(1139, 45+105*abilities.length, (int) (190 * ratio), 12);
        g.setColor(Color.BLACK);
        g.drawRect(1139, 45+105*abilities.length,190, 12);
        g.drawString(getEnergy() + "/" + getMaxEnergy(), 1169, 56+105*abilities.length);

    }

    /**
     * [isFriendly]
     * gets whether the entity is friendly or not
     * @return boolean, false for all enemies
     */
    public boolean isFriendly() {
        return false;
    }

}
