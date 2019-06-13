import utils.AnimatedSprite;

import java.awt.Graphics;
/**
 * [SpearAbility.java]
 * low range high damage attack
 * @version 1.0
 * @author Kevin Liu
 * @since May 31, 2019
 */
public class SpearAbility extends DamagingAbility {

    /**
     * [SpearAbility]
     * Constructor for a short range spear ability
     * @param animation the animation that is played on ability case
     * @param name the displayed name of the ability
     * @param desc the displayed description of the ability
     * @param energyCost the energy cost of the ability
     * @param cooldown the cooldown in turns
     * @param damage the amount of damage that the ability will do, unmodded
     * @param ratio the percentage of attack that is added by the caster as a decimal
     */
    SpearAbility(AnimatedSprite animation, String name, String desc, double energyCost, int cooldown, double damage, double ratio) {
        super(animation, name, desc, energyCost, cooldown, 2, 1, damage, ratio, true, false);
    }
    /**
     * action: This method will target and damage a selected tile
     * @param jointMap: The map that will be affected
     * @param x: one of the selected coordinates
     * @param y: the other selected coordinate
     */
    @Override
    public void action(JointMap jointMap, int x, int y) {
        jointMap.target(x, y, this.getDamage());
    }

    /**
     * [drawHoverAttack]
     * Draws the area that will be affected by an ability
     * @param x the x of the tile that the mouse is hovered over
     * @param y the y of the tile that the mouse is hovered over
     * @param g the graphics object to draw with
     * @param jointMap map to draw the hovered square on
     */
    @Override
    public void drawHoverAttack(int x, int y, Graphics g, JointMap jointMap) {
        drawHoverAttackSingleHelper(x,y,g,jointMap);
    }

    /**
     * This method will indicate and make tiles targetable for you to click on it with your cursor to enact an ability.
     * @param jointMap: The tiles on the jointMap array will be modified (some tiles will be indicated, some will become targetable)
     */
    @Override
    public void indicateValidTiles(JointMap jointMap) {
        int rangeAhead = getEntitySource().getXGrid() + 2;
        int rangeBehind = getEntitySource().getXGrid();
        int rangeDown = getEntitySource().getYGrid();
        int rangeUp = getEntitySource().getYGrid();

        indicateValidTileHelper(jointMap, rangeAhead, rangeBehind, rangeDown, rangeUp, false, true);
    }
}
