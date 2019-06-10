import utils.AnimatedSprite;

/**
 * [DecaEnemy]
 * An enemy that is a living, rotating DECA logo. It primarily uses debuff attacks to do damage over time.
 * @version 1.1
 * @author Allen Liu
 * @since June 9, 2019
 */
public class DecaEnemy extends Enemy {

    private Icon attackIcon;

    /**
     * [DecaEnemy]
     * creates a Deca enemy at the specified grid space
     * @param x the x coordinate to generate at, should be between 3 and 5 (inclusive)
     * @param y the y coordinate to generate at, should be between 0 and 2 (inclusive)
     */
    DecaEnemy(int x, int y) {
        super(x, y, 100, "Spirit of Deca",
                new AnimatedSprite("spritesheets/deca.png", 6, 6, 100),
                new Ability[] {
                    new AbilityPair(new AnimatedSprite("spritesheets/deca.png", 6, 6, 25),"Decadent strike", "Pay the toll.", 0, 0,
                            new SingleAbility(null,
                            "Payment", "Seeks payment from a student.", 0, 0,
                            3, 1, 10, true, false),
                            new StatusAbility(new CursedStatus(1), null,
                                    "Crippling Debt!", "Deca deca deca...", 0, 1, 6, 6, true, false)
                    ),
                    new StatusAbility(new CursedStatus( 4), new AnimatedSprite("spritesheets/deca.png", 6, 6, 25),
                                        "Crippling Debt!", "Deca deca deca...", 0, 1, 6, 6, true, false)
            });
        attackIcon = new Icon("assets/icons/sword.png", "Greed", "This enemy intends to deal damage to a player.");
    }

    /**
     * [decide]
     * generates an intent and ability for Deca to use next turn using random selection
     * @param map the jointmap that Deca is on
     */
    @Override
    public void decide(JointMap map) {
        setIntent(attackIcon);
        if (Math.random() < 0.5) {
            setDecide(abilities[1]);
        } else {
            setDecide(abilities[1]);
        }
    }

    /**
     * [act]
     * uses the ability that was chosen previously in .decide(JointMap map) and casts it on a random target
     * @param map the JointMap that Deca is currently on
     */
    @Override
    public void act(JointMap map) {
        selectRandomTile(map, getDecide());
    }

}
