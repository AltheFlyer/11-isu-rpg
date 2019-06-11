import java.awt.Graphics;

/**
 * [ConstructorAbility]
 * restores health to full for an entity, and gives an attack buff
 * @version 1.0
 * @author Allen Liu
 * @since June 11, 2019
 */
public class ConstructorAbility extends StatusAbility {

    /**
     * [Ability]
     * Constructor for movement-style abilities
     */
    ConstructorAbility() {
        super(new AttackStatModifier(25), null, "Constructor", "Resets this enemy.",
                0, 100, 0, 0, false, true);
    }

    /**
     * [action]
     * resets all of the entity's stats
     * @param jointMap The map that will be affected
     * @param x        one of the selected coordinates
     * @param y        the other selected coordinate
     * @return it will return a value based on if an action was valid or not, if it was, it will unindicate everything and reset selectedAbility on levelscreen
     */
    @Override
    public void action(JointMap jointMap, int x, int y) {
        getEntitySource().setHealth(getEntitySource().getMaxHealth());
        getEntitySource().getStatuses().clear();
        getEntitySource().inflictStatus(jointMap, getEffect().spread());
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
        drawHoverAttackSingleHelper(i, j, g, jointMap);
    }

    /**
     * [indicateValidTiles]
     * Draws valid selection regions for the center of an ability
     *
     * @param jointMap the map to draw on
     */
    @Override
    public void indicateValidTiles(JointMap jointMap) {
        jointMap.indicate(getEntitySource().getXGrid(), getEntitySource().getYGrid());
        jointMap.isTargetable(getEntitySource().getXGrid(), getEntitySource().getYGrid());
    }
}