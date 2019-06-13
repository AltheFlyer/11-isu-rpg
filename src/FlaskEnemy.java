import utils.AnimatedSprite;

/**
 * [FlaskEnemy.java]
 * A flask enemy with short range that inflicts curse on players
 * @verion 1.1
 * @author Allen Liu & Kevin Liu
 * @since June 12, 2019
 */
public class FlaskEnemy extends Enemy {

    private Icon toxicCloud;

    FlaskEnemy(int x, int y) {
        super(x, y, 150, 20, 0, "Walking Flask", new AnimatedSprite("spritesheets/flask.png", 2, 6, 150),
                new Ability[] {
                new StatusAbility(
                        new CursedStatus(8), new AnimatedSprite("spritesheets/toxic_cloud.png", 1, 5, 75),
                        "Fume", "Inflicts 'Curse' on one player.",
                        0, 2, 4, 3, true, false
                )
        });

        toxicCloud = new Icon("assets/icons/toxic_cloud.png", "Toxic", "This enemy intends to debuff a player.");
    }

    /**
     * [decide]
     * lets the flask choose to debuff a random player
     * @param map the map that the entities are in
     */
    @Override
    public void decide(JointMap map) {
        setDecide(getAbility(0));
        setIntent(toxicCloud);
    }

    /**
     * [act]
     * lets the flask inflict curse on a player
     * @param map the map that the entities are in
     */
    @Override
    public void act(JointMap map) {
        selectRandomTile(map, getDecide());
    }
}
