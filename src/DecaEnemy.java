import utils.AnimatedSprite;

import java.awt.*;

public class DecaEnemy extends Enemy {

    AnimatedSprite gif;
    Icon intent;

    DecaEnemy(int x, int y) {
        super(1000, "Spirit of Deca", new Ability[] {
                new SingleAbility(new AnimatedSprite("spritesheets/deca.png", 6, 6, 25),
                        "Payment", "Seeks payment from a student.", 0, 0,
                        3, 1, 10, 1, true, false)
        });

        setXGrid(x);
        setYGrid(y);

        gif = new AnimatedSprite("spritesheets/deca.png", 6, 6, 100);

        intent = new Icon("assets/icons/sword.png");

        setIntent(intent);
    }

    @Override
    public void decide(JointMap map) {
        setDecide(abilities[0]);
    }

    @Override
    public void act(JointMap map) {
        selectRandomTile(map, abilities[0]);
    }

    @Override
    public void draw(int x, int y, Graphics g, boolean selected) {
        super.draw(x, y, g, selected);
        gif.draw(g, x, y);
    }
}
