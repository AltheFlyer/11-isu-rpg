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
        if (getEntitySource().getEnergy() >= 90) {
            for (int j = 0; j < 3; j++) {
                for (int i = 0; i < 6; i++) {
                    if (Math.abs(getEntitySource().getXGrid() - i) + Math.abs(getEntitySource().getYGrid() - j) <= getMoves() && getEntitySource().isAlive()) {
                        if (jointMap.isTileFriendly(i, j) == getEntitySource().isFriendly()) {
                            jointMap.indicate(i, j);
                            //Indicate if the tile is targetable or not, at this point Single and AOE ability are used for if they can target empty tiles
                            if (jointMap.isEmpty(i, j)) {
                                jointMap.isTargetable(i, j);
                            }
                        }
                    }
                }
            }
        }
    }
}
