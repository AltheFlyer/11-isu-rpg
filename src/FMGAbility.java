import utils.AnimatedSprite;

import java.awt.*;

public class FMGAbility extends AOEAbility{

    /**
     * [FMGAbility]
     * Constructor for aoe abilities
     * @param animation the animation that is played on ability case
     * @param name the displayed name of the ability
     * @param desc the displayed description of the ability
     */
    FMGAbility(AnimatedSprite animation, String name, String desc){
        super (animation, name, desc, 40, 2, 0, 0, 6,2, 0,1, true, true);
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
        for (int l = j - getYAOE(); l <= j + getYAOE(); l++) {
            for (int k = i - getXAOE(); k <= i + getXAOE(); k++) {
                if (jointMap.tileExists(k, l)) {
                    if (!jointMap.isEmpty(k,l)) {
                        if ((getFriendTarget()) && (jointMap.isTileFriendly(k, l) == getEntitySource().isFriendly()) && (getIsMarked(jointMap, k, l))) {
                            jointMap.target(k, l, -getDamage());
                        }
                        if ((getEnemyTarget()) && (jointMap.isTileFriendly(k, l) != getEntitySource().isFriendly()) && (getIsMarked(jointMap, k, l))) {
                            System.out.println("help");
                            jointMap.target(k, l, getDamage());
                        }
                    }
                }
            }
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
        int gridX = 323;
        int gridY = 108;

        int gridWidth = 120;
        int gridHeight = 120;

        int gridWidthSpace = 121;
        int gridHeightSpace = 121;
        for (int l = j-getYAOE(); l <= j+getYAOE(); l++){
            for (int k = i-getXAOE(); k <= i+getXAOE(); k++){
                //Yeah might need to revamp Single and AOE ability so one can do empty tiles, one cannot do that
                if (jointMap.tileExists(k,l)){
                    if (!jointMap.isEmpty(k,l)) {
                        if (getIsMarked(jointMap, k, l)) {
                            g.setColor(Color.GREEN);
                            g.drawRect(gridX + gridWidthSpace * k, gridY + gridHeightSpace * l, gridWidth, gridHeight);
                        }
                    }
                }
            }
        }
    }
}
