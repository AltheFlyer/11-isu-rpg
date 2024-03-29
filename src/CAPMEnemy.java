import utils.AnimatedSprite;

/**
 * [CAPMEnemy]
 * an enemy with constantly increasing attack power
 * @version 1.0
 * @author Allen Liu
 * @since June 9, 2019
 */
public class CAPMEnemy extends Enemy {

    private Icon weakAttackIcon;
    private Icon strongAttackIcon;

    /**
     * [CAPMEnemy]
     * creates a campEnemy at the desired grid position
     * @param x the x position of the grid
     * @param y the y position of the grid
     */
    CAPMEnemy(int x, int y) {
        super(x, y, 150, 20, 0, "CAPM", new AnimatedSprite("spritesheets/CAPM.png", 3, 3, 100),
                new Ability[] {
                        new SingleAbility(null, "Accelerate", "Deals damage to a target and increases attack power.",
                                0, 1, 6, 0, 0,1, true, false)
                });

        weakAttackIcon = new Icon("assets/icons/sword.png", "Basic attack", "This enemy intends to damage a player.");
        strongAttackIcon = new Icon("assets/icons/beam.png", "Strong attack", "This enemy intends to do a lot of damage to a player!");
    }

    /**
     * [decide]
     * generates next move ability and icon, the icon changes based on the attack power of the enemy (think of a ball
     * going so fast it becomes a laser)
     * @param map the JointMap that the enemy is on
     */
    @Override
    public void decide(JointMap map) {
        setDecide(getAbility(0));
        if (getAttack() <= 30) {
            setIntent(weakAttackIcon);
        } else {
            setIntent(strongAttackIcon);
        }
    }

    /**
     * [act]
     * targets a random valid player with an attack, then increases attack power with a buff
     * @param map the JointMap that the enemy is on
     */
    @Override
    public void act(JointMap map) {
        selectRandomTile(map, getDecide());
        inflictStatus(map, new AttackStatModifier(25));
    }
}
