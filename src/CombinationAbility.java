import java.awt.*;

public class CombinationAbility extends Ability {
    CombinationAbility(String name, int xRange, int yRange, int status, double damage, boolean enemyTarget, boolean friendTarget){
        super (name, xRange, yRange, status, damage, enemyTarget, friendTarget);
        setXAOE(0);
        setYAOE(0);
    }

    /**
     * action: This method will target and affect the selected tiles in a certain pattern
     * @param jointMap: The map that will be affected
     * @param i: one of the selected coordinates
     * @param j: the other selected coordinate
     * @return: it will return a value based on if an action was valid or not, if it was, it will unindicate everything and reset selectedAbility on levelscreen
     */
    public boolean action(JointMap jointMap, int i, int j){
        if (jointMap.getIndication(i, j) && !jointMap.isEmpty(i, j)) {
            for (int k = j - getYAOE(); k <= j + getYAOE(); k++) {
                for (int l = i - getXAOE(); l <= i + getXAOE(); l++) {
                    if (jointMap.tileExists(l, k)) {
                        jointMap.target(l, k, getDamage(), getStatus());
                        jointMap.moveOnTile(getEntitySource().getXGrid(),getEntitySource().getYGrid(),getEntitySource().getXGrid()-1,getEntitySource().getYGrid());
                    }
                }
            }
            return true;
        }
        return false;
    }
    /**
     * [drawSelectedArea]
     * Draws the area that will be affected by an ability
     * @param g the graphics object to draw with
     */
    public void drawHoverAttack(int i, int j, Graphics g, JointMap jointMap) {
        drawHoverAttackSingleHelper(i,j,g,jointMap);
    }

    /**
     * This method will indicate and make tiles targetable for you to click on it with your cursor to enact an ability.
     * @param jointMap: The tiles on the jointMap array will be modified (some tiles will be indicated, some will become targetable)
     */
    public void indicateValidTiles(JointMap jointMap){
        int rangeAhead = getEntitySource().getXGrid() + getXRange();
        int rangeBehind = getEntitySource().getXGrid() - getXRange();
        int rangeDown = getEntitySource().getYGrid() + getYRange();
        int rangeUp = getEntitySource().getYGrid() - getYRange();

        indicateValidTileHelper(jointMap, rangeAhead, rangeBehind, rangeDown, rangeUp, false,false);
    }
}
