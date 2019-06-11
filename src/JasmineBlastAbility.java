import utils.AnimatedSprite;

import java.awt.*;

public class JasmineBlastAbility extends SpearAbility {

    /**
     * [JasmineBlastAbility]
     * Constructor for Jasmine's blast ability
     * @param animation the animation that is played on ability case
     * @param name the displayed name of the ability
     * @param desc the displayed description of the ability
     */
    JasmineBlastAbility(AnimatedSprite animation, String name, String desc) {
        super(animation, name, desc, 20, 0, 0,2);
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
        jointMap.moveOnTile(getEntitySource().getXGrid(),getEntitySource().getYGrid(),getEntitySource().getXGrid()-1,getEntitySource().getYGrid());
        jointMap.moveOnTile(i,j,i+1,j);
        getEntitySource().lowerCooldown(0,1);
    }

    /**
     * This method will indicate and make tiles targetable for you to click on it with your cursor to enact an ability.
     * @param jointMap: The tiles on the jointMap array will be modified (some tiles will be indicated, some will become targetable)
     */
    @Override
    public void indicateValidTiles(JointMap jointMap) {
        int rangeAhead = getEntitySource().getXGrid() + 1;
        int rangeBehind = getEntitySource().getXGrid();
        int rangeDown = getEntitySource().getYGrid();
        int rangeUp = getEntitySource().getYGrid();

        indicateValidTileHelper(jointMap, rangeAhead, rangeBehind, rangeDown, rangeUp, false, true);
    }
}
