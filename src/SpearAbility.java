import utils.AnimatedSprite;

import java.awt.Graphics;

public class SpearAbility extends DamagingAbility {

    /**
     * [AOEAbility]
     * Constructor for Spear abilities
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
     * action: This method will target and affect the selected tiles in a certain pattern
     * @param jointMap: The map that will be affected
     * @param i: one of the selected coordinates
     * @param j: the other selected coordinate
     * @return: it will return a value based on if an action was valid or not, if it was, it will unindicate everything and reset selectedAbility on levelscreen
     */
    @Override
    public void action(JointMap jointMap, int i, int j) {
        jointMap.target(i, j, this.getDamage());
    }

    /**
     * [drawHoverAttack]
     * Draws the area that will be affected by an ability
     * @param i the x of the tile that the mouse is hovered over
     * @param j the y of the tile that the mouse is hovered over
     * @param g the graphics object to draw with
     * @param jointMap map to draw the hovered square on
     */
    @Override
    public void drawHoverAttack(int i, int j, Graphics g, JointMap jointMap) {
        drawHoverAttackSingleHelper(i,j,g,jointMap);
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
