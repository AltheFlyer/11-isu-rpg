import utils.AnimatedSprite;

import java.awt.Graphics;

/**
 * [AbilityPair]
 * An attempt at making a general form for two abilities to act as one. Theoretically (horrifically) recursive
 * @version 1.0
 * @author Allen Liu
 * @since June 7, 2019
 */
public class AbilityPair extends Ability {

    protected Ability firstAbility;
    protected Ability secondAbility;

    /**
     * [AbilityPair]
     * Creates an ability pair, which combines the effects of two selected abilities. This does include other AbilityPair objects...
     * @param animation the animation to use when the ability is cast
     * @param name the displayed name of the ability
     * @param desc the displayed text description of the ability
     * @param energyCost the energy cost of the ability
     * @param cooldown the cooldown of the ability in turns
     * @param firstAbility an ability that is part of the full AbilityPair, this ability is processed first
     * @param secondAbility an ability that is part of the full AbilityPair, this ability is processed last
     */
    public AbilityPair(AnimatedSprite animation, String name, String desc, int energyCost, int cooldown, Ability firstAbility, Ability secondAbility) {
        super(name, desc, energyCost, cooldown);
        this.firstAbility = firstAbility;
        this.secondAbility = secondAbility;

        setAnimation(animation);
    }

    /**
     * [action]
     * performs the actions of the two contained abilities
     * @param jointMap the map that the ability is cast on
     * @param x the targeted x position
     * @param y the targeted y position
     */
    @Override
    public void action(JointMap jointMap, int x, int y) {
        firstAbility.action(jointMap, x, y);
        secondAbility.action(jointMap, x, y);
    }

    /**
     * [drawHoverAttack]
     * draws the affected area of the ability, currently based on the selectable areas of BOTH contained abilities
     * @param x the targeted x position
     * @param y the targeted y position
     * @param g the graphics object to draw with
     * @param jointMap the map that the ability is indicated on
     */
    @Override
    public void drawHoverAttack(int x, int y, Graphics g, JointMap jointMap) {
        firstAbility.drawHoverAttack(x, y, g, jointMap);
        secondAbility.drawHoverAttack(x, y, g, jointMap);
    }

    /**
     * [indicateValidTiles]
     * indicates the targetable tiles, based on what is targetable by BOTH contained abilities
     * @param jointMap the map that the ability is part of
     */
    @Override
    public void indicateValidTiles(JointMap jointMap) {
        firstAbility.indicateValidTiles(jointMap);
        secondAbility.indicateValidTiles(jointMap);
    }

    /**
     * [setEntitySource]
     * sets the entity source of the two contained abilities
     * @param e the entity that is the source to the main ability pair (and thus the contained abilities)
     */
    @Override
    public void setEntitySource(Entity e) {
        super.setEntitySource(e);
        firstAbility.setEntitySource(e);
        secondAbility.setEntitySource(e);
    }

    /**
     * [drawInfoBox]
     * Draws the default ability text box, but also draws damage IF the abilities would do damage.
     * @param g the graphics object to draw with
     * @param x the x position to draw from (top left corner)
     * @param y the y position to draw from (top left corner)
     */
    @Override
    public void drawInfoBox(Graphics g, int x, int y) {
        super.drawInfoBox(g, x, y);
        double damage = 0;

        if (firstAbility instanceof DamagingAbility) {
            damage += ((DamagingAbility) firstAbility).getDamage();
        }
        if (secondAbility instanceof DamagingAbility) {
            damage += ((DamagingAbility) secondAbility).getDamage();
        }

        if (damage != 0) {
            g.drawString("Damage: " + damage, x + 110, 54 + y);
            g.drawRect(x + 100,37+y,163,22);
        }
    }
}
