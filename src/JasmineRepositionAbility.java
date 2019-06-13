/**
 * [JasmineRepositionAbility.java]
 * a free teleport with an energy condition
 * @version 1.0
 * @author Kevin Liu
 * @since June 7, 2019
 */
public class JasmineRepositionAbility extends BasicMoveAbility {
    /**
     * [JasmineRepositionAbility]
     * Constructor for movement for jasmine
     * @param name the displayed name of the ability
     * @param desc the displayed description of the ability
     */
    JasmineRepositionAbility(String name, String desc, double energyCost) {
        super(name, desc, energyCost, 3,4);
    }

    @Override
    /**
     * This method will indicate and make tiles targetable for you to click on it with your cursor to enact an ability.
     * @param jointMap: The tiles on the jointMap array will be modified (some tiles will be indicated, some will become targetable)
     */
    public void indicateValidTiles(JointMap jointMap) {
        if (getEntitySource().getEnergy() >= 90 && getCurrentCooldown() <= 0) {
            for (int y = 0; y < 3; y++) {
                for (int x = 0; x < 6; x++) {
                    if (Math.abs(getEntitySource().getXGrid() - x) + Math.abs(getEntitySource().getYGrid() - y) <= getMoves() && getEntitySource().isAlive()) {
                        if (jointMap.isTileFriendly(x, y) == getEntitySource().isFriendly()) {
                            jointMap.indicate(x, y);
                            //Indicate if the tile is targetable or not, at this point Single and AOE ability are used for if they can target empty tiles
                            if (jointMap.isEmpty(x, y)) {
                                jointMap.isTargetable(x, y);
                            }
                        }
                    }
                }
            }
        }
    }
}
