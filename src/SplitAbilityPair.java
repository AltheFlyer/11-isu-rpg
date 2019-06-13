import utils.AnimatedSprite;

/**
 * [SplitAbilityPair]
 * A general form for two abilities to act as one, where targeting systems are different
 * @version 1.0
 * @author Allen Liu
 * @since June 12, 2019
 */
public class SplitAbilityPair extends AbilityPair {

    /**
     * [SplitAbilityPair]
     * Creates a split ability pair
     *
     * @param animation     the animation to use when the ability is cast
     * @param name          the displayed name of the ability
     * @param desc          the displayed text description of the ability
     * @param energyCost    the energy cost of the ability
     * @param cooldown      the cooldown of the ability in turns
     * @param firstAbility  an ability that is part of the full AbilityPair, this ability is processed first and follows targeting defined by .action()
     * @param secondAbility an ability that is part of the full AbilityPair, this ability is processed last and does NOT follow targeting from .action()
     */
    public SplitAbilityPair(AnimatedSprite animation, String name, String desc, int energyCost, int cooldown, Ability firstAbility, Ability secondAbility) {
        super(animation, name, desc, energyCost, cooldown, firstAbility, secondAbility);
    }

    /**
     * [action]
     * performs the actions of the two contained abilities
     * @param jointMap the map that the ability is cast on
     * @param x the targeted x position
     * @param y the targeted y position
     */
    @Override
    public void action(JointMap jointMap, int x, int y) {
        firstAbility.indicateValidTiles(jointMap);
        firstAbility.action(jointMap, x, y);

        jointMap.unIndicateAll();
        jointMap.unTargetableAll();
        secondAbility.selectRandomTile(jointMap);
    }

}
