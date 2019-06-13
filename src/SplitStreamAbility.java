import utils.AnimatedSprite;
/**
 * [SplitStreamAbility.java]
 * pushes all entities in a row away from the center
 * @version 1.0
 * @author Kevin Liu
 * @since June 7, 2019
 */
public class SplitStreamAbility extends AOEAbility{

    /**
     * [SplitStreamAbility]
     * Constructor for an ability that pushes both sides back (players/enemies)
     * @param animation the animation that is played on ability case
     * @param name the displayed name of the ability
     * @param desc the displayed description of the ability
     * @param energyCost the energy cost of the ability
     * @param cooldown the cooldown in turns
     */
    SplitStreamAbility(AnimatedSprite animation, String name, String desc, double energyCost, int cooldown){
        super (animation, name, desc, energyCost, cooldown, 0,2,6,0,0,0,true,true);
    }

    /**
     * action: This method will push players as far left and enemies as far right as possible in a row
     * @param jointMap: The map that will be affected
     * @param x one of the selected coordinates
     * @param y the other selected coordinate
     */
    @Override
    public void action(JointMap jointMap, int x, int y){
        for (int l = x - getXAOE(); l <= x + getXAOE(); l++) {
            if (jointMap.tileExists(l, y)) {
                if (jointMap.isTileFriendly(l, y) && !jointMap.isEmpty(l,y)) {
                    jointMap.moveOnTile(l,y,l-1,y);
                }
                if (!jointMap.isTileFriendly(l, y) && !jointMap.isEmpty(l,y)) {
                    jointMap.moveOnTile(l,y,l+1,y);
                }
            }
        }
        for (int l = x - getXAOE(); l <= x + getXAOE(); l++) {
            if (jointMap.tileExists(l, y)) {
                if (jointMap.isTileFriendly(l, y) && !jointMap.isEmpty(l,y)) {
                    jointMap.moveOnTile(l,y,l-1,y);
                }
                if (!jointMap.isTileFriendly(l, y) && !jointMap.isEmpty(l,y)) {
                    jointMap.moveOnTile(l,y,l+1,y);
                }
            }
        }
    }
}
