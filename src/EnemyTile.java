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
    public void drawIcons(Graphics g) {
        if (!isEmpty()) {
            Icon intent = enemy.getIntent();
            intent.setPosition(getXGraphic() + 60, getYGraphic() - 40);
            intent.draw(g);
        }
    }
}
