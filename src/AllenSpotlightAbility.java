import utils.AnimatedSprite;

/**
 * [AllenSpotlightAbility.java]
 * A special spotlight ability for Allen the Physicist
 * @version 1.1
 * @author Kevin Liu
 * @since June 8, 2019
 */
public class AllenSpotlightAbility extends StatusAbility {

    /**
     * [AllenSpotlightAbility]
     * generates a StatusAbility with single target capabilities
     * @param animation the animation that is played on ability case
     * @param name the displayed name of the ability
     * @param desc the displayed description of the ability
     */
    public AllenSpotlightAbility(AnimatedSprite animation, String name, String desc) {
        super(new MarkStatus(1), animation, name, desc, 20, 1, 4,2, true,true);
    }

    /**
     * [action]
     * runs the action for the ability, which is to apply the status effect to a targeted entity
     * @param jointMap the map that the ability is cast on
     * @param x the x position of the desired tile
     * @param y the y position of the desired tile
     */

    @Override
    public void action(JointMap jointMap, int x, int y) {
        jointMap.inflictStatus(x, y, getEffect().spread());
    }
}
