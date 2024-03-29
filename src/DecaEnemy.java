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
    private Icon statusIcon;

    /**
     * [DecaEnemy]
     * creates a Deca enemy at the specified grid space
     * @param x the x coordinate to generate at, should be between 3 and 5 (inclusive)
     * @param y the y coordinate to generate at, should be between 0 and 2 (inclusive)
     */
    DecaEnemy(int x, int y) {
        super(x, y, 200, 15, 0, "Spirit of Deca",
                new AnimatedSprite("spritesheets/deca.png", 6, 6, 100),
                new Ability[] {
                    new AbilityPair(new AnimatedSprite("spritesheets/deca.png", 6, 6, 25),"Decadent strike", "Pay the toll.", 0, 0,
                            new SingleAbility(null,
                            "Payment", "Seeks payment from a student.", 0, 0,
                            3, 1, 0,1, true, false),
                            new StatusAbility(new CursedStatus(6), null,
                                    "Crippling Debt!", "Deca deca deca...", 0, 1, 6, 6, true, false)
                    ),
                    new StatusAbility(new CursedStatus( 15), new AnimatedSprite("spritesheets/deca.png", 6, 6, 25),
                                        "Crippling Debt!", "Deca deca deca...", 0, 1, 6, 6, true, false)
            });
        attackIcon = new Icon("assets/icons/sword.png", "Greed", "This enemy intends to deal damage to a player.");
        statusIcon = new Icon("assets/icons/test.png", "Indebt", "This enemy intends place a player into debt");
    }

    /**
     * [decide]
     * generates an intent and ability for Deca to use next turn using random selection
     * @param map the jointmap that Deca is on
     */
    @Override
    public void decide(JointMap map) {
        if (Math.random() < 0.5) {
            setIntent(attackIcon);
            setDecide(getAbility(0));
        } else {
            setIntent(statusIcon);
            setDecide(getAbility(1));
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
