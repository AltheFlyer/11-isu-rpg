import java.awt.*;

public class Player extends Entity{

    Icon ico;

    Player(double health, String name, Ability[] abilities){
        super(health,name,abilities);
        for (int i = 0; i < abilities.length; i++){
            abilities[i].setEntitySource(this);
        }
        ico = new Icon(new Rectangle(0, 0, 40, 40), "assets/icons/test.png");
    }

    public Ability getAbility(int index){
        return abilities[index];
    }

    //pass list of abilities into levelScreen, select based on rect hovering
    public void drawAbilities(Graphics g, Ability ability){
        //BACKGROUND
        g.setColor(Color.GRAY);
        if (getName().equals("allen")){
            g.setColor(Color.LIGHT_GRAY);
        } else if (getName().equals("bryan")){
            g.setColor(Color.BLACK);
        }
        g.fillRect(0,0,323,768);
        g.setColor(Color.BLACK);
        g.drawRect(0,0,323,768);

        //ABILITY ICONS

        for (int i = 0; i < abilities.length; i++) {
            g.setColor(Color.CYAN);
            g.fillRect(30,30+90*i,263,80);
            g.setColor(Color.BLACK);
            g.drawString(abilities[i].getName(), 40, 50+90*i);
            g.drawRect(30,30+90*i,263,80);
        }

        for (int i = 0; i < abilities.length; i++){
            if (ability != null) {
                if (ability.getName().equals(abilities[i].getName())) {
                    g.setColor(new Color(0, 0, 0, 100));
                    g.fillRect(30, 30 + 90 * i, 263, 80);
                }
            }
        }
    }

    public void draw(int x, int y, Graphics g, boolean indicated){
        g.setColor(Color.MAGENTA);
        if (getName().equals("allen")){
            g.setColor(Color.yellow);
        } else if (getName().equals("bryan")){
            g.setColor(Color.GREEN);
        }
        g.fillRect(x,y,120,120);
        g.setColor(Color.BLACK);
        if (indicated){
            g.setColor(new Color(0, 0, 0, 100));
            g.fillRect(x,y,120,120);
        }
        g.drawRect(x, y, 120, 120);
    }

    public void drawIcons(Graphics g) {
        ico.draw(g);
    }
}
