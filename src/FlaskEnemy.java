import utils.AnimatedSprite;

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

    @Override
    public void decide(JointMap map) {
        setDecide(abilities[0]);
        setIntent(toxicCloud);
    }

    @Override
    public void act(JointMap map) {
        selectRandomTile(map, getDecide());
    }
}
