import java.awt.*;

public class EnemyTile extends Tile{
    private Enemy enemy;

    EnemyTile(int x, int y){
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
            g.setColor(Color.RED);
            g.fillRect(getX(), getY(), 120, 120);
            g.setColor(Color.BLACK);
            g.drawRect(getX(), getY(), 120, 120);
        } else {
            enemy.draw(getX(), getY(),g);
        }
    }

    //There is also damageEntity here!
}
