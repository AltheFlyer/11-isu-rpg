import utils.AnimatedSprite;
/**
 * [EthanTransfuseAbility.java]
 * basic single attack in a row that also heals a bit
 * @version 1.0
 * @author Kevin Liu
 * @since June 9, 2019
 */
public class EthanTransfuseAbility extends SingleAbility {
    /**
     * [EthanTransfuseAbility]
     * Constructor for ethan's single target ability
     * @param animation the animation that is played on ability case
     * @param name the displayed name of the ability
     * @param desc the displayed description of the ability
     */
    EthanTransfuseAbility(AnimatedSprite animation, String name, String desc){
        super (animation, name, desc, 40, 1, 3, 2, 0, 0.5, true, false);
    }

    @Override
    /**
     * action: This method will target and affect a single tile while affecting it with DissectedStatus
     * @param jointMap: The map that will be affected
     * @param i: one of the selected coordinates
     * @param j: the other selected coordinate
     */
    public void action(JointMap jointMap, int i, int j){
        jointMap.target(getEntitySource().getXGrid(), getEntitySource().getYGrid(),-(getEntitySource().getMaxHealth()-getEntitySource().getHealth())/4);
        if (getIsMarked(jointMap,i,j)){
            jointMap.target(getEntitySource().getXGrid(), getEntitySource().getYGrid(),-(getEntitySource().getMaxHealth()-getEntitySource().getHealth())/4);
        }
        jointMap.target(i, j, getDamage());
    }
}