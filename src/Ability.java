import java.awt.*;

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

    public void actEnemy(EnemyMap enemyMap, int x, int y) {
        enemyMap.target(x, y, damage, status);
    }

    public void actPlayer(PlayerMap playerMap, int x, int y) {
        playerMap.target(x, y, damage, status);
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
     * [drawValidTargets]
     * Draws valid selection regions for the center of an ability
     * @param g the graphics object to draw with
     */
    public void drawValidTargets(Graphics g) {}

    }


    public int getMoves() {
        return moves;
    }
}
