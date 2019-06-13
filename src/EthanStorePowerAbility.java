import utils.AnimatedSprite;

import java.awt.Graphics;

/**
 * [EthanStorePowerAbility.java]
 * A charging ability
 * @version 1.0
 * @author Kevin Liu
 * @since June 11, 2019
 */
public class EthanStorePowerAbility extends DamagingAbility{
    double sacrificed;
    /**
     * [EthanStorePowerAbility]
     * Constructor for abilities that charge up a character
     * @param animation the animation that is played on ability case
     * @param name the displayed name of the ability
     * @param desc the displayed description of the ability
     */
    EthanStorePowerAbility(AnimatedSprite animation, String name, String desc) {
        super(animation, name, desc, 20,1, 0,0,0,0, false, true);
    }

    /**
     * action: This method will target and damage an entity in exchange for power
     * @param jointMap: The map that will be affected
     * @param i one of the selected coordinates
     * @param j the other selected coordinate
     */
    public void action(JointMap jointMap, int i, int j){
        for (int l = 0; l < 3; l ++) {
            for (int k = 0; k < 3; k ++) {
                if (jointMap.tileExists(k, l)) {
                    if (!jointMap.isEmpty(k,l)) {
                        if ((l == getEntitySource().getYGrid()) && (k == getEntitySource().getXGrid())) {
                            sacrificed += jointMap.getEntity(k,l).getHealth() / 2;
                            jointMap.target(k,l, jointMap.getEntity(k,l).getHealth() / 2);
                        } else if ((getFriendTarget()) && (jointMap.isTileFriendly(k, l) == getEntitySource().isFriendly()) && (getIsMarked(jointMap, k, l))) {
                            sacrificed += jointMap.getEntity(k,l).getHealth() / 2;
                            jointMap.target(k,l, jointMap.getEntity(k,l).getHealth() / 2);
                        }
                    }
                }
            }
        }
        getEntitySource().inflictStatus(jointMap, new StoredPowerStatus((int) sacrificed));
        sacrificed = 0;
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
     * [indicateValidTiles]
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
