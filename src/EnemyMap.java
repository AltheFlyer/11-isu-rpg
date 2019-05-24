import java.awt.*;

public class EnemyMap {
    EnemyTile[][] enemyArray = new EnemyTile[3][3];

    EnemyMap() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                enemyArray[i][j] = new EnemyTile(686+j*121, 108+i*121, j, i);
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
        if (i < 3 && i >= 0 && j < 3 && j >= 0) {
            return true;
        }
        return false;
    }

    public void indicate(int x, int y){
        enemyArray[y][x].indicate();
    }

    public void unIndicate(int x, int y){
        enemyArray[y][x].unIndicate();
    }

    public void indicateAll(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++){
                enemyArray[i][j].indicate();
            }
        }
    }

    public void unIndicateAll(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++){
                enemyArray[i][j].unIndicate();
            }
        }
    }

    public boolean getIndication(int x, int y){
        return enemyArray[y][x].getIndication();
    }

    public boolean isEmpty(int x, int y){
        return enemyArray[y][x].isEmpty();
    }
}
