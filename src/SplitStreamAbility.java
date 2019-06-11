import utils.AnimatedSprite;

public class SplitStreamAbility extends AOEAbility{
    SplitStreamAbility(AnimatedSprite animation, String name, String desc, double energyCost, int cooldown){
        super (animation, name, desc, energyCost, cooldown, 0,2,6,0,0,0,true,true);
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
        for (int l = i - getXAOE(); l <= i + getXAOE(); l++) {
            if (jointMap.tileExists(l, j)) {
                if (jointMap.isTileFriendly(l, j) && !jointMap.isEmpty(l,j)) {
                    jointMap.moveOnTile(l,j,l-1,j);
                }
                if (!jointMap.isTileFriendly(l, j) && !jointMap.isEmpty(l,j)) {
                    jointMap.moveOnTile(l,j,l+1,j);
                }
            }
        }
        for (int l = i - getXAOE(); l <= i + getXAOE(); l++) {
            if (jointMap.tileExists(l, j)) {
                if (jointMap.isTileFriendly(l, j) && !jointMap.isEmpty(l,j)) {
                    jointMap.moveOnTile(l,j,l-1,j);
                }
                if (!jointMap.isTileFriendly(l, j) && !jointMap.isEmpty(l,j)) {
                    jointMap.moveOnTile(l,j,l+1,j);
                }
            }
        }
    }
}
