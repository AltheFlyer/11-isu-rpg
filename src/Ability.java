import utils.AnimatedSprite;

import java.awt.*;

/**
 * This class is for the most basic abilities, it will be overwritten by other methods!
 */
abstract public class Ability {
    private String name;
    private String desc;
    private int xRange;
    private int yRange;

    private int xAOE;
    private int yAOE;
    private boolean enemyTarget;
    private boolean friendTarget;
    private int moves;
    private Entity entitySource;
    private double energyCost;
    private int cooldown;
    private int currentCooldown;
    private AnimatedSprite animation;

    private double baseDamage;
    private double ratio;

    /**
     * [Ability]
     * Constructor for single target and aoe abilities
     * @param animation the animation that is played on ability case
     * @param name the displayed name of the ability
     * @param desc the displayed description of the ability
     * @param energyCost the energy cost of the ability
     * @param cooldown the cooldown in turns
     * @param xRange the range in the x axis in tiles
     * @param yRange the range in the y axis in tiles
     * @param baseDamage the amount of damage that the ability will do
     * @param damageRatio the percentage of the casting entity's attack to include in damage
     * @param enemyTarget whether the ability can target enemies (relative to the caster) or not
     * @param friendTarget whether the ability can target allies (relative to the caster) or not
     */
    Ability(AnimatedSprite animation, String name, String desc, double energyCost, int cooldown, int xRange, int yRange, double baseDamage, double damageRatio, boolean enemyTarget, boolean friendTarget) {
        this.name = name;
        this.desc = desc;
        this.energyCost = energyCost;
        this.cooldown = cooldown;
        currentCooldown = 0;
        this.xRange = xRange;
        this.yRange = yRange;
        this.baseDamage = baseDamage;
        this.ratio = damageRatio;
        this.enemyTarget = enemyTarget;
        this.friendTarget = friendTarget;
        this.animation = animation;
    }

    /**
     * [Ability]
     * Constructor for movement abilities
     * @param name the displayed name of the ability
     * @param desc the displayed description of the ability
     * @param energyCost the energy cost of the ability
     * @param cooldown the cooldown in turns
     * @param moves the distance that can be moved, in tiles based on manhattan distance
     */
    Ability(String name, String desc, double energyCost, int cooldown, int moves){
        this.name = name;
        this.desc = desc;
        this.energyCost = energyCost;
        this.cooldown = cooldown;
        currentCooldown = 0;
        this.moves = moves;
    }

    /**
     * action: This method will target and affect the selected tiles in a certain pattern
     * @param jointMap: The map that will be affected
     * @param i: one of the selected coordinates
     * @param j: the other selected coordinate
     * @return : it will return a value based on if an action was valid or not, if it was, it will unindicate everything and reset selectedAbility on levelscreen
     */
    abstract public void action(JointMap jointMap, int i, int j);

    /**
     * [setAnimation]
     * sets the ability's animated sprite
     * @param animation the animated sprite to use when this ability is cast
     */
    public void setAnimation(AnimatedSprite animation) {
        this.animation = animation;
    }

    /**
     * [getAnimation]
     * gets the ability's animated sprite
     * @return AnimatedSprite, the animated sprite to draw for ability casts
     */
    public AnimatedSprite getAnimation(){
        return animation;
    }

    /**
     * [getCooldown]
     * gets the maximum cooldown of an ability
     * @return cooldown, the maximum cooldown of the ability
     */
    public int getCooldown(){
        return cooldown;
    }

    /**
     * [getCurrentCooldown]
     * gets the turns before you can use an attack
     * @return currentCooldown, the number of turns before you can attack
     */
    public int getCurrentCooldown(){
        return currentCooldown;
    }

    /**
     * [lowerCooldown]
     * lowers the current cooldown of an ability by one, happens after a turn ends if the move is not currently usable
     */
    public void lowerCooldown(int amountLower){
        if (currentCooldown - amountLower < 0){
            currentCooldown = 0;
        } else {
            currentCooldown -= amountLower;
        }
    }

    /**
     * [resetCooldown]
     * Once a move has been used, it will reset the current cooldown to the max possible cooldown
     */
    public void resetCooldown(){
        currentCooldown = cooldown;
    }

    /**
     * [getEnergyCost]
     * gets the energyCost of an ability
     * @return energyCost, the energy cost of an ability
     */
    public double getEnergyCost(){
        return energyCost;
    }

    /**
     * [getXRange]
     * gets the range of an ability on the x coordinate in tiles
     * @return xRange, the range of an ability on the x coordinate in tiles
     */
    public int getXRange() {
        return xRange;
    }

    /**
     * [getYRange]
     * gets the range of an ability on the y coordinate in tiles
     * @return yRange, the range of an ability on the y coordinate in tiles
     */
    public int getYRange(){
        return yRange;
    }

    /**
     * [getBaseDamage]
     * gets the damage that the ability does
     * @return baseDamage, gets the damage that the ability does ignoring ratio
     */
    public double getBaseDamage(){
        return baseDamage;
    }

    /**
     * [getDamage]
     * gets the damage of tha ability when used, including the attack power added by the caster
     * @return double, the base damage of the ability + (the attack power of the caster, multiplied by the damage ratio)
     */
    public double getDamage() {
        return baseDamage + ratio * entitySource.getAttack();
    }

    /**
     * [getRatio]
     * gets the ratio attack conversion of the ability
     * @return ratio, the ratio attack conversion of the ability
     */
    public double getRatio(){
        return ratio;
    }
    /**
     * [getName]
     * gets the name of an ability
     * @return name, gets the name of an ability
     */
    public String getName(){
        return name;
    }

