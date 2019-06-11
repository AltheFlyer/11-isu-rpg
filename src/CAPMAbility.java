import utils.AnimatedSprite;

import java.awt.Graphics;

public class CAPMAbility extends DamagingAbility {
    private int distance;

    /**
     * [CAPMAbility]
     * Constructor for combination abilities
     * @param animation the animation that is played on ability case
     * @param name the displayed name of the ability
     * @param desc the displayed description of the ability
     * @param energyCost the energy cost of the ability
     * @param cooldown the cooldown in turns
     * @param damage the amount of damage that the ability will do
     * @param ratio the percentage of attack that is added by the caster as a decimal
     */
    CAPMAbility(AnimatedSprite animation, String name, String desc, double energyCost, int cooldown, double damage, double ratio){
        super (animation, name, desc, energyCost, cooldown, 6,0, damage, ratio, true, false);
        distance = 1;
    }
    /**
     * action: This method will target and affect the selected tiles in a certain pattern
     * @param jointMap: The map that will be affected
     * @param i: one of the selected coordinates
     * @param j: the other selected coordinate
     * @return: it will return a value based on if an action was valid or not, if it was, it will unindicate everything and reset selectedAbility on levelscreen
     */
    public void action(JointMap jointMap, int i, int j){
        jointMap.target(i, j, getDamage());
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
        distance = Math.abs(i-getEntitySource().getXGrid());
    }

    /**
     * [getDamage]
     * @return a different calculation on the display of how much an ability damages one by
     */
    @Override
    public double getDamage() {
        return (getBaseDamage() + getRatio() * getEntitySource().getAttack()) * distance;
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
        distance = 1;
    }
}
