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
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(1049,0,317,768);
        g.setColor(Color.BLACK);
        g.drawRect(1049,0,317,768);

        //Draw the player profile at the bottom so you know!
        g.setColor(Color.ORANGE);
        g.fillRect(1069,15+105*abilities.length,60,60);
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
            g.fillRect(1069, 15 + 105 * i, 264, 100);

            //Cooldown bar!
            g.setColor(new Color(0, 0, 0, 50));
            if (abilities[i].getCurrentCooldown() > 0) {
                g.fillRect(1069, 15 + 105 * i, 264/abilities[i].getCooldown()*(abilities[i].getCurrentCooldown()), 100);
            }

            g.setColor(Color.BLACK);
            //Drawing the name of the ability and a box around it

            g.drawString(abilities[i].getName(), 1079, 32+105*i);
            g.drawRect(1069,15+105*i,100,22);

            //Drawing the damage for an ability
            g.drawString("Damage: " + abilities[i].getDamage(), 1179, 32+105*i);
            g.drawRect(1169,15+105*i,163,22);

            //Drawing the description
            TextDrawer drawer = new TextDrawer(g,abilities[i].getDesc(), 1079, 54+105*i,250);
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

    public void drawHealthBar(Graphics g){
        //Drawing a health bar here to make it nicer
        double ratio = getHealth() / getMaxHealth();
        //Grey backing bar
        g.setColor(Color.GRAY);
        g.fillRect(1139, 33+105*abilities.length,190, 12);

        //Set healthbar color based on if friendly or not
        g.setColor(Color.GREEN);

        //Draw at bottom of screen, -10 is for the height of the bar
        g.fillRect(1139, 33+105*abilities.length, (int) (190 * ratio), 12);
        g.setColor(Color.BLACK);
        g.drawRect(1139, 33+105*abilities.length,190, 12);
        g.drawString(getHealth() + "/" + getMaxHealth(), 1169, 44+105*abilities.length);
    }

    public void drawEnergyBar(Graphics g){
        double ratio = getEnergy() / getMaxEnergy();
        g.setColor(Color.GRAY);
        g.fillRect(1139, 45+105*abilities.length,190, 12);

        //Set healthbar color based on if friendly or not
        g.setColor(new Color(0,200,255));

        //Draw at bottom of screen, -10 is for the height of the bar
        g.fillRect(1139, 45+105*abilities.length, (int) (190 * ratio), 12);
        g.setColor(Color.BLACK);
        g.drawRect(1139, 45+105*abilities.length,190, 12);
        g.drawString(getEnergy() + "/" + getMaxEnergy(), 1169, 56+105*abilities.length);

    }

}
