import java.awt.*;

public class Player extends Entity{

    Icon ico;

    Player(double health, double energy, String name, Ability[] abilities){
        super(health,energy,name,abilities);
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
            //draw the abilities cyan if usable
            if (abilities[i].getEnergyCost() > getEnergy() || abilities[i].getCurrentCooldown() > 0) {
                g.setColor(new Color(255, 150, 200));
            } else{
                g.setColor(new Color(0, 200, 255));
            }
            g.fillRect(30, 30 + 90 * i, 263, 80);

            //Cooldown bar! make it nicer please
            g.setColor(new Color(0, 0, 0, 50));
            if (abilities[i].getCurrentCooldown() > 0) {
                g.fillRect(30, 30 + 90 * i, 263 / (abilities[i].getCooldown()-abilities[i].getCurrentCooldown()+1), 80);
            }

            g.setColor(Color.BLACK);
            g.drawString(abilities[i].getName(), 40, 50+90*i);
            g.drawString("Energy Cost: " + abilities[i].getEnergyCost(), 40, 70+90*i);
            g.drawString("Cooldown: " + abilities[i].getCooldown(), 40, 90+90*i);
            g.drawString("Turns until Usable: " + abilities[i].getCurrentCooldown(), 40, 110+90*i);
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
        if (getName().equals("yellow")){
            g.setColor(Color.yellow);
        } else if (getName().equals("cyan")){
            g.setColor(Color.CYAN);
        }
        g.fillRect(x,y,120,120);
        g.setColor(Color.BLACK);
        if (indicated){
            g.setColor(new Color(0, 0, 0, 100));
            g.fillRect(x,y,120,120);
        }
        g.drawRect(x, y, 120, 120);
    }

    public void endTurnLowerCooldown(){
        for (int i = 0; i < abilities.length; i++){
            abilities[i].lowerCooldown(1);
        }
    }

    public void drawIcons(Graphics g) {
        ico.draw(g);
    }
}
