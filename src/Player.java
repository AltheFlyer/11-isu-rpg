import utils.AnimatedSprite;
import utils.TextDrawer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity{

    Icon ico;

    private String debugName;
    private AnimatedSprite gif;

    /**
     * [Player]
     * creates a player with its debug name for use in grid configuration
     * @param health the max health of the player
     * @param attack the base attack of the player
     * @param defence the base defence of the player
     * @param energy the max energy of the player
     * @param debugName the debug name of the plauer
     * @param name the display name of the player
     * @param animatedSprite the idle animated sprite for the player
     * @param abilities the list of abilities that the player can use
     */
    Player(double health, double attack, double defence, double energy, String debugName, String name, AnimatedSprite animatedSprite, Ability[] abilities){
        super(health, attack, defence, energy,name, abilities);
        for (int i = 0; i < abilities.length; i++){
            abilities[i].setEntitySource(this);
        }
        this.debugName = debugName;
        ico = new Icon(new Rectangle(0, 0, 40, 40), "assets/icons/test.png");
        //this.sprite = sprite;

        gif = animatedSprite;
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
        //g.drawImage(sprite, 30,15+105*abilities.length,60,60, null);
        //g.fillRect(30,15+105*abilities.length,60,60);
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
        gif.draw(g,x,y);

        g.setColor(Color.MAGENTA);
        if (getName().equals("yellow")){
            g.setColor(Color.yellow);
        } else if (getName().equals("cyan")){
            g.setColor(Color.CYAN);
        }
        //g.fillRect(x,y,120,120);
        //g.drawImage(sprite, x, y, 120, 120, null);
        g.setColor(Color.BLACK);
        if (indicated){
            g.setColor(new Color(0, 0, 0, 100));
            g.fillRect(x,y,120,120);
        }
        g.drawRect(x, y, 120, 120);
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
        g.drawString(Math.ceil(getHealth()) + "/" + getMaxHealth(), 130, 44+105*abilities.length);
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

    /**
     * [getDebugName]
     * Gets the player's debug name (the name used to load from files)
     */
    public String getDebugName() {
        return debugName;
    }

    //Drawing out abilities on the selection screen
    public void drawSelectAbilities(Graphics g){
        //BACKGROUND
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0,0,323,768);
        g.setColor(Color.BLACK);
        g.drawRect(0,0,323,768);

        //Draw the player profile at the bottom so you know!
        g.setColor(Color.MAGENTA);
        //g.drawImage(sprite, 30, 15+105*abilities.length,60,60, null);
        //g.fillRect(30,15+105*abilities.length,60,60);

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
    }

    /**
     * [isFriendly]
     * gets whether the entity is friendly or not
     * @return boolean, true for all players
     */
    public boolean isFriendly() {
        return true;
    }
}
