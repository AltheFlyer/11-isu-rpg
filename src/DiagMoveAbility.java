import java.awt.Graphics;

public class DiagMoveAbility extends MoveAbility{
    /**
     * [DiagMoveAbility]
     * Constructor for movement
     * @param name the displayed name of the ability
     * @param desc the displayed description of the ability
     * @param energyCost the energy cost of the ability
     * @param cooldown the cooldown in turns
     * @param moves the amount of moves that an entity can take
     */
    DiagMoveAbility(String name, String desc, double energyCost, int cooldown, int moves) {
        super(name, desc, energyCost, cooldown, moves);
    }

    /**
     * action: This method will target and affect the selected tiles in a certain pattern
     * @param jointMap: The map that will be affected
     * @param i: one of the selected coordinates
     * @param j: the other selected coordinate
     * @return: it will return a value based on if an action was valid or not, if it was, it will unindicate everything and reset selectedAbility on levelscreen
     */
    public void action(JointMap jointMap, int i, int j) {
        if (jointMap.isEmpty(i, j)) {
            jointMap.moveOnTile(getEntitySource().getXGrid(), getEntitySource().getYGrid(), i, j);
        }
    }

    /**
     * [drawHoverAttack]
     * Draws the area that will be affected by an ability
     * @param i the x of the tile that the mouse is hovered over
     * @param j the y of the tile that the mouse is hovered over
     * @param g the graphics object to draw with
     * @param jointMap map to draw the hovered square on
     */
    public void drawHoverAttack(int i, int j, Graphics g, JointMap jointMap) {
        drawHoverAttackSingleHelper(i,j,g,jointMap);
    }

    /**
     * This method will indicate and make tiles targetable for you to click on it with your cursor to enact an ability.
     * @param jointMap: The tiles on the jointMap array will be modified (some tiles will be indicated, some will become targetable)
     */
    public void indicateValidTiles(JointMap jointMap) {
        if ((getEntitySource().getEnergy() >= getEnergyCost()) && (getCurrentCooldown() <= 0)) {
            for (int j = 0; j < 3; j++) {
                for (int i = 0; i < 6; i++) {
                    if ((Math.abs(getEntitySource().getXGrid() - i) == getMoves()) && (Math.abs(getEntitySource().getYGrid() - j) == getMoves())) {
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
