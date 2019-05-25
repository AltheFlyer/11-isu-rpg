import java.awt.*;

public class EnemyTile extends Tile{
    private Enemy enemy;

    EnemyTile(int x, int y, int xTile, int yTile){
        super(x,y);
    }

    public void setEnemy(Enemy enemy){
        this.enemy = enemy;
        setEntity(enemy);
    }

    public Enemy getEnemy(){
        return enemy;
    }

    public void draw(Graphics g){
        if (isEmpty()) {
            g.setColor(new Color(255, 204, 204));
            g.fillRect(getX(), getY(), 120, 120);
            g.setColor(Color.BLACK);
            if (getIndication()){
                g.setColor(Color.GRAY);
            }
            g.drawRect(getX(), getY(), 120, 120);
        } else {
            enemy.draw(getX(), getY(),g, getIndication());
        }
    }

    //There is also damageEntity here!
}
