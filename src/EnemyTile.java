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

    @Override
    public void drawIcons(Graphics g, int mouseX, int mouseY) {
        int tileDimension = 120;
        int textBackwidth = 150;

        if (!isEmpty()) {
            Icon intent = enemy.getIntent();
            intent.setPosition(getXGraphic() + (tileDimension / 2), getYGraphic() - (tileDimension / 3));
            intent.draw(g);
            //If the mouse is hovering over the icon, draw text
            if (intent.getBoundingBox().contains(mouseX, mouseY)) {
                //Create a text drawer that will draw the intent name and description.
                TextDrawer t = new TextDrawer(g, intent.getName() + "\n\n" + intent.getDescription(), getXGraphic() + (tileDimension / 2) + 10, getYGraphic() - (tileDimension / 3), 130);

                //Grey background box for contrast
                g.setColor(new Color(50, 50, 50, 200));
                g.fillRect(getXGraphic() + (tileDimension / 2),
                        getYGraphic() - (tileDimension / 2),
                        textBackwidth,
                        t.getTotalHeight() + 20);

                //Draw text
                g.setColor(Color.WHITE);
                t.drawText(g);
            }
        }
    }
}
