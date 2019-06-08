import utils.AnimatedSprite;

import java.awt.*;

/**
 * [AbilityPair]
 * An attempt at making a general form for two abilities to act as one. Theoretically (horrifically) recursive
 * @version 1.0
 * @author Allen Liu
 * @since June 7, 2019
 */
public class AbilityPair extends Ability {

    Ability firstAbility;
    Ability secondAbility;

    public AbilityPair(AnimatedSprite animation, String name, String desc, int energyCost, int cooldown, Ability firstAbility, Ability secondAbility) {
        super(name, desc, energyCost, cooldown, 0);
        this.firstAbility = firstAbility;
        this.secondAbility = secondAbility;

        setAnimation(animation);
    }

    @Override
    public void action(JointMap jointMap, int i, int j) {
        firstAbility.action(jointMap, i, j);
        secondAbility.action(jointMap, i, j);
    }

    @Override
    public void drawHoverAttack(int i, int j, Graphics g, JointMap jointMap) {
        firstAbility.drawHoverAttack(i, j, g, jointMap);
        secondAbility.drawHoverAttack(i, j, g, jointMap);
    }

    @Override
    public void indicateValidTiles(JointMap jointMap) {
        firstAbility.indicateValidTiles(jointMap);
        secondAbility.indicateValidTiles(jointMap);
    }

    @Override
    public void setEntitySource(Entity e) {
        firstAbility.setEntitySource(e);
        secondAbility.setEntitySource(e);
    }

}
