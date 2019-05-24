import java.awt.*;

public class Enemy extends Entity{
    Enemy(double health){
        super(health);
    }

    public void drawAbilities(Graphics g){
        g.setColor(Color.GRAY);
        g.fillRect(0,0,120,120);
    }

    public void draw(int x, int y, Graphics g){
        g.setColor(Color.ORANGE);
        g.fillRect(x,y,120,120);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, 120, 120);
    }
}
