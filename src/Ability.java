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
    public boolean action(JointMap jointMap, int i, int j){
        if (jointMap.getIndication(i, j) && !jointMap.isEmpty(i, j) || jointMap.getIndication(i, j) && this instanceof AOEAbility) {
            for (int k = j - yAOE; k <= j + yAOE; k++) {
                for (int l = i - xAOE; l <= i + xAOE; l++) {
                    if (jointMap.tileExists(l, k)) {
                        jointMap.target(l, k, damage, status);
                    }
                }
            }
            return true;
        }
        return false;
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
        enemyTarget = true;
    }

    public void setPlayerOnly(){
        friendTarget = true;
    }

    public boolean getEnemyOnly(){
        return enemyTarget;
    }

    public boolean getPlayerOnly(){
        return friendTarget;
    }

    //Just a little bit radical here

    /**
     * [drawSelectedArea]
     * Draws the area that will be affected by an ability
     * @param g the graphics object to draw with
     */
    public void drawHoverAttack(int i, int j, Graphics g, JointMap jointMap) {
        //What to do here???
        int gridX = 323;
        int gridY = 108;

        int gridWidth = 120;
        int gridHeight = 120;

        int gridWidthSpace = 121;
        int gridHeightSpace = 121;
        for (int k = j-yAOE; k <= j+yAOE; k++){
            for (int l = i-xAOE; l <= i+xAOE; l++){
                if (jointMap.tileExists(l, k)){
                    g.setColor(Color.GREEN);
                    g.drawRect(gridX + gridWidthSpace * l, gridY + gridHeightSpace * k, gridWidth, gridHeight);
                }
            }
        }
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
                    if (friendTarget && jointMap.getTileType(i, j) == jointMap.getTileType(entitySource.getXGrid(),entitySource.getYGrid())) {
                        jointMap.indicate(i, j);
                    }
                    if (enemyTarget && jointMap.getTileType(i, j) != jointMap.getTileType(entitySource.getXGrid(),entitySource.getYGrid())) {
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
