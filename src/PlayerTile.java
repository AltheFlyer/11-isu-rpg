import java.awt.*;

public class PlayerTile extends Tile{
    private Player player;

    PlayerTile(int xGraphic, int yGraphic, int xGrid, int yGrid){
        super(xGraphic,yGraphic, xGrid, yGrid);
    }

    public void setPlayer(Player player){
        this.player = player;
        player.setXGrid(getXGrid());
        player.setYGrid(getYGrid());
        setEntity(player);
    }

    public Player getPlayer(){
        return player;
    }

    public void draw(Graphics g){
        if (isEmpty()) {
            g.setColor(new Color(204, 255, 255));
            g.fillRect(getXGraphic(), getYGraphic(), 120, 120);
            g.setColor(Color.BLACK);
            if (getIndication()){
                g.setColor(new Color(0, 0, 0, 100));
                g.fillRect(getXGraphic(), getYGraphic(),120,120);
            }
            if (getTargetable()){
                g.setColor(new Color(0, 0, 255));
                g.drawRect(getXGraphic(), getYGraphic(),120,120);
            }
            g.drawRect(getXGraphic(), getYGraphic(), 120, 120);
        } else {
            player.draw(getXGraphic(), getYGraphic(),g, getIndication());

            //Draw blue border around if it is a targetable space
            if (getTargetable()){
                g.setColor(new Color(0, 0, 255));
                g.drawRect(getXGraphic(), getYGraphic(),120,120);
            }
        }
    }

    /**
     * RETURNS THE TYPE OF TILE IT IS, 'p' for Player 'e' for Enemy
     */

    public char getType(){
        return 'p';
    }

    //There is also damageEntity here!
}
