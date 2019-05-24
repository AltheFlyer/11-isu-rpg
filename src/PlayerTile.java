import java.awt.*;

public class PlayerTile extends Tile{
    private Player player;

    PlayerTile(int x, int y){
        super(x,y);
    }

    public void setPlayer(Player player){
        this.player = player;
        setEntity(player);
    }

    public Player getPlayer(){
        return player;
    }

    public void draw(Graphics g){
        if (isEmpty()) {
            g.setColor(Color.BLUE);
            g.fillRect(getX(), getY(), 120, 120);
            g.setColor(Color.BLACK);
            g.drawRect(getX(), getY(), 120, 120);
        } else {
            player.draw(getX(), getY(),g);
        }
    }

    //There is also damageEntity here!
}
