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

    public void act(EnemyMap enemyMap, int x, int y) {
        enemyMap.target(x,y,damage,status);
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
