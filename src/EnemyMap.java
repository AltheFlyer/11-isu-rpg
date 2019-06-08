import java.awt.*;

public class EnemyMap {
    EnemyTile[][] enemyArray = new EnemyTile[3][3];

    EnemyMap() {
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                enemyArray[i][j] = new EnemyTile(686+i*121, 108+j*121, i, j);
            }
        }
    }

    public void draw(Graphics g) {
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                enemyArray[i][j].draw(g);
            }
        }
    }

    public void target(int i, int j, double damage) {
        enemyArray[i][j].damageTile(damage);
    }

    public void addEnemy(int i, int j, Enemy enemy) {
        if (tileExists(i,j)) {
            enemyArray[i][j].setEnemy(enemy);
        }
    }

    public boolean tileExists(int i, int j) {
        return (i < 3) && (i >= 0) && (j < 3) && (j >= 0);
    }

    public void indicate(int x, int y){
        enemyArray[x][y].indicate();
    }

    public void unIndicate(int x, int y){
        enemyArray[x][y].unIndicate();
    }

    public void unIndicateAll(){
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                enemyArray[i][j].unIndicate();
            }
        }
    }

    public boolean getIndication(int x, int y){
        return enemyArray[x][y].getIndication();
    }

    public boolean isEmpty(int x, int y){
        return enemyArray[x][y].isEmpty();
    }
}
