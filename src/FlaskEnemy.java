import utils.AnimatedSprite;

public class FlaskEnemy extends Enemy {

    FlaskEnemy(int x, int y) {
        super(x, y, 75, "Walking Flask", new AnimatedSprite("spritesheets/flask.png", 2, 6, 150),
                new Ability[] {
                new StatusAbility(
                        new CursedStatus(1), null,
                        "Fume", "Inflicts 'Curse' on all players.",
                        0, 2, 3, 3, true, false
                )
        });
    }

    @Override
    public void decide(JointMap map) {

    }

    @Override
    public void act(JointMap map) {

    }
}
