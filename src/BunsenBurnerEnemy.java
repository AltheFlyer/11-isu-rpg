import utils.AnimatedSprite;

/**
 * [BunsenBurnerEnemy.java]
 * An enemy that burns players.
 * @version 1.0
 * @author Allen Liu
 * @since June 6, 2019
 */
public class BunsenBurnerEnemy extends Enemy {

    private Icon attackIcon;

    /**
     * [BunsenBurnerEnemy]
     * Creates a bunsen burner at the specified grid position
     * @param x the x grid position to spawn at
     * @param y the y grid position to spawn at
     */
    BunsenBurnerEnemy(int x, int y) {
        super(x, y, 200, 30, 0,"Bunsen Burner",
                new AnimatedSprite("spritesheets/bunsen.png", 1, 10, 100),
                new Ability[] {
                    new AOEAbility(new AnimatedSprite("spritesheets/inflame.png", 1, 2, 600),"Inflame", "Burns all targets in the row.", 0, 1,
                            6, 0, 6, 0, 0,1, true, false)
        });

        attackIcon = new Icon("assets/icons/sword.png", "Inflame", "This enemy intends to attack all players in the row.");
    }

    /**
     * [decide]
     * generates a decision, which will always be to use the 'inflame' attack
     * @param map the map that the entities are in
     */
    @Override
    public void decide(JointMap map) {
        setIntent(attackIcon);
        setDecide(getAbility(0));
    }

    /**
     * [act]
     * allos the enemy to act, which will always trigger the 'inflame' ability
     * @param map the map that the entities are in
     */
    @Override
    public void act(JointMap map) {
        getDecide().indicateValidTiles(map);
        getDecide().action(map, 2, getYGrid());

        setTargetedX(2);
        setTargetedY(getYGrid());
    }

}
