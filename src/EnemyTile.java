import utils.TextDrawer;

import java.awt.Graphics;
import java.awt.Color;

/**
 * [EnemyTile.java]
 * the tiles that enemies will be situated on
 * @version 1.0
 * @author Kevin Liu
 * @since May 23, 2019
 */
public class EnemyTile extends Tile{
    private Enemy enemy;

    /**
     * Constructor for creating a EnemyTile
     * @param xGraphic the x location in pixels, used to draw graphics
     * @param yGraphic the y location in pixels, used to draw graphics
     * @param xGrid the x location on the battle grid, used for attack ranges and calculations
     * @param yGrid the y location on the battle grid, used for attack ranges and calculations
     */
    EnemyTile(int xGraphic, int yGraphic, int xGrid, int yGrid){
        super(xGraphic,yGraphic, xGrid, yGrid);
    }

    /**
     * [setEnemy]
     * sets a enemy in this EnemyTile's location
     * @param enemy the enemy that is in the current tile
     */
    public void setEnemy(Enemy enemy){
        this.enemy = enemy;
        enemy.setXGrid(getXGrid());
        enemy.setYGrid(getYGrid());
        setEntity(enemy);
    }

    /**
     * [nullPlayer]
     * removes the enemy at the current location, due to move or death
     */
    public void nullEnemy(){
        enemy = null;
        nullEntity();
    }

    /**
     * [getEnemy]
     * gets a enemy in this EnemyTile's location
     * @return enemy, the enemy that is in the current tile
     */
    public Enemy getEnemy(){
        return enemy;
    }

    /**
     * [draw]
     * draw the information present on the EnemyTile, it is red and will draw differently if it is indicated or targetable
     * @param g the graphics used to draw
     */
    public void draw(Graphics g){
        if (isEmpty()) {
            g.setColor(new Color(255, 204, 204));
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
            enemy.draw(getXGraphic(), getYGraphic() ,g, getIndication());

            //Draw blue square around if it is a targetable space
            if (getTargetable()){
                g.setColor(new Color(0, 0, 255));
                g.drawRect(getXGraphic(), getYGraphic(),120,120);
            }
        }
    }

    /**
     * [isTileFriendly]
     * checks if the tile is friendly or not
     * @return boolean, false for all enemy tiles
     */
    @Override
    public boolean isTileFriendly(){
        return false;
    }

    //There is also damageEntity here!


    /**
     * [drawIcons]
     * draws icons for the enemy tile, used to show intents
     * @param g the graphics object to draw with
     */
    @Override
    public void drawIcons(Graphics g) {
        drawStatus(g);

        int tileDimension = 120;

        if (!isEmpty()) {
            Icon intent = enemy.getIntent();
            intent.setPosition(getXGraphic() + (tileDimension / 2), getYGraphic() - (tileDimension / 3));
            intent.draw(g);
        }
    }

    /**
     * [drawIconText]
     * draws the text on the icons when hovered over (read their intent)
     * @param g the graphics object to draw with
     * @param mouseX the x position of the mouse
     * @param mouseY the y position of the mouse
     */
    public void drawIconText(Graphics g, int mouseX, int mouseY) {
        super.drawIconText(g, mouseX, mouseY);

        if (!isEmpty()) {
            Icon intent = enemy.getIntent();
            if (intent.getBoundingBox().contains(mouseX, mouseY)) {
                intent.drawText(g, 25, 0, 150);
            }
        }
    }
}
