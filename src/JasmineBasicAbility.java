import utils.AnimatedSprite;

/**
 * [JasmineBasicAbility.java]
 * a basic single attack that will reset Jasmine's diagonal move
 * @version 1.0
 * @author Kevin Liu
 * @since June 7, 2019
 */
public class JasmineBasicAbility extends SingleAbility {
    /**
     * [JasmineBasicAbility]
     * Constructor for jasmine's single target ability
     * @param animation the animation that is played on ability case
     * @param name the displayed name of the ability
     * @param desc the displayed description of the ability
     */
    JasmineBasicAbility(AnimatedSprite animation, String name, String desc){
        super (animation, name, desc, 20, 1, 6, 0, 0, 0.5, true, false);
    }

    @Override
    /**
     * action: This method will target and damage a single tile then lower the cooldown of waltz to 0
     * @param jointMap: The map that will be affected
     * @param x: one of the selected coordinates
     * @param y: the other selected coordinate
     */
    public void action(JointMap jointMap, int x, int y){
        jointMap.target(x, y, getDamage());
        getEntitySource().lowerCooldown(0,1);
    }
}
