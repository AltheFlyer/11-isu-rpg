import java.awt.*;

public class PlayerTile extends Tile{
    private Player player;

    PlayerTile(int x, int y, int xTile, int yTile){
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
            g.setColor(new Color(204, 255, 255));
            g.fillRect(getX(), getY(), 120, 120);
            g.setColor(Color.BLACK);
            if (getIndication()){
                g.setColor(Color.GRAY);
            }
            g.drawRect(getX(), getY(), 120, 120);
        } else {
            player.draw(getX(), getY(),g, getIndication());
        }
    }

    //There is also damageEntity here!
}
