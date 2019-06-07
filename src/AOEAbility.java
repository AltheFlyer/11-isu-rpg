import utils.AnimatedSprite;

import java.awt.*;

public class AOEAbility extends Ability{
    AOEAbility(AnimatedSprite animation, String name, String desc, double energyCost, int cooldown, int xRange, int yRange, int xAOE, int yAOE, int status, double damage, boolean enemyTarget, boolean friendTarget){
        super (animation, name, desc, energyCost, cooldown, xRange, yRange, status, damage, enemyTarget, friendTarget);
        setXAOE(xAOE);
        setYAOE(yAOE);
    }
    
    /**
     * action: This method will target and affect the selected tiles in a certain pattern
     * @param jointMap: The map that will be affected
     * @param i: one of the selected coordinates
     * @param j: the other selected coordinate
     * @return: it will return a value based on if an action was valid or not, if it was, it will unindicate everything and reset selectedAbility on levelscreen
     */
    public void action(JointMap jointMap, int i, int j){
        for (int k = j - getYAOE(); k <= j + getYAOE(); k++) {
            for (int l = i - getXAOE(); l <= i + getXAOE(); l++) {
                if (jointMap.tileExists(l, k)) {
                    if (getFriendTarget() && jointMap.getTileType(l, k) == jointMap.getTileType(getEntitySource().getXGrid(), getEntitySource().getYGrid())) {
                        jointMap.target(l, k, getDamage(), getStatus());
                    }
                    if (getEnemyTarget() && jointMap.getTileType(l, k) != jointMap.getTileType(getEntitySource().getXGrid(), getEntitySource().getYGrid())) {
                        jointMap.target(l, k, getDamage(), getStatus());
                    }
                }
            }
        }
    }
    /**
     * [drawSelectedArea]
     * Draws the area that will be affected by an ability
     * @param g the graphics object to draw with
     */
    public void drawHoverAttack(int i, int j, Graphics g, JointMap jointMap) {
        //What to do here???
        int gridX = 323;
        int gridY = 108;

        int gridWidth = 120;
        int gridHeight = 120;

        int gridWidthSpace = 121;
        int gridHeightSpace = 121;
        for (int k = j-getYAOE(); k <= j+getYAOE(); k++){
            for (int l = i-getXAOE(); l <= i+getXAOE(); l++){
                //Yeah might need to revamp Single and AOE ability so one can do empty tiles, one cannot do that
                if (jointMap.tileExists(l,k)){
                    if (getFriendTarget() && jointMap.getTileType(l, k) == jointMap.getTileType(getEntitySource().getXGrid(), getEntitySource().getYGrid())) {
                        g.setColor(Color.GREEN);
                        g.drawRect(gridX + gridWidthSpace * l, gridY + gridHeightSpace * k, gridWidth, gridHeight);
                    }
                    if (getEnemyTarget() && jointMap.getTileType(l, k) != jointMap.getTileType(getEntitySource().getXGrid(), getEntitySource().getYGrid())) {
                        g.setColor(Color.GREEN);
                        g.drawRect(gridX + gridWidthSpace * l, gridY + gridHeightSpace * k, gridWidth, gridHeight);
                    }
                }
            }
        }
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

        indicateValidTileHelper(jointMap, rangeAhead, rangeBehind, rangeDown, rangeUp, true, false);
    }
}
