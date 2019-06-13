import utils.AnimatedSprite;

import java.awt.Graphics;
/**
 * [LureAbility.java]
 * mark 1 target and drag them closer to the center
 * @version 1.0
 * @author Kevin Liu
 * @since June 7, 2019
 */
public class LureAbility extends DamagingAbility {
    /**
     * [LureAbility]
     * Constructor for a lureAbility that brings a target forwards
     * @param animation the animation that is played on ability case
     * @param name the displayed name of the ability
     * @param desc the displayed description of the ability
     * @param energyCost the energy cost of the ability
     * @param cooldown the cooldown in turns
     * @param xRange the range in the x axis in tiles
     * @param yRange the range in the y axis in tiles
     * @param damage the amount of damage that the ability will do
     * @param ratio the percentage of attack that is added by the caster as a decimal
     */
    LureAbility(AnimatedSprite animation, String name, String desc, double energyCost, int cooldown, int xRange, int yRange, double damage, double ratio) {
        super(animation, name, desc, energyCost,cooldown, xRange,yRange,damage, ratio, true, false);
    }

    /**
     * action: This method will target and potentially damage a selected tile while pulling the affecting entity forwards
     * @param jointMap: The map that will be affected
     * @param i: one of the selected coordinates
     * @param j: the other selected coordinate
     */
    public void action(JointMap jointMap, int i, int j){
        jointMap.target(i, j, getDamage());

        if (getEntitySource() instanceof Player) {
            jointMap.inflictStatus(i,j,new MarkStatus(1).spread());
            jointMap.moveOnTile(i, j, i - 1, j);

        } else if (getEntitySource() instanceof Enemy) {
            jointMap.moveOnTile(i, j, i + 1, j);
        }
    }
    /**
     * [drawHoverAttack]
     * Draws the area that will be affected by an ability
     * @param i the x of the tile that the mouse is hovered over
     * @param j the y of the tile that the mouse is hovered over
     * @param g the graphics object to draw with
     * @param jointMap map to draw the hovered square on
     */
    public void drawHoverAttack(int i, int j, Graphics g, JointMap jointMap) {
        //What to do here???
        drawHoverAttackSingleHelper(i,j,g,jointMap);
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
