import java.awt.*;

public class EnemyMap {
    EnemyTile[][] enemyArray = new EnemyTile[3][3];

    EnemyMap() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                enemyArray[i][j] = new EnemyTile(683+j*120, 108+i*120);
            }
        }
    }

    public void draw(Graphics g) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                enemyArray[i][j].draw(g);
            }
        }
    }

    public void target(int i, int j, double damage, int status) {
        enemyArray[i][j].damageTile(damage, status);
    }

    public void addEnemy(int i, int j, Enemy enemy) {
        if (tileExists(i,j)) {
            enemyArray[i][j].setEnemy(enemy);
        }
    }

    public boolean tileExists(int i, int j) {
        if (i < 3 && i >= 0 && j < 3 && j >= 0){
            return true;
        }
        return false;
    }
}
