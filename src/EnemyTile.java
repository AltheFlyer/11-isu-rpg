import utils.TextDrawer;

import java.awt.*;

public class EnemyTile extends Tile{
    private Enemy enemy;

    EnemyTile(int xGraphic, int yGraphic, int xGrid, int yGrid){
        super(xGraphic,yGraphic, xGrid, yGrid);
    }

    public void setEnemy(Enemy enemy){
        this.enemy = enemy;
        enemy.setXGrid(getXGrid());
        enemy.setYGrid(getYGrid());
        setEntity(enemy);
    }

    public void nullEnemy(){
        enemy = null;
        nullEntity();
    }

    public Enemy getEnemy(){
        return enemy;
    }

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
