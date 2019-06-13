import utils.AnimatedSprite;

/**
 * [EthanPactAbility.java]
 * A special pact ability for Ethan
 * @version 1.1
 * @author Kevin Liu
 * @since June 8, 2019
 */
public class EthanPactAbility extends StatusAbility {

    /**
     * [EthanPactAbility]
     * generates a StatusAbility which marks an ally and grants a small heal
     * @param animation the animation that is played on ability case
     * @param name the displayed name of the ability
     * @param desc the displayed description of the ability
     */
    public EthanPactAbility(AnimatedSprite animation, String name, String desc) {
        super(new MarkStatus(1), animation, name, desc, 20, 1, 2,2, false,true);
    }

    /**
     * [action]
     * runs the action for the ability, which is to mark a targeted entity
     * @param jointMap the map that the ability is cast on
     * @param x the x position of the desired tile
     * @param y the y position of the desired tile
     */

    @Override
    public void action(JointMap jointMap, int x, int y) {
        jointMap.inflictStatus(x, y, getEffect().spread());
        jointMap.target(x,y,-20);
        jointMap.target(getEntitySource().getXGrid(), getEntitySource().getYGrid(), 20);
    }
}
