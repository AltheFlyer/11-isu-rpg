public class EnemyTile extends Tile {

    Enemy enemy;

    public EnemyTile() {
        super();
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
        setEntity(enemy);
    }

    public Enemy getEnemy() {
        return this.enemy;
    }
}
