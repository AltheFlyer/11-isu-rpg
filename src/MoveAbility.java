import java.awt.Graphics;

/**
 * [MoveAbility]
 * Abstract class for any ability that moves entities based on a number of moves/distance, where distance matters.
 * This is NOT necessary for abilities with fixed movements (e.g always moves one tile back).
 * @version 1.0
 * @author Allen Liu
 * @since May 10, 2019
 */
abstract public class MoveAbility extends Ability {

    int moves;

    /**
     * [Ability]
     * Constructor for movement abilities
     *
     * @param name       the displayed name of the ability
     * @param desc       the displayed description of the ability
     * @param energyCost the energy cost of the ability
     * @param cooldown   the cooldown in turns
     * @param moves      the distance that can be moved, in tiles based on manhattan distance
     */
    MoveAbility(String name, String desc, double energyCost, int cooldown, int moves) {
        super(name, desc, energyCost, cooldown);
        this.moves = moves;
    }

    /**
     * [getMoves]
     * gets the amount of moves a movement ability can use
     */
    public int getMoves() {
        return moves;
    }

    /**
     * [drawInfoBox]
     * Draws the default ability text box, but also draws movements allowed.
     * @param g the graphics object to draw with
     * @param x the x position to draw from (top left corner)
     * @param y the y position to draw from (top left corner)
     */
    @Override
    public void drawInfoBox(Graphics g, int x, int y) {
        super.drawInfoBox(g, x, y);
        g.drawString("Moves: " + moves, x + 110, 54 + y);
        g.drawRect(x + 100,37+y,163,22);
    }
}
