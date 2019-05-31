import utils.TextDrawer;

import java.awt.*;

public class Enemy extends Entity{

    private Icon testIntent;
    private Ability decide;

    Enemy(double health, String name, Ability[] abilities){
        super(health,name,abilities);
        for (int i = 0; i < abilities.length; i++){
            abilities[i].setEntitySource(this);
        }
        //The first ability is always the one that will be decided to be used first
        decide = abilities[0];
        testIntent = new Icon(new Rectangle(0, 0, 40, 40), "assets/icons/test.png");
        testIntent.setName("Special");
        testIntent.setDescription("A *really* powerful attack. I need more text to test newline drawing.\n\n\n\n\n\nI hope this works");
    }

    public Ability getAbility(int index){
        return abilities[index];
    }

    public void drawAbilities(Graphics g){
        int xOffset = 1043; //Offset from left side of screen

        //BACKGROUND
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(xOffset,0,323,768);
        g.setColor(Color.BLACK);
        g.drawRect(xOffset,0,323,768);

        //Draw the enemy as an image maybe

        //ABILITY ICONS
        for (int i = 0; i < abilities.length; i++) {
            //draw the abilities cyan if usable
            if (abilities[i].getEnergyCost() > getEnergy() || abilities[i].getCurrentCooldown() > 0) {
                g.setColor(new Color(255, 150, 200));
            } else{
                g.setColor(new Color(0, 200, 255));
            }
            g.fillRect(xOffset + 30, 15 + 105 * i, 263, 100);

            //Cooldown bar!
            g.setColor(new Color(0, 0, 0, 50));
            if (abilities[i].getCurrentCooldown() > 0) {
                g.fillRect(xOffset + 30, 15 + 105 * i, 264/abilities[i].getCooldown()*(abilities[i].getCurrentCooldown()), 100);
            }

            g.setColor(Color.BLACK);
            //Drawing the name of the ability and a box around it
            g.drawString(abilities[i].getName(), xOffset + 40, 32+105*i);
            g.drawRect(xOffset + 30,15+105*i,100,22);

            //Drawing the energy cost of an ability and a box around it
            g.drawString("Energy Cost: " + abilities[i].getEnergyCost(), xOffset + 140, 32+105*i);
            g.drawRect(xOffset + 130,15+105*i,163,22);

            //Drawing the cooldown of an ability and a box around it
            g.drawString("Cooldown: " + abilities[i].getCooldown(), xOffset + 40, 54+105*i);
            g.drawRect(xOffset + 30,37+105*i,100,22);

            //Drawing the damage for an ability
            g.drawString("Damage: " + abilities[i].getDamage(), xOffset + 140, 54+105*i);
            g.drawRect(xOffset + 130,37+105*i,163,22);

            //Drawing the description
            TextDrawer drawer = new TextDrawer(g,abilities[i].getDesc(), xOffset + 40, 76+105*i,250);
            drawer.drawText(g);
        }
    }

    public void draw(int x, int y, Graphics g, boolean indicated){
        g.setColor(Color.ORANGE);
        g.fillRect(x,y,120,120);
        g.setColor(Color.BLACK);
        if (indicated){
            g.setColor(new Color(0, 0, 0, 100));
            g.fillRect(x,y,120,120);
        }
        g.drawRect(x, y, 120, 120);
    }

    /**
     * [decide]
     * contrary to the method name, this doesn't cause enemies to decide, rather it
     * generates a next move (Ability and intent) (should be overriden)
     */
    public void decide(JointMap map) {
        //For testing/theory purposes only...
        if (this.getHealth() < this.getMaxHealth() / 2) {
            //A heal on itself
            testIntent = new Icon("assets/icons/medic.png");
            testIntent.setName("Medic");
            testIntent.setDescription("This enemy intends to restore health to itself.");
            //I don't know if there are multiple attacks yet so...
            decide = abilities[1];
        } else {
            testIntent = new Icon("assets/icons/sword.png");
            testIntent.setName("Attack");
            testIntent.setDescription("A basic attack that will deal damage to a player.");
            decide = abilities[0];
        }

    }

    public Icon getIntent() {
        return testIntent;
    }

    public Ability getDecide() {
        return decide;
    }



}
