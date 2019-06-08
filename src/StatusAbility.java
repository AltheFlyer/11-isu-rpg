import utils.AnimatedSprite;

import java.awt.*;

public class StatusAbility extends Ability {

    StatusEffect effect;

    public StatusAbility(StatusEffect effect, AnimatedSprite animation, String name, String desc, double energyCost, int cooldown, int xRange, int yRange, boolean enemyTarget, boolean friendTarget) {
        super(animation, name, desc, energyCost, cooldown, xRange, yRange, 0, enemyTarget, friendTarget);

        this.effect = effect;
    }


    @Override
    public void action(JointMap jointMap, int i, int j) {
        effect.setAffected(jointMap.getEntity(i, j));
        jointMap.getEntity(i, j).addStatus(effect.spread());
    }

    @Override
    public void drawHoverAttack(int i, int j, Graphics g, JointMap jointMap) {
        drawHoverAttackSingleHelper(i, j, g, jointMap);
    }

    @Override
    public void indicateValidTiles(JointMap jointMap) {
        int rangeAhead = getEntitySource().getXGrid() + getXRange();
        int rangeBehind = getEntitySource().getXGrid() - getXRange();
        int rangeDown = getEntitySource().getYGrid() + getYRange();
        int rangeUp = getEntitySource().getYGrid() - getYRange();

        indicateValidTileHelper(jointMap, rangeAhead, rangeBehind, rangeDown, rangeUp, false, false);
    }
}
