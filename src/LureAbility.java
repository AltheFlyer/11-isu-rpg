import utils.AnimatedSprite;

import java.awt.*;

public class LureAbility extends DamagingAbility {
    LureAbility(AnimatedSprite animation, String name, String desc, double energyCost, int cooldown, int xRange, int yRange, double damage, double ratio) {
        super(animation, name, desc, energyCost,cooldown, xRange,yRange,damage, ratio, true, false);
    }

    public void action(JointMap jointMap, int i, int j){
        jointMap.target(i, j, getDamage());

        if (getEntitySource() instanceof Player) {
            jointMap.moveOnTile(i, j, i - 1, j);
        } else if (getEntitySource() instanceof Enemy) {
            jointMap.moveOnTile(i, j, i + 1, j);
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
        //What to do here???
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

        indicateValidTileHelper(jointMap, rangeAhead, rangeBehind, rangeDown, rangeUp, false, false);
    }
}
