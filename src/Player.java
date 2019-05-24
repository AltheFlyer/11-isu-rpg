import java.awt.*;

public class Player extends Entity{
    Player(double health, String name, Ability ability1){
        super(health,name,ability1);
    }

    public Ability getAbility1(){
        return ability1;
    }

    public void drawAbilities(Graphics g, boolean selected){
        g.setColor(Color.GRAY);
        if (getName().equals("allen")){
            g.setColor(Color.LIGHT_GRAY);
        }
        g.fillRect(0,0,323,768);
        g.setColor(Color.BLACK);
        g.drawRect(0,0,323,768);
        if (getName().equals("allen")){
            g.drawString("HEAL", 10, 50);
        } else {
            g.drawString("ATTACK", 10, 50);
        }
        if (selected) {
            g.drawRect(10, 40, 60, 15);
        }
    }

    public void draw(int x, int y, Graphics g, boolean indicated){
        g.setColor(Color.MAGENTA);
        if (getName().equals("allen")){
            g.setColor(Color.PINK);
        }
        g.fillRect(x,y,120,120);
        g.setColor(Color.BLACK);
        if (indicated){
            g.setColor(Color.GRAY);
        }
        g.drawRect(x, y, 120, 120);
    }
}
