import utils.AnimatedSprite;

/**
 * [JasmineBlastAbility.java]
 * a powerful low range move that will move user and affected target
 * @version 1.0
 * @author Kevin Liu
 * @since June 7, 2019
 */
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
     * action: This method will target damage a selected tile, push user and affected back by 1
     * then reset cooldown of waltz to 0
     * @param jointMap: The map that will be affected
     * @param x: one of the selected coordinates
     * @param y: the other selected coordinate
     */
    @Override
    public void action(JointMap jointMap, int x, int y) {
        jointMap.target(x, y, this.getDamage());
        jointMap.moveOnTile(getEntitySource().getXGrid(),getEntitySource().getYGrid(),getEntitySource().getXGrid()-1,getEntitySource().getYGrid());
        jointMap.moveOnTile(x,y,x+1,y);
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
