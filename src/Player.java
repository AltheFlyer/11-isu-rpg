import java.awt.*;

public class Player extends Entity{
    Player(double health, String name){
        super(health,name);
    }

    public void drawAbilities(Graphics g){
        g.setColor(Color.GRAY);
        g.fillRect(0,0,323,768);
        g.setColor(Color.BLACK);
        g.drawRect(0,0,323,768);
        g.drawString("ATTACK", 10, 50);
    }

    public void draw(int x, int y, Graphics g){
        g.setColor(Color.MAGENTA);
        g.fillRect(x,y,120,120);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, 120, 120);
    }
}
