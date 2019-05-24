import java.awt.*;

public class PlayerMap {
    private PlayerTile[][] playerArray = new PlayerTile[3][3];

    PlayerMap() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                playerArray[i][j] = new PlayerTile(323 + j * 120, 108 + i * 120);
            }
        }
    }

    public void draw(Graphics g) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                playerArray[i][j].draw(g);
            }
        }
    }

    public void target(int i, int j, int damage, int status) {
        playerArray[i][j].damageTile(damage, status);
    }

    public void addPlayer(int i, int j, Player player) {
        if (tileExists(i,j)) {
            playerArray[i][j].setPlayer(player);
        }
    }

    public boolean tileExists(int i, int j) {
        if (i < 3 && i >= 0 && j < 3 && j >= 0){
            return true;
        }
        return false;
    }

    public Player findPlayer(Player player){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!playerArray[i][j].isEmpty()){
                    if (playerArray[i][j].getPlayer().getName().equals(player.getName())) {
                        return playerArray[i][j].getPlayer();
                    }
                }
            }
        }
        return null;
    }
}
