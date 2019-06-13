import utils.AnimatedSprite;

import java.awt.Graphics;

/**
 * [EnergyAbility.java]
 * An energy replenishing ability
 * @version 1.0
 * @author Kevin Liu
 * @since June 11, 2019
 */
public class EnergyAbility extends DamagingAbility{
    private double energyGain;

    /**
     * [EnergyAbility]
     * Constructor for abilities that modify energy
     * @param animation the animation that is played on ability case
     * @param name the displayed name of the ability
     * @param desc the displayed description of the ability
     * @param energyCost the energy cost of the ability
     * @param cooldown the cooldown in turns
     * @param xRange the range in the x axis in tiles
     * @param yRange the range in the y axis in tiles
     * @param damage the amount of damage that the ability will do
     */
    EnergyAbility(AnimatedSprite animation, String name, String desc, double energyCost, int cooldown, int xRange, int yRange, double damage, double energyGain) {
        super(animation, name, desc, energyCost,cooldown, xRange,yRange,damage,0, false, true);
        this.energyGain = energyGain;
    }

    /**
     * action: This method will target and potentially damage an entity while giving it energy
     * @param jointMap: The map that will be affected
     * @param x one of the selected coordinates
     * @param y the other selected coordinate
     */
    public void action(JointMap jointMap, int x, int y){
        jointMap.getEntity(x,y).gainEnergy(energyGain);
        jointMap.target(x, y, getDamage());
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
    public void indicateValidTiles(JointMap jointMap){
        int rangeAhead = getEntitySource().getXGrid() + getXRange();
        int rangeBehind = getEntitySource().getXGrid() - getXRange();
        int rangeDown = getEntitySource().getYGrid() + getYRange();
        int rangeUp = getEntitySource().getYGrid() - getYRange();

        indicateValidTileHelper(jointMap, rangeAhead, rangeBehind, rangeDown, rangeUp, false, false);
    }
}
