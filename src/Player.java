import utils.TextDrawer;

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
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0,0,323,768);
        g.setColor(Color.BLACK);
        g.drawRect(0,0,323,768);

        //Draw the player profile at the bottom so you know!
        g.setColor(Color.MAGENTA);
        if (getName().equals("yellow")){
            g.setColor(Color.yellow);
        } else if (getName().equals("cyan")){
            g.setColor(Color.CYAN);
        }
        g.fillRect(30,15+105*abilities.length,60,60);
        drawHealthBar(g);
        drawEnergyBar(g);

        //ABILITY ICONS
        for (int i = 0; i < abilities.length; i++) {
            //draw the abilities cyan if usable
            if (abilities[i].getEnergyCost() > getEnergy() || abilities[i].getCurrentCooldown() > 0) {
                g.setColor(new Color(255, 150, 200));
            } else{
                g.setColor(new Color(0, 200, 255));
            }
            g.fillRect(30, 15 + 105 * i, 263, 100);

            //Cooldown bar!
            g.setColor(new Color(0, 0, 0, 50));
            if (abilities[i].getCurrentCooldown() > 0) {
                g.fillRect(30, 15 + 105 * i, 264/abilities[i].getCooldown()*(abilities[i].getCurrentCooldown()), 100);
            }

            g.setColor(Color.BLACK);
            //Drawing the name of the ability and a box around it
            g.drawString(abilities[i].getName(), 40, 32+105*i);
            g.drawRect(30,15+105*i,100,22);

            //Drawing the energy cost of an ability and a box around it
            g.drawString("Energy Cost: " + abilities[i].getEnergyCost(), 140, 32+105*i);
            g.drawRect(130,15+105*i,163,22);

            //Drawing the cooldown of an ability and a box around it
            g.drawString("Cooldown: " + abilities[i].getCooldown(), 40, 54+105*i);
            g.drawRect(30,37+105*i,100,22);

            //Drawing the damage for an ability
            g.drawString("Damage: " + abilities[i].getDamage(), 140, 54+105*i);
            g.drawRect(130,37+105*i,163,22);

            //Drawing the description
            TextDrawer drawer = new TextDrawer(g,abilities[i].getDesc(), 40, 76+105*i,250);
            drawer.drawText(g);
        }

        for (int i = 0; i < abilities.length; i++){
            if (ability != null) {
                if (ability.equals(abilities[i])) {
                    g.setColor(new Color(0, 0, 0, 100));
                    g.fillRect(30, 15 + 105 * i, 263, 100);
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

    public void drawHealthBar(Graphics g){
        //Drawing a health bar here to make it nicer
        double ratio = getHealth() / getMaxHealth();
        //Grey backing bar
        g.setColor(Color.GRAY);
        g.fillRect(100, 33+105*abilities.length,190, 12);

        //Set healthbar color based on if friendly or not
        g.setColor(Color.GREEN);

        //Draw at bottom of screen, -10 is for the height of the bar
        g.fillRect(100, 33+105*abilities.length, (int) (190 * ratio), 12);
        g.setColor(Color.BLACK);
        g.drawRect(100, 33+105*abilities.length,190, 12);
        g.drawString(getHealth() + "/" + getMaxHealth(), 130, 44+105*abilities.length);
    }

    public void drawEnergyBar(Graphics g){
        double ratio = getEnergy() / getMaxEnergy();
        g.setColor(Color.GRAY);
        g.fillRect(100, 45+105*abilities.length,190, 12);

        //Set healthbar color based on if friendly or not
        g.setColor(new Color(0,200,255));

        //Draw at bottom of screen, -10 is for the height of the bar
        g.fillRect(100, 45+105*abilities.length, (int) (190 * ratio), 12);
        g.setColor(Color.BLACK);
        g.drawRect(100, 45+105*abilities.length,190, 12);
        g.drawString(getEnergy() + "/" + getMaxEnergy(), 130, 56+105*abilities.length);

    }
}
