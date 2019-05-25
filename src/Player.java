import java.awt.*;

public class Player extends Entity{
    Player(double health, String name, Ability ability1){
        super(health,name,ability1);
    }

    public Ability getAbility1(){
        return ability1;
    }

    public void drawAbilities(Graphics g, boolean selected){
        //BACKGROUND
        g.setColor(Color.GRAY);
        if (getName().equals("allen")){
            g.setColor(Color.LIGHT_GRAY);
        }
        g.fillRect(0,0,323,768);
        g.setColor(Color.BLACK);
        g.drawRect(0,0,323,768);

        //ABILITY ICONS

        for (int i = 0; i < 4; i++) {
            g.setColor(Color.CYAN);
            g.fillRect(30,30+90*i,263,80);
            g.setColor(Color.BLACK);
            g.drawRect(30,30+90*i,263,80);
        }

        g.drawString(ability1.getName(), 40, 50);

        if (selected) {
            g.setColor(new Color(0,0,0,100));
            g.fillRect(30, 30, 263, 80);
        }
    }

    public void draw(int x, int y, Graphics g, boolean indicated){
        g.setColor(Color.MAGENTA);
        if (getName().equals("allen")){
            g.setColor(Color.yellow);
        }
        g.fillRect(x,y,120,120);
        g.setColor(Color.BLACK);
        if (indicated){
            g.setColor(new Color(0, 0, 0, 100));
            g.fillRect(x,y,120,120);
        }
        g.drawRect(x, y, 120, 120);
    }
}
