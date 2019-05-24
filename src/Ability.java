abstract public class Ability {
    private String name;
    private String desc;
    private int xRange;
    private int yRange;
    private int status;
    private double damage;
    private int cost;

    private Tile[][] getValidTiles;

    Ability(String name, int xRange, int yRange, int status, double damage) {
        this.name = name;
        this.xRange = xRange;
        this.yRange = yRange;
        this.status = status;
        this.damage = damage;

    }

    public void indicate() {

    }

    public void actEnemy(EnemyMap enemyMap, int x, int y) {
        enemyMap.target(y,x,damage,status);
    }

    public void actPlayer(PlayerMap playerMap, int x, int y) {
        playerMap.target(y,x,damage,status);
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
}
