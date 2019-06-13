import utils.AnimatedSprite;
import utils.TextDrawer;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

import java.awt.image.BufferedImage;

/**
 * [Player.java]
 * the constructor to create different players with health, energy, stats and abilities
 * @version 1.0
 * @author Kevin Liu
 * @since May 23, 2019
 */
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
        for (int i = 0; i < getNumAbilities(); i++){
            getAbility(i).setEntitySource(this);
        }
        this.debugName = debugName;
        ico = new Icon(new Rectangle(0, 0, 40, 40), "assets/icons/test.png");
        //this.sprite = sprite;

        gif = animatedSprite;
    }

    /**
     * [drawAbilities]
     * draws out the menu on the side which contains all of the abilities, the ability that is currently selected
     * will be shaded in
     * @param g the graphics used to draw with
     * @param ability the ability that is currently selected
     */
    //pass list of abilities into levelScreen, select based on rect hovering
    public void drawAbilities(Graphics g, Ability ability){
        //BACKGROUND
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0,0,323,768);
        g.setColor(Color.BLACK);
        g.drawRect(0,0,323,768);

        //Draw the player profile at the bottom so you know!
        g.setColor(Color.MAGENTA);

        //g.drawImage(sprite, 30,15+105*getNumAbilities(),60,60, null);
        //g.fillRect(30,15+105*getNumAbilities(),60,60);
        drawHealthBar(g);
        drawEnergyBar(g);

        //Display stats
        g.drawString("Attack power: " + getAttack(), 100, 70+105*getNumAbilities());
        g.drawString("Percent defence: " + (int) (getDefence() * 100) + "%", 100, 85+105*getNumAbilities());

        //ABILITY ICONS
        for (int i = 0; i < getNumAbilities(); i++) {
            //draw the abilities cyan if usable
            getAbility(i).drawInfoBox(g, 30, 105 * i);
        }

        for (int i = 0; i < getNumAbilities(); i++){
            if (ability != null) {
                if (ability.equals(getAbility(i))) {
                    g.setColor(new Color(0, 0, 0, 100));
                    g.fillRect(30, 15 + 105 * i, 263, 100);
                }
            }
        }
    }

    /**
     * [draw]
     * draws out the player animation on the battle grid
     * @param x the x graphical location of the player
     * @param y the y graphicat location of the player
     * @param g the graphics to draw with
     * @param indicated determines if a player should be shaded (indicated when an ability is selected)
     */
    public void draw(int x, int y, Graphics g, boolean indicated){
        gif.draw(g,x,y);

        //g.fillRect(x,y,120,120);
        //g.drawImage(sprite, x, y, 120, 120, null);
        g.setColor(Color.BLACK);
        if (indicated){
            g.setColor(new Color(0, 0, 0, 100));
            g.fillRect(x,y,120,120);
        }
        g.drawRect(x, y, 120, 120);
    }

    /**
     * [drawHealthBar]
     * draws the healthbar for a player
     * @param g the graphics used to draw with
     */
    public void drawHealthBar(Graphics g){
        //Drawing a health bar here to make it nicer
        double ratio = getHealth() / getMaxHealth();
        //Grey backing bar
        g.setColor(Color.GRAY);
        g.fillRect(100, 33+105*getNumAbilities(),190, 12);

        //Set healthbar color based on if friendly or not
        g.setColor(Color.GREEN);

        //Draw at bottom of screen, -10 is for the height of the bar
        g.fillRect(100, 33+105*getNumAbilities(), (int) (190 * ratio), 12);
        g.setColor(Color.BLACK);
        g.drawRect(100, 33+105*getNumAbilities(),190, 12);
        g.drawString(Math.ceil(getHealth()) + "/" + getMaxHealth(), 130, 44+105*getNumAbilities());
    }
    /**
     * [drawEnergyBar]
     * draws the energybar for a player
     * @param g the graphics used to draw with
     */
    public void drawEnergyBar(Graphics g){
        double ratio = getEnergy() / getMaxEnergy();
        g.setColor(Color.GRAY);
        g.fillRect(100, 45+105*getNumAbilities(),190, 12);

        //Set healthbar color based on if friendly or not
        g.setColor(new Color(0,200,255));

        //Draw at bottom of screen, -10 is for the height of the bar
        g.fillRect(100, 45+105*getNumAbilities(), (int) (190 * ratio), 12);
        g.setColor(Color.BLACK);
        g.drawRect(100, 45+105*getNumAbilities(),190, 12);
        g.drawString(getEnergy() + "/" + getMaxEnergy(), 130, 56+105*getNumAbilities());

    }

    /**
     * [getDebugName]
     * Gets the player's debug name (the name used to load from files)
     */
    public String getDebugName() {
        return debugName;
    }

    /**
     * [drawSelectedAbilities]
     * Gets the selectedAbility and shades it in when selected
     * @param g the graphics used to draw
     */
    //Drawing out abilities on the selection screen
    public void drawSelectAbilities(Graphics g){
        //BACKGROUND
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0,0,323,768);
        g.setColor(Color.BLACK);
        g.drawRect(0,0,323,768);

        //Draw the player profile at the bottom so you know!
        g.setColor(Color.MAGENTA);
        //g.drawImage(sprite, 30, 15+105*getNumAbilities(),60,60, null);
        //g.fillRect(30,15+105*getNumAbilities(),60,60);

        //ABILITY ICONS
        for (int i = 0; i < getNumAbilities(); i++) {
            //draw the abilities cyan if usable
            getAbility(i).drawInfoBox(g, 30, 105 * i);
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
