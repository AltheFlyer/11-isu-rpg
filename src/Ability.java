import java.awt.*;

/**
 * This class is for the most basic abilities, it will be overwritten by other methods!
 */

abstract public class Ability {
    private String name;
    private String desc;
    private int xRange;
    private int yRange;
    private int status;
    private double damage;
    private int cost;
    private int xAOE;
    private int yAOE;
    private Tile[][] getValidTiles;
    private boolean enemyTarget;
    private boolean friendTarget;
    private int moves;
    private Entity entitySource;

    //Constructor for Single target and AOE abilities
    Ability(String name, int xRange, int yRange, int status, double damage, boolean enemyTarget, boolean friendTarget) {
        this.name = name;
        this.xRange = xRange;
        this.yRange = yRange;
        this.status = status;
        this.damage = damage;
        this.enemyTarget = enemyTarget;
        this.friendTarget = friendTarget;
    }

    //Constructor for movement abilities
    Ability(String name, int moves){
        this.name = name;
        this.moves = moves;
    }

    public void indicate() {

    }

    //Should know where the person is casting is
    //Give entity information
    //Get entitySource (Entity entitySource)
    //have access to jointMap
    //getValidTiles - call indicate
    //drawAffectedTiles(x,y) (indicate for AOE) - drawHoverAttack
    //DO EVERYTHING IN HERE NOT LEVEL SCREEN

    //This piece of code will run after a person has clicked tile after an ability has been selected, it will attempt to cast the ability selected on the hovered tiles
    //The boolean return is for if the action was taken or not
    abstract public boolean action(JointMap jointMap, int i, int j);

    public int getXRange() {
        return xRange;
    }

    public int getStatus(){
        return status;
    }

    public int getYRange(){
        return yRange;
    }

    public double getDamage(){
        return damage;
    }

    public String getName(){
        return name;
    }

    public String getDesc(){
        return desc;
    }

    public int getXAOE(){
        return xAOE;
    }

    public int getYAOE(){
        return yAOE;
    }

    public void setXAOE(int xAOE){
        this.xAOE = xAOE;
    }

    public void setYAOE(int yAOE){
        this.yAOE = yAOE;
    }

    public void setEnemyOnly(){
        enemyTarget = true;
    }

    public void setFriendOnly(){
        friendTarget = true;
    }

    public boolean getEnemyTarget(){
        return enemyTarget;
    }

    public boolean getFriendTarget(){
        return friendTarget;
    }

    //Just a little bit radical here

    /**
     * [drawSelectedArea]
     * Draws the area that will be affected by an ability
     * @param g the graphics object to draw with
     */
    abstract public void drawHoverAttack(int i, int j, Graphics g, JointMap jointMap);

    /**
     * [indicateValidTiles]
     * Draws valid selection regions for the center of an ability
     * @param
     */
    abstract public void indicateValidTiles(JointMap jointMap);

    public void setEntitySource(Entity entitySource){
        this.entitySource = entitySource;
    }
    public Entity getEntitySource(){
        return entitySource;
    }

    public int getMoves() {
        return moves;
    }
}