    /**
     * [getDesc]
     * gets the description of an ability
     * @return desc, the description of an ability
     */
    public String getDesc(){
        return desc;
    }

    /**
     * [getXAOE]
     * gets the AOE in the x Axis of the ability in tiles
     * @return xAOE, the AOE in the x Axis of the ability in tiles
     */
    public int getXAOE(){
        return xAOE;
    }

    /**
     * [getYAOE]
     * gets the AOE in the y Axis of the ability in tiles
     * @return yAOE, the AOE in the y Axis of the ability in tiles
     */
    public int getYAOE(){
        return yAOE;
    }

    /**
     * [setXAOE]
     * sets the AOE in the x Axis of the ability in tiles
     */
    public void setXAOE(int xAOE){
        this.xAOE = xAOE;
    }

    /**
     * [setYAOE]
     * sets the AOE in the y Axis of the ability in tiles
     */
    public void setYAOE(int yAOE){
        this.yAOE = yAOE;
    }

    /**
     * [getEnemyTarget]
     * gets if the ability can target opposing tiles
     * @return enemyTarget, if the ability can target opposing tiles
     */
    public boolean getEnemyTarget(){
        return enemyTarget;
    }

    /**
     * [getFriendTarget]
     * gets if the ability can target friendly tiles
     * @return friendTarget, if the ability can target friendly tiles
     */
    public boolean getFriendTarget(){
        return friendTarget;
    }

    //Just a little bit radical here

    /**
     * [drawSelectedArea]
     * Draws the area that will be affected by an ability
     * @param i the x of the tile that the mouse is hovered over
     * @param j the y of the tile that the mouse is hovered over
     * @param g the graphics object to draw with
     * @param jointMap map to draw the hovered square on
     */
    abstract public void drawHoverAttack(int i, int j, Graphics g, JointMap jointMap);

    /**
     * [indicateValidTiles]
     * Draws valid selection regions for the center of an ability
     * @param jointMap the map to draw on
     */
    abstract public void indicateValidTiles(JointMap jointMap);

    /**
     * [setEntitySource]
     * connects an ability to an entity
     * @param entitySource, connects an ability to an entity
     */
    public void setEntitySource(Entity entitySource){
        this.entitySource = entitySource;
    }

    /**
     * [getEntitySource]
     * gets the entity that the ability is attached to
     */
    public Entity getEntitySource(){
        return entitySource;
    }

    /**
     * [getMoves]
     * gets the amount of moves a movement ability can use
     */
    public int getMoves() {
        return moves;
    }

    //BELOW ARE SOME ABILITY CREATING ASSISTANCE METHODS!

    /**
     * [indicatedValidTileHelper]
     * Something for abilities that helps users create where an ability will indicate as valid targetable tiles
     * @param jointMap the map that the battle will take place on
     * @param rangeAhead the range ahead of the user that you can target
     * @param rangeBehind the range behind the user that you can target
     * @param rangeDown the range below the user that you can target
     * @param rangeUp the range above the user that you can target
     * @param targetEmpty determines if the ability can target empty tiles
     * @param showInvalidTiles determines if the ability when selected will indicate the valid tiles
     */
    public void indicateValidTileHelper(JointMap jointMap, int rangeAhead, int rangeBehind, int rangeDown, int rangeUp, boolean targetEmpty, boolean showInvalidTiles){
        if (entitySource.getEnergy() >= energyCost && currentCooldown <= 0) {
            for (int j = rangeUp; j <= rangeDown; j++) {
                for (int i = rangeBehind; i <= rangeAhead; i++) {
                    if (jointMap.tileExists(i, j) && getEntitySource().isAlive()) {
                        if (showInvalidTiles) {
                            jointMap.indicate(i, j);
                        }
                        if (getFriendTarget() && jointMap.getTileType(i, j) == jointMap.getTileType(getEntitySource().getXGrid(), getEntitySource().getYGrid())) {
                            jointMap.indicate(i, j);
                            //Indicate if the tile is targetable or not, at this point Single and AOE ability are used for if they can target empty tiles
                            if (targetEmpty) {
                                jointMap.isTargetable(i, j);
                            } else if (!jointMap.isEmpty(i, j)) {
                                jointMap.isTargetable(i, j);
                            }
                        }
                        if (getEnemyTarget() && jointMap.getTileType(i, j) != jointMap.getTileType(getEntitySource().getXGrid(), getEntitySource().getYGrid())) {
                            jointMap.indicate(i, j);
                            //Indicate if the tile is targetable or not, at this point Single and AOE ability are used for if they can target empty tiles
                            if (targetEmpty) {
                                jointMap.isTargetable(i, j);
                            } else if (!jointMap.isEmpty(i, j)) {
                                jointMap.isTargetable(i, j);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * [drawHoverAttackSingleHelper]
     * Something for abilities which will draw something when hovered
     * @param i the x of the tile that the mouse is hovered over
     * @param j the y of the tile that the mouse is hovered over
     * @param g the graphics object to draw with
     * @param jointMap map to draw the hovered square on
     */
    public void drawHoverAttackSingleHelper(int i, int j, Graphics g, JointMap jointMap){
        int gridX = 323;
        int gridY = 108;

        int gridWidth = 120;
        int gridHeight = 120;

        int gridWidthSpace = 121;
        int gridHeightSpace = 121;
        if (jointMap.getTargetable(i,j)){
            g.setColor(Color.GREEN);
            g.drawRect(gridX + gridWidthSpace * i, gridY + gridHeightSpace * j, gridWidth, gridHeight);
        }
    }
}
