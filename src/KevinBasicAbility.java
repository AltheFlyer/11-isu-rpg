import utils.AnimatedSprite;
/**
 * [KevinBasicAbility.java]
 * basic single attack in a row that also afflicts DissectedStatus
 * @version 1.0
 * @author Kevin Liu
 * @since June 9, 2019
 */
public class KevinBasicAbility extends SingleAbility {
    /**
     * [KevinBasicAbility]
     * Constructor for kevin's singe target ability
     * @param animation the animation that is played on ability case
     * @param name the displayed name of the ability
     * @param desc the displayed description of the ability
     */
    KevinBasicAbility(AnimatedSprite animation, String name, String desc){
        super (animation, name, desc, 20, 1, 6, 0, 0, 0.5, true, false);
    }


    /**
     * action: This method will target and affect a single tile while affecting it with DissectedStatus
     * @param jointMap: The map that will be affected
     * @param x: one of the selected coordinates
     * @param y: the other selected coordinate
     */
    @Override
    public void action(JointMap jointMap, int x, int y){
        jointMap.inflictStatus(x,y,new DissectedStatus(1).spread());
        if (getIsMarked(jointMap,x,y)){
            jointMap.inflictStatus(x,y,new DissectedStatus(1).spread());
        }
        jointMap.target(x, y, getDamage());
    }
}