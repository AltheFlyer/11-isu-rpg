import utils.AnimatedSprite;
import utils.TextDrawer;

import java.awt.*;

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

        //ABILITY ICONS
        for (int i = 0; i < abilities.length; i++) {
            //draw the abilities cyan if usable
            abilities[i].drawInfoBox(g, 1069, 105 * i);

            /*
            if (abilities[i].getEnergyCost() > getEnergy() || abilities[i].getCurrentCooldown() > 0) {
                g.setColor(new Color(255, 150, 200));
            } else{
                g.setColor(new Color(0, 200, 255));
            }
            g.fillRect(1069, 15 + 105 * i, 264, 100);

            //Cooldown bar!
            g.setColor(new Color(0, 0, 0, 50));
            if (abilities[i].getCurrentCooldown() > 0) {
                g.fillRect(1069, 15 + 105 * i, 264/abilities[i].getCooldown()*(abilities[i].getCurrentCooldown()), 100);
            }

            g.setColor(Color.BLACK);
            //Drawing the name of the ability and a box around it

            g.drawString(abilities[i].getName(), 1079, 32+105*i);
            g.drawRect(1069,15+105*i,100,22);

            //Drawing the damage for an ability
            g.drawString("Damage: " + abilities[i].getDamage(), 1179, 32+105*i);
            g.drawRect(1169,15+105*i,163,22);

            //Drawing the description
            TextDrawer drawer = new TextDrawer(g,abilities[i].getDesc(), 1079, 54+105*i,250);
            drawer.drawText(g);
            */
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

    public int getTargetedX(){
        return targetedX;
    }

    public int getTargetedY(){
        return targetedY;
    }

    public void setTargetedX(int targetedX){
        this.targetedX =  targetedX;
    }

    public void setTargetedY(int targetedY){
        this.targetedY =  targetedY;
    }

    //-1 acts as a null check
    public void resetTargeted(){
        targetedX = -1;
        targetedY = -1;
    }

    public void setIntent(Icon icon) {
        this.intent = icon;
    }

    public Icon getIntent() {
        return intent;
    }

    public void setDecide(Ability ability) {
        decide = ability;
    }

    public Ability getDecide() {
        return decide;
    }

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
