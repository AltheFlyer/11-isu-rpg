import utils.AnimatedSprite;

import java.awt.Graphics;

public class FirstInRowAbility extends DamagingAbility {

    /**
     * [DamagingAbility]
     * Constructor for single target and aoe abilities
     *
     * @param animation    the animation that is played on ability case
     * @param name         the displayed name of the ability
     * @param desc         the displayed description of the ability
     * @param energyCost   the energy cost of the ability
     * @param cooldown     the cooldown in turns
     * @param baseDamage   the amount of damage that the ability will do
     * @param damageRatio  the percentage of the casting entity's attack to include in damage
     * @param enemyTarget  whether the ability can target enemies (relative to the caster) or not
     * @param friendTarget whether the ability can target allies (relative to the caster) or not
     */
    FirstInRowAbility(AnimatedSprite animation, String name, String desc, double energyCost, int cooldown, double baseDamage, double damageRatio, boolean enemyTarget, boolean friendTarget) {
        super(animation, name, desc, energyCost, cooldown, 6, 0, baseDamage, damageRatio, enemyTarget, friendTarget);
    }

    /**
     * action: This method will target and affect the selected tiles in a certain pattern
     *
     * @param jointMap The map that will be affected
     * @param i        one of the selected coordinates
     * @param j        the other selected coordinate
     * @return it will return a value based on if an action was valid or not, if it was, it will unindicate everything and reset selectedAbility on levelscreen
     */
    @Override
    public void action(JointMap jointMap, int i, int j) {
        jointMap.target(i, j, getDamage());
    }

    /**
     * [drawSelectedArea]
     * Draws the area that will be affected by an ability
     *
     * @param i        the x of the tile that the mouse is hovered over
     * @param j        the y of the tile that the mouse is hovered over
     * @param g        the graphics object to draw with
     * @param jointMap map to draw the hovered square on
     */
    @Override
    public void drawHoverAttack(int i, int j, Graphics g, JointMap jointMap) {
        drawHoverAttackSingleHelper(i,j,g,jointMap);
    }

    /**
     * [indicateValidTiles]
     * Draws valid selection regions for the center of an ability
     *
     * @param jointMap the map to draw on
     */
    @Override
    public void indicateValidTiles(JointMap jointMap) {
        if ((getEntitySource().getEnergy() >= getEnergyCost()) && (getCurrentCooldown() <= 0)) {
            int y = getEntitySource().getYGrid();

            int startX = getEntitySource().getXGrid();
            int endX = getEntitySource().getXGrid();


            if (getEntitySource().isFriendly()) {
                startX -= getXRange();
                endX += getXRange();
                int x = startX;

                //Run from left to right, stopping when a target is found
                while (!isTargetFound(jointMap, x, y) && (x <= endX)) {
                    x++;
                }

            } else {
                startX += getXRange();
                endX -= getXRange();
                int x = startX;

                //Run from right to left, stopping when a target is found
                while (!isTargetFound(jointMap, x, y) && (x >= endX)) {
                    x--;
                }
            }
        }
    }

    /**
     * [isTargetFound]
     * checks if a target can be found for the purpose of finding the first target in a row
     * @param jointMap the map that the ability is on
     * @param x the x position of the map to checl
     * @param y the y position of the map to check
     * @return boolean, whether a target has been found
     */
    public boolean isTargetFound(JointMap jointMap, int x, int y) {
        if (jointMap.tileExists(x, y) && getEntitySource().isAlive()) {
            jointMap.indicate(x, y);
            if (!jointMap.isEmpty(x, y) && (jointMap.isTileFriendly(x, y) != getEntitySource().isFriendly())) {
                 jointMap.isTargetable(x, y);
                 return true;
            }
        }
        return false;
    }
}
