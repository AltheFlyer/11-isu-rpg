import utils.AnimatedSprite;

public class KevinSpotlightAbility extends StatusAbility {

    /**
     * [KevinSpotlightAbility]
     * generates a StatusAbility with single target capabilities
     * @param animation the animation that is played on ability case
     * @param name the displayed name of the ability
     * @param desc the displayed description of the ability
     */
    public KevinSpotlightAbility(AnimatedSprite animation, String name, String desc) {
        super(new MarkStatus(1), animation, name, desc, 20, 2, 4,2, true,true);
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
