import utils.AnimatedSprite;

/**
 * [CAPMEnemy]
 * an enemy modeled off of what the Constant Acceleration Particle Model would do if it suddenly became alive
 * @version 1.0
 * @author Allen Liu
 * @since June 9, 2019
 */
public class CAPMEnemy extends Enemy {

    private int attackMod = 0;
    private Icon weakAttackIcon;
    private Icon strongAttackIcon;

    /**
     * [CAPMEnemy]
     * creates a campEnemy at the desired grid position
     * @param x the x position of the grid
     * @param y the y position of the grid
     */
    CAPMEnemy(int x, int y) {
        super(x, y, 125, "CAPM", new AnimatedSprite("spritesheets/CAPM.png", 3, 3, 100),
                new Ability[] {
                        new SingleAbility(null, "Accelerate", "Deals damage to a target and increases attack power.",
                                0, 1, 6, 0, 3, true, false)
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
        setDecide(abilities[0]);
        if (attackMod <= 6) {
            setIntent(weakAttackIcon);
        } else {
            setIntent(strongAttackIcon);
        }
    }

    /**
     * [act]
     * targets a random valid player with an attack, then increases attack power through the power of jank
     * @param map the JointMap that the enemy is on
     */
    @Override
    public void act(JointMap map) {
        selectRandomTile(map, getDecide());
        attackMod += 2;
        abilities[0] = new SingleAbility(null, "Accelerate", "Deals damage to a target and increases attack power.",
                0, 1, 6, 0, 3 + attackMod, true, false);
        abilities[0].setEntitySource(this);
    }
}
