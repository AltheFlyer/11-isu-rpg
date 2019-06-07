import utils.AnimatedSprite;

import java.awt.*;

public class SpearAbility extends Ability {


    SpearAbility(AnimatedSprite animation, String name, String desc, double energyCost, int cooldown, double damage) {
        super(animation, name, desc, energyCost, cooldown, 2, 1, 1, damage, true, false);
    }

    @Override
    public void action(JointMap jointMap, int i, int j) {
        jointMap.target(i, j, this.getDamage(), 1);
    }

    @Override
    public void drawHoverAttack(int i, int j, Graphics g, JointMap jointMap) {
        drawHoverAttackSingleHelper(i,j,g,jointMap);
    }

    @Override
    public void indicateValidTiles(JointMap jointMap) {
        int rangeAhead = getEntitySource().getXGrid() + 2;
        int rangeBehind = getEntitySource().getXGrid();
        int rangeDown = getEntitySource().getYGrid();
        int rangeUp = getEntitySource().getYGrid();

        indicateValidTileHelper(jointMap, rangeAhead, rangeBehind, rangeDown, rangeUp, false, true);
    }
}
