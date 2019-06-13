import utils.AnimatedSprite;

import java.awt.Graphics;

/**
 * [EnemySummonAbility.java]
 * summons an enemy from a prefab selection
 * @version 1.0
 * @author Allen Liu
 * @since June 12, 2019
 */
public class EnemySummonAbility extends Ability {

    Enemy summon;

    /**
     * [Ability]
     * Constructor for generic abilities
     *
     * @param animation    the animation that is played on ability case
     * @param name         the displayed name of the ability
     * @param desc         the displayed description of the ability
     * @param energyCost   the energy cost of the ability
     * @param cooldown     the cooldown in turns
     * @param xRange       the range in the x axis in tiles
     * @param yRange       the range in the y axis in tiles
     * @param enemyTarget  whether the ability can target enemies (relative to the caster) or not
     * @param friendTarget whether the ability can target allies (relative to the caster) or not
     */
    EnemySummonAbility(Enemy summon, AnimatedSprite animation, String name, String desc, double energyCost, int cooldown, int xRange, int yRange, boolean enemyTarget, boolean friendTarget) {
        super(animation, name, desc, energyCost, cooldown, xRange, yRange, false, true);
        this.summon = summon;
    }

    /**
     * action: This method will target and affect the selected tiles in a certain pattern
     *
     * @param jointMap The map that will be affected
     * @param x        one of the selected coordinates
     * @param y        the other selected coordinate
     * @return it will return a value based on if an action was valid or not, if it was, it will unindicate everything and reset selectedAbility on levelscreen
     */
    @Override
    public void action(JointMap jointMap, int x, int y) {
        if (jointMap.isEmpty(x, y)) {
        }
    }

    /**
     * [drawHoverAttack]
     * Draws the area that will be affected by an ability
     *
     * @param x        the x of the tile that the mouse is hovered over
     * @param y        the y of the tile that the mouse is hovered over
     * @param g        the graphics object to draw with
     * @param jointMap map to draw the hovered square on
     */
    @Override
    public void drawHoverAttack(int x, int y, Graphics g, JointMap jointMap) {
        drawHoverAttackSingleHelper(x,y,g,jointMap);
    }

    /**
     * [indicateValidTiles]
     * Draws valid selection regions for the center of an ability
     *
     * @param jointMap the map to draw on
     */
    @Override
    public void indicateValidTiles(JointMap jointMap) {
        int rangeAhead = getEntitySource().getXGrid() + getXRange();
        int rangeBehind = getEntitySource().getXGrid() - getXRange();
        int rangeDown = getEntitySource().getYGrid() + getYRange();
        int rangeUp = getEntitySource().getYGrid() - getYRange();

        indicateValidTileHelper(jointMap, rangeAhead, rangeBehind, rangeDown, rangeUp, false, false);
    }
}
