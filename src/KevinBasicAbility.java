import utils.AnimatedSprite;

public class KevinBasicAbility extends SingleAbility {
    /**
     * [KevinBasicAbility]
     * Constructor for single target abilities
     * @param animation the animation that is played on ability case
     * @param name the displayed name of the ability
     * @param desc the displayed description of the ability
     */
    KevinBasicAbility(AnimatedSprite animation, String name, String desc){
        super (animation, name, desc, 20, 1, 6, 0, 0, 0.5, true, false);
    }

    @Override
    /**
     * [KevinBasicAbility]
     * action: This method will target and affect a single tile then lower the cooldown of waltz to 0
     * @param jointMap: The map that will be affected
     * @param i: one of the selected coordinates
     * @param j: the other selected coordinate
     * @return: it will return a value based on if an action was valid or not, if it was, it will unindicate everything and reset selectedAbility on levelscreen
     */
    public void action(JointMap jointMap, int i, int j){
        if (getIsMarked(jointMap, i, j)){
            jointMap.target(i, j, getDamage());
            jointMap.target(i, j, getDamage());
        }
        jointMap.target(i, j, getDamage());
        getEntitySource().lowerCooldown(0,1);
    }
}