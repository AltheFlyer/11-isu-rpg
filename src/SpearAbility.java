import java.awt.*;

public class SpearAbility extends Ability {


    SpearAbility(String name, double energyCost, double damage) {
        super(name, energyCost, 2, 1, 1, damage, true, false);
    }

    @Override
    public boolean action(JointMap jointMap, int i, int j) {
        if (jointMap.getTargetable(i, j)) {
            jointMap.target(i, j, this.getDamage(), 1);
            return true;
        }
        return false;
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
