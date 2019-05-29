import java.awt.*;

public class BasicMoveAbility extends Ability {
    BasicMoveAbility(String name, int moves) {
        super(name, moves);
    }

    /**
     * action: This method will target and affect the selected tiles in a certain pattern
     *
     * @param jointMap: The map that will be affected
     * @param i: one of the selected coordinates
     * @param j: the other selected coordinate
     * @return: it will return a value based on if an action was valid or not, if it was, it will unindicate everything and reset selectedAbility on levelscreen
     */
    public boolean action(JointMap jointMap, int i, int j) {
        if (jointMap.getTargetable(i, j) && jointMap.isEmpty(i, j)) {
            jointMap.moveOnTile(getEntitySource().getXGrid(), getEntitySource().getYGrid(), i, j);
            return true;
        }
        return false;
    }

    /**
     * [drawSelectedArea]
     * Draws the area that will be affected by an ability
     *
     * @param g the graphics object to draw with
     */
    public void drawHoverAttack(int i, int j, Graphics g, JointMap jointMap) {
        drawHoverAttackSingleHelper(i,j,g,jointMap);
    }

    /**
     * This method will indicate and make tiles targetable for you to click on it with your cursor to enact an ability.
     *
     * @param jointMap: The tiles on the jointMap array will be modified (some tiles will be indicated, some will become targetable)
     */
    public void indicateValidTiles(JointMap jointMap) {
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 6; i++) {
                if (Math.abs(getEntitySource().getXGrid() - i) + Math.abs(getEntitySource().getYGrid() - j) <= getMoves() && getEntitySource().isAlive()) {
                    if (jointMap.getTileType(i, j) == jointMap.getTileType(getEntitySource().getXGrid(), getEntitySource().getYGrid())) {
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