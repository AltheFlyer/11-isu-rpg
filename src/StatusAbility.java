import utils.AnimatedSprite;

import java.awt.Graphics;

/**
 * [StatusAbility.java]
 * A single target ability that inflicts a status (deals no damage). If you want this paired up with some damage or other
 * statuses, look into AbilityPair.
 * @version 1.1
 * @author Allen Liu
 * @since June 8, 2019
 */
public class StatusAbility extends Ability {

    private StatusEffect effect;

    /**
     * [StatusAbility]
     * generates a StatusAbility with single target capabilities
     * @param effect the status effect that is inflicted on the ability's target
     * @param animation the animation that is played on ability case
     * @param name the displayed name of the ability
     * @param desc the displayed description of the ability
     * @param energyCost the energy cost of the ability
     * @param cooldown the cooldown in turns
     * @param xRange the range in the x axis in tiles
     * @param yRange the range in the y axis in tiles
     * @param enemyTarget whether the ability can target enemies (relative to the caster) or not
     * @param friendTarget whether the ability can target allies (relative to the caster) or not
     */
    public StatusAbility(StatusEffect effect, AnimatedSprite animation, String name, String desc, double energyCost, int cooldown, int xRange, int yRange, boolean enemyTarget, boolean friendTarget) {
        super(animation, name, desc, energyCost, cooldown, xRange, yRange, 0, enemyTarget, friendTarget);

        this.effect = effect;
    }

    /**
     * [action]
     * runs the action for the ability, which is to apply the status effect to a targeted entity
     * @param jointMap the map that the ability is cast on
     * @param x the x position of the desired tile
     * @param y the y position of the desired tile
     */
    @Override
    public void action(JointMap jointMap, int x, int y) {
        jointMap.inflictStatus(x, y, effect.spread());
    }

    /**
     * [drawHoverAttack]
     * draws the affected area of the ability
     * @param x the grid x position of the target
     * @param y the grid y position of the target
     * @param g the graphics object to draw with
     * @param jointMap the map that the ability is used on
     */
    @Override
    public void drawHoverAttack(int x, int y, Graphics g, JointMap jointMap) {
        drawHoverAttackSingleHelper(x, y, g, jointMap);
    }

    /**
     * [indicateValidTiles]
     * indicates the selectable tiles for the ability
     * @param jointMap the map that the ability is used on
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
