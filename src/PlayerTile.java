import java.awt.Graphics;
import java.awt.Color;

/**
 * [PlayerTile.java]
 * the tiles that players will be situated on
 * @version 1.0
 * @author Kevin Liu
 * @since May 23, 2019
 */
public class PlayerTile extends Tile{
    private Player player;

    /**
     * Constructor for creating a PlayerTile
     * @param xGraphic the x location in pixels, used to draw graphics
     * @param yGraphic the y location in pixels, used to draw graphics
     * @param xGrid the x location on the battle grid, used for attack ranges and calculations
     * @param yGrid the y location on the battle grid, used for attack ranges and calculations
     */
    PlayerTile(int xGraphic, int yGraphic, int xGrid, int yGrid){
        super(xGraphic,yGraphic, xGrid, yGrid);
    }

    /**
     * [setPlayer]
     * sets a player in this PlayerTile's location
     * @param player the player that is in the current tile
     */
    public void setPlayer(Player player){
        this.player = player;
        player.setXGrid(getXGrid());
        player.setYGrid(getYGrid());
        setEntity(player);
    }

    /**
     * [nullPlayer]
     * removes the player at the current location, due to move or death
     */
    public void nullPlayer(){
        player = null;
        nullEntity();
    }

    /**
     * [getPlayer]
     * gets a player in this PlayerTile's location
     * @return player, the player that is in the current tile
     */
    public Player getPlayer(){
        return player;
    }

    /**
     * [draw]
     * draw the information present on the PlayerTile, it is blue and will draw differently if it is indicated or targetable
     * @param g the graphics used to draw
     */
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
     * [isTileFriendly]
     * checks if the tile is friendly or not
     * @return boolean, true for all player tiles
     */
    @Override
    public boolean isTileFriendly(){
        return true;
    }

    //There is also damageEntity here!
}
