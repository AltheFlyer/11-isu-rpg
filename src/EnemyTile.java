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
    * RETURNS THE TYPE OF TILE IT IS, 'p' for Player 'e' for Enemy
    */

    public char getType(){
        return 'e';
    }

    //There is also damageEntity here!

    /**
     * [drawIcons]
     * draws icons for the enemy tile, used to show intents
     * @param g the graphics object to draw with
     * @param mouseX the mouse x coordinate
     * @param mouseY the mouse y coordinate
     */
    @Override
    public void drawIcons(Graphics g, int mouseX, int mouseY) {
        int tileDimension = 120;
        int textBackwidth = 150;

        int boxOffset = 25; //Icons tend to be 40 pixels wide
        //Space between text box edge and text
        int padding = 10;

        if (!isEmpty()) {
            Icon intent = enemy.getIntent();
            intent.setPosition(getXGraphic() + (tileDimension / 2), getYGraphic() - (tileDimension / 3));
            intent.draw(g);

            //If the mouse is hovering over the icon, draw text
            if (intent.getBoundingBox().contains(mouseX, mouseY)) {
                //Create a text drawer that will draw the intent name and description.
                //In order: draw at halfway point in tile, then offset by fixed constant plus padding
                //Draw a third of a tile's height above the current one
                //Set text width to width of textbox minus left and right side padding
                TextDrawer t = new TextDrawer(g,
                        intent.getName() + "\n\n" + intent.getDescription(),
                        getXGraphic() + (tileDimension / 2) + padding + boxOffset,
                        getYGraphic() - (tileDimension / 3),
                        textBackwidth - (2 * padding));

                //Grey background box for contrast
                g.setColor(new Color(50, 50, 50, 200));
                //In order: draw at halfway point in tile, then offset by fixed constant
                //Draw half a tile's height above the current one plus the text's line height and padding
                //Fixed constant width, dynamic height based on text height and padding
                g.fillRect(getXGraphic() + (tileDimension / 2) + boxOffset,
                        getYGraphic() - (tileDimension / 3) - t.getLineHeight() - padding,
                        textBackwidth,
                        t.getTotalHeight() + (padding * 2));

                //Draw text
                g.setColor(Color.WHITE);
                t.drawText(g);
            }
        }
    }
}
