import utils.AnimatedSprite;

/**
 * [EthanTidesAbility.java]
 * Creating of a tide ability that will do damage based on stored power
 * @version 1.1
 * @author Kevin Liu
 * @since June 13, 2019
 */
public class EthanTidesAbility extends AOEAbility{
    int stacks;
    /**
     * [EthanTidesAbility]
     * Constructor for ethan's special tide
     * @param animation the animation that is played on ability case
     * @param name the displayed name of the ability
     * @param desc the displayed description of the ability
     */
    EthanTidesAbility(AnimatedSprite animation, String name, String desc){
        super (animation, name, desc, 0, 1,0,2, 0,2, 0,0, true, false);
    }

    /**
     * action: This method will target and affect the selected tiles in a certain pattern
     * @param jointMap: The map that will be affected
     * @param x one of the selected coordinates
     * @param y the other selected coordinate
     */
    public void action(JointMap jointMap, int x, int y){
        for (int k = y - getYAOE(); k <= y + getYAOE(); k++) {
            if (jointMap.tileExists(x,k)) {
                if (jointMap.isTileFriendly(x, k) != getEntitySource().isFriendly()) {
                    jointMap.target(x, k, getDamage());
                }
            }
        }
        if (!jointMap.isEmpty(getEntitySource().getXGrid(),getEntitySource().getYGrid())) {
            removeStoredPower(jointMap, getEntitySource().getXGrid(), getEntitySource().getYGrid());
        }
    }

    /**
     * [getDamage]
     * @return a different calculation on the display of how much an ability damages one (storedPower matters)
     */
    @Override
    public double getDamage() {
        return (int)(stacks*1.2);
    }

    /**
     * [indicateValidTiles]
     * This method will indicate and make tiles targetable for you to click on it with your cursor to enact an ability.
     * @param jointMap The tiles on the jointMap array will be modified (some tiles will be indicated, some will become targetable)
     */
    public void indicateValidTiles(JointMap jointMap){
        jointMap.indicate(4,getEntitySource().getYGrid());
        jointMap.isTargetable(4,getEntitySource().getYGrid());
        if (!jointMap.isEmpty(getEntitySource().getXGrid(), getEntitySource().getYGrid())) {
            stacks = getStoredPower(jointMap, getEntitySource().getXGrid(), getEntitySource().getYGrid());
        }
    }
}
