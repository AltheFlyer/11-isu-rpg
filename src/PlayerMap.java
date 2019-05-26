import java.awt.*;

public class PlayerMap {
    private PlayerTile[][] playerArray = new PlayerTile[3][3];

    PlayerMap() {
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                playerArray[i][j] = new PlayerTile(323 + i * 121, 108 + j * 121, i, j);
            }
        }
    }

    public void draw(Graphics g) {
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                playerArray[i][j].draw(g);
            }
        }
    }

    public void target(int i, int j, double damage, int status) {
        playerArray[i][j].damageTile(damage, status);
    }

    public void addPlayer(int i, int j, Player player) {
        if (tileExists(i,j)) {
            playerArray[i][j].setPlayer(player);
        }
    }

    public boolean tileExists(int i, int j) {
        return (i < 3) && (i >= 0) && (j < 3) && (j >= 0);
    }

    /*
    public Player findPlayer(Player player){
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                if (!playerArray[i][j].isEmpty()){
                    if (playerArray[i][j].getPlayer().getName().equals(player.getName())) {
                        return playerArray[i][j].getPlayer();
                    }
                }
            }
        }
        return null;
    }
    */

    public int findPlayerX(Player player){
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                if (!playerArray[i][j].isEmpty()){
                    if (playerArray[i][j].getPlayer().getName().equals(player.getName())) {
                        return i;
                    }
                }
            }
        }
        return 0;
    }

    public int findPlayerY(Player player){
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                if (!playerArray[i][j].isEmpty()){
                    if (playerArray[i][j].getPlayer().getName().equals(player.getName())) {
                        return j;
                    }
                }
            }
        }
        return 0;
    }

    public void indicate(int x, int y){
        playerArray[x][y].indicate();
    }

    public void unIndicate(int x, int y){
        playerArray[x][y].unIndicate();
    }

    public void unIndicateAll(){
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                playerArray[i][j].unIndicate();
            }
        }
    }

    public boolean getIndication(int x, int y){
        return playerArray[x][y].getIndication();
    }

    public boolean isEmpty(int x, int y){
        return playerArray[x][y].isEmpty();
    }

    public void swapTiles(int x, int y, int xNow, int yNow){
        playerArray[x][y].setPlayer(playerArray[yNow][xNow].getPlayer());
        playerArray[xNow][yNow].setPlayer(null);
    }
}
