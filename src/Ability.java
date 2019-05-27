import java.awt.*;

/**
 * This class is only used for creating dupes of Ability and testing, this class will not be used in the future
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
    private boolean enemyOnly;
    private boolean playerOnly;
    private int moves;
    private Entity entitySource;

    //Constructor for Single target and AOE abilities
    Ability(String name, int xRange, int yRange, int status, double damage, boolean enemyOnly) {
        this.name = name;
        this.xRange = xRange;
        this.yRange = yRange;
        this.status = status;
        this.damage = damage;
        this.enemyOnly = enemyOnly;
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
    public void act(){

    }


    public void act(JointMap jointMap, int x, int y) {
        jointMap.target(x, y, damage, status);
    }

    public int getXRange() {
        return xRange;
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
        enemyOnly = true;
    }

    public void setPlayerOnly(){
        playerOnly = true;
    }

    public boolean getEnemyOnly(){
        return enemyOnly;
    }

    public boolean getPlayerOnly(){
        return playerOnly;
    }

    //Just a little bit radical here

    /**
     * [drawSelectedArea]
     * Draws the area that will be affected by an ability
     * @param g the graphics object to draw with
     */
    public void drawSelectedArea(Graphics g) {

    }

    /**
     * [indicateValidTiles]
     * Draws valid selection regions for the center of an ability
     * @param
     */
    public void indicateValidTiles(JointMap jointMap) {
        //Calculate Range of Ability!
        int rangeAhead = entitySource.getXGrid() + xRange;
        int rangeBehind = entitySource.getXGrid() - xRange;
        int rangeDown = entitySource.getYGrid() + yRange;
        int rangeUp = entitySource.getYGrid() - yRange;

        //Draw out indications for ability
        for (int j = rangeUp; j <= rangeDown; j++) {
            for (int i = rangeBehind; i <= rangeAhead; i++) {
                if (jointMap.tileExists(i, j)) {
                    if (jointMap.getTileType(i, j) == 'e' && !this.getPlayerOnly()) {
                        jointMap.indicate(i, j);
                    } else if (jointMap.getTileType(i, j) == 'p' && !this.getEnemyOnly()) {
                        jointMap.indicate(i, j);
                    }
                }
            }
        }
    }

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
