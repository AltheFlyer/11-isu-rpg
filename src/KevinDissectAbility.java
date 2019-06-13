import utils.AnimatedSprite;

import java.awt.*;
/**
 * [KevinDissectAbility.java]
 * Damage an enemy and gain energy based on number of dissect stacks on target
 * @version 1.0
 * @author Kevin Liu
 * @since June 9, 2019
 */
public class KevinDissectAbility extends SingleAbility {
    private int stacks;

    /**
     * [KevinDissectAbility]
     * Constructor for kevin's dissection ability
     * @param animation the animation that is played on ability case
     * @param name the displayed name of the ability
     * @param desc the displayed description of the ability
     */
    KevinDissectAbility(AnimatedSprite animation, String name, String desc){
        super (animation, name, desc, 0, 1, 6, 0, 0, 0.375, true, false);
    }

    @Override
    /**
     * [KevinBasicAbility]
     * action: This method will target and affect a single tile then gain energy based on dissect stacks on target
     * @param jointMap: The map that will be affected
     * @param i: one of the selected coordinates
     * @param j: the other selected coordinate
     */
    public void action(JointMap jointMap, int i, int j){
        jointMap.target(i, j, getDamage());
        getEntitySource().gainEnergy(stacks*10);
        if (!jointMap.isEmpty(i,j)) {
            removeDissect(jointMap, i, j);
        }
    }

    @Override
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
        if (!jointMap.isEmpty(i,j)) {
            stacks = getDissect(jointMap, i, j);
        }
    }

    @Override
    /**
     * [getDamage]
     * @return int, a damage value that is changed based on dissect stacks on target
     */
    public double getDamage(){
        return (getBaseDamage() + getRatio() * getEntitySource().getAttack())*stacks;
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
        stacks = 0;
    }

}