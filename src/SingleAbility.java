import utils.AnimatedSprite;

import java.awt.Graphics;

/**
 * [SingleAbility.java]
 * Creating of single target abilities that will only select and target one tile
 * @version 1.1
 * @author Kevin Liu
 * @since May 31, 2019
 */
public class SingleAbility extends DamagingAbility {
    /**
     * [SingleAbility]
     * Constructor for single target abilities
     * @param animation the animation that is played on ability case
     * @param name the displayed name of the ability
     * @param desc the displayed description of the ability
     * @param energyCost the energy cost of the ability
     * @param cooldown the cooldown in turns
     * @param xRange the range in the x axis in tiles
     * @param yRange the range in the y axis in tiles
     * @param damage the amount of damage that the ability will do
     * @param ratio the percentage of attack that is added by the caster as a decimal
     * @param enemyTarget whether the ability can target enemies (relative to the caster) or not
     * @param friendTarget whether the ability can target allies (relative to the caster) or not
     */
    SingleAbility(AnimatedSprite animation, String name, String desc, double energyCost, int cooldown, int xRange, int yRange, double damage, double ratio, boolean enemyTarget, boolean friendTarget){
        super (animation, name, desc, energyCost, cooldown, xRange, yRange, damage, ratio, enemyTarget, friendTarget);
        setXAOE(0);
        setYAOE(0);
    }

    /**
     * action: This method will target and damage a single tile
     * @param jointMap: The map that will be affected
     * @param x: one of the selected coordinates
     * @param y: the other selected coordinate
     */
    public void action(JointMap jointMap, int x, int y){
        jointMap.target(x, y, getDamage());
    }
    /**
     * [drawHoverAttack]
     * Draws the area that will be affected by an ability
     * @param x the x of the tile that the mouse is hovered over
     * @param y the y of the tile that the mouse is hovered over
     * @param g the graphics object to draw with
     * @param jointMap map to draw the hovered square on
     */
    public void drawHoverAttack(int x, int y, Graphics g, JointMap jointMap) {
        drawHoverAttackSingleHelper(x,y,g,jointMap);
    }

    /**
     * This method will indicate and make tiles targetable for you to click on it with your cursor to enact an ability.
     * @param jointMap: The tiles on the jointMap array will be modified (some tiles will be indicated, some will become targetable)
     */
    public void indicateValidTiles(JointMap jointMap){
        int rangeAhead = getEntitySource().getXGrid() + getXRange();
        int rangeBehind = getEntitySource().getXGrid() - getXRange();
        int rangeDown = getEntitySource().getYGrid() + getYRange();
        int rangeUp = getEntitySource().getYGrid() - getYRange();

        indicateValidTileHelper(jointMap, rangeAhead, rangeBehind, rangeDown, rangeUp, false, false);
    }
}
