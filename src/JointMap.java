
import java.awt.*;

public class JointMap {
    //private PlayerTile[][] playerArray = new PlayerTile[3][3];
    private Tile[][] tileArray = new Tile[6][3];

    JointMap() {
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                tileArray[i][j] = new PlayerTile(323 + i * 121, 108 + j * 121, i, j);
            }
            for (int i = 3; i < 6; i++) {
                tileArray[i][j] = new EnemyTile(323 + i * 121, 108 + j * 121, i, j);
            }
        }
    }

    public void draw(Graphics g) {
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 6; i++) {
                tileArray[i][j].draw(g);
            }
        }
    }

    //DO WE KEEP TARGET OR MOVE IT TO ATTACKS? DECIDE LATER
    public void target(int i, int j, double damage, int status) {
        tileArray[i][j].damageTile(damage, status);
    }

    //Setting entities onto the grid
    public void addEntity(int i, int j, Entity entity) {
        if (getTileType(i,j) == 'p'){
            ((PlayerTile)tileArray[i][j]).setPlayer((Player)entity);
        } else if (getTileType(i,j) == 'e') {
            ((EnemyTile)tileArray[i][j]).setEnemy((Enemy)entity);
        }
    }

    public boolean tileExists(int x, int y) {
        return (x < 6) && (x >= 0) && (y < 3) && (y >= 0);
    }

    public void indicate(int x, int y){
        tileArray[x][y].indicate();
    }

    public void unIndicate(int x, int y){
        tileArray[x][y].unIndicate();
    }

    public void unIndicateAll(){
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 6; i++) {
                tileArray[i][j].unIndicate();
            }
        }
    }

    public boolean getIndication(int x, int y){
        return tileArray[x][y].getIndication();
    }

    public boolean isEmpty(int x, int y){
        return tileArray[x][y].isEmpty();
    }

    /*
    public void swapTiles(int x, int y, int xNow, int yNow){
        playerArray[x][y].setPlayer(playerArray[yNow][xNow].getPlayer());
        playerArray[xNow][yNow].setPlayer(null);
    }
    */

    /**
     * getTileType will return a char based on if a tile is a playerTile or an enemyTile
     * @param x
     * @param y
     * @return returns 'p' for playerTile and 'e' for enemyTile
     */

    public char getTileType(int x, int y){
        return tileArray[x][y].getType();
    }



    public void drawIcons(Graphics g) {
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 6; i++) {
                tileArray[i][j].drawIcons(g);
            }
        }
    }
}