import utils.AnimatedSprite;

import java.awt.Graphics;

/**
 * [DamagingAbility]
 * Abstract class for any ability that does damage based on a constant, and a ratio of the caster's attack
 * @version 1.0
 * @author Allen Liu
 * @since June 10, 2019
 */
abstract public class DamagingAbility extends Ability {

    private double baseDamage;
    private double ratio;

    /**
     * [Ability]
     * Constructor for single target and aoe abilities
     *
     * @param animation    the animation that is played on ability case
     * @param name         the displayed name of the ability
     * @param desc         the displayed description of the ability
     * @param energyCost   the energy cost of the ability
     * @param cooldown     the cooldown in turns
     * @param xRange       the range in the x axis in tiles
     * @param yRange       the range in the y axis in tiles
     * @param baseDamage   the amount of damage that the ability will do
     * @param damageRatio  the percentage of the casting entity's attack to include in damage
     * @param enemyTarget  whether the ability can target enemies (relative to the caster) or not
     * @param friendTarget whether the ability can target allies (relative to the caster) or not
     */
    DamagingAbility(AnimatedSprite animation, String name, String desc, double energyCost, int cooldown, int xRange, int yRange, double baseDamage, double damageRatio, boolean enemyTarget, boolean friendTarget) {
        super(animation, name, desc, energyCost, cooldown, xRange, yRange, enemyTarget, friendTarget);
        this.baseDamage = baseDamage;
        this.ratio = damageRatio;
    }

    /**
     * [getBaseDamage]
     * gets the damage that the ability does
     * @return baseDamage, gets the damage that the ability does ignoring ratio
     */
    public double getBaseDamage(){
        return baseDamage;
    }

    /**
     * [getDamage]
     * gets the damage of tha ability when used, including the attack power added by the caster
     * @return double, the base damage of the ability + (the attack power of the caster, multiplied by the damage ratio)
     */
    public double getDamage() {
        return baseDamage + ratio * getEntitySource().getAttack();
    }

    /**
     * [getRatio]
     * gets the ratio attack conversion of the ability
     * @return ratio, the ratio attack conversion of the ability
     */
    public double getRatio(){
        return ratio;
    }

    /**
     * [drawInfoBox]
     * Draws the default ability text box, but also draws damage dealt.
     * @param g the graphics object to draw with
     * @param x the x position to draw from (top left corner)
     * @param y the y position to draw from (top left corner)
     */
    @Override
    public void drawInfoBox(Graphics g, int x, int y) {
        super.drawInfoBox(g, x, y);
        g.drawString("Damage: " + getDamage(), x + 110, 54 + y);
        g.drawRect(x + 100,37+y,163,22);
    }
}
