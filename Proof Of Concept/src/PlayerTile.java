public class PlayerTile extends Tile {

    Player player;

    public PlayerTile() {
        super();
    }

    public void setPlayer(Player player) {
        this.player = player;
        setEntity(player);
    }

    public Player getPlayer() {
        return this.player;
    }
}
