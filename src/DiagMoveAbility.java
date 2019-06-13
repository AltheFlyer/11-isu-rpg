import java.awt.Graphics;
/**
 * [DiagMoveAbility.java]
 * Creating a diagonal movement for Jasmine the floutist
 * @version 1.1
 * @author Kevin Liu
 * @since June 7, 2019
 */
public class DiagMoveAbility extends MoveAbility{
    /**
     * [DiagMoveAbility]
     * Constructor for diagonal movement
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
     * action: This method will move an entoty to the selected tile
     * @param jointMap: The map that will be affected
     * @param x: one of the selected coordinates
     * @param y: the other selected coordinate
     */
    public void action(JointMap jointMap, int x, int y) {
        if (jointMap.isEmpty(x, y)) {
            jointMap.moveOnTile(getEntitySource().getXGrid(), getEntitySource().getYGrid(), x, y);
        }
    }

    /**
     * [drawHoverAttack]
     * Draws the area that will be affected by an ability
     * @param x the x of the tile that the mouse is hovered over
     * @param y the y of the tile that the mouse is hovered over
     * @param g the graphics object to draw with
     * @param jointMap map to draw the hovered square on
     */
    public void drawHoverAttack(int x, int y, Graphics g, JointMap jointMap) {
        drawHoverAttackSingleHelper(x,y,g,jointMap);
    }

    /**
     * [indicateValidTiles]
     * This method will indicate and make tiles targetable for you to click on it with your cursor to enact an ability.
     * @param jointMap: The tiles on the jointMap array will be modified (some tiles will be indicated, some will become targetable)
     */
    public void indicateValidTiles(JointMap jointMap) {
        if ((getEntitySource().getEnergy() >= getEnergyCost()) && (getCurrentCooldown() <= 0)) {
            for (int y = 0; y < 3; y++) {
                for (int x = 0; x < 6; x++) {
                    if ((Math.abs(getEntitySource().getXGrid() - x) == getMoves()) && (Math.abs(getEntitySource().getYGrid() - y) == getMoves())) {
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
