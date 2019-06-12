import utils.AnimatedSprite;

import java.awt.*;

public class KevinAOEDissectAbility extends AOEAbility{

    /**
     * [KevinSpotlightAbility]
     * generates a StatusAbility with single target capabilities
     * @param animation the animation that is played on ability case
     * @param name the displayed name of the ability
     * @param desc the displayed description of the ability
     */
    KevinAOEDissectAbility(AnimatedSprite animation, String name, String desc){
        super (animation, name, desc, 60, 0, 3,0, 2,0, 0,0, true, false);
    }

    /**
     * action: This method will target and affect the selected tiles in a certain pattern
     * @param jointMap: The map that will be affected
     * @param i one of the selected coordinates
     * @param j the other selected coordinate
     * @return it will return a value based on if an action was valid or not, if it was, it will unindicate everything and reset selectedAbility on levelscreen
     */
    @Override
    public void action(JointMap jointMap, int i, int j){
        for (int l = j - getYAOE(); l <= j + getYAOE(); l++) {
            for (int k = i - getXAOE(); k <= i + getXAOE(); k++) {
                if (jointMap.tileExists(k,l)) {
                    if (!jointMap.isTileFriendly(k,l) && !jointMap.isEmpty(k,l)) {
                        jointMap.inflictStatus(k,l,new DissectedStatus(4));
                    }
                }
            }
        }
    }

    /**
     * This method will indicate and make tiles targetable for you to click on it with your cursor to enact an ability.
     * @param jointMap The tiles on the jointMap array will be modified (some tiles will be indicated, some will become targetable)
     */
    @Override
    public void indicateValidTiles(JointMap jointMap){
        if (getEntitySource().getEnergy() == 60) {
            jointMap.indicate(3,getEntitySource().getYGrid());
            jointMap.isTargetable(3,getEntitySource().getYGrid());
        }
    }
}
