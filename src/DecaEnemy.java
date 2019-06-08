import utils.AnimatedSprite;

import java.awt.*;

public class DecaEnemy extends Enemy {

    AnimatedSprite gif;
    Icon intent;

    DecaEnemy(int x, int y) {
        super(100, "Spirit of Deca", new Ability[] {
                new AbilityPair(new AnimatedSprite("spritesheets/deca.png", 6, 6, 25),"Decadent strike", "Pay the toll.", 0, 0,
                        new SingleAbility(null,
                        "Payment", "Seeks payment from a student.", 0, 0,
                        3, 1, 10, 1, true, false),
                        new StatusAbility(new CursedStatus(null, 1), null,
                                "Crippling Debt!", "Deca deca deca...", 0, 1, 6, 6, true, false)
                ),
                new AbilityPair(new AnimatedSprite("spritesheets/deca.png", 6, 6, 25),"Decadent strike", "Pay the toll.", 0, 0,
                    new AbilityPair(new AnimatedSprite("spritesheets/deca.png", 6, 6, 25),"Decadent strike", "Pay the toll.", 0, 0,
                            new StatusAbility(new CursedStatus(null, 1), null,
                                    "Crippling Debt!", "Deca deca deca...", 0, 1, 6, 6, true, false),
                            new StatusAbility(new CursedStatus(null, 1), null,
                                    "Crippling Debt!", "Deca deca deca...", 0, 1, 6, 6, true, false)
                    ), new AbilityPair(new AnimatedSprite("spritesheets/deca.png", 6, 6, 25),"Decadent strike", "Pay the toll.", 0, 0,
                            new StatusAbility(new CursedStatus(null, 1), null,
                                    "Crippling Debt!", "Deca deca deca...", 0, 1, 6, 6, true, false),
                            new StatusAbility(new CursedStatus(null, 1), null,
                                    "Crippling Debt!", "Deca deca deca...", 0, 1, 6, 6, true, false)
                    ))
        });

        setXGrid(x);
        setYGrid(y);

        gif = new AnimatedSprite("spritesheets/deca.png", 6, 6, 100);

        intent = new Icon("assets/icons/sword.png", "Payment", "Deca intends to take something from a student...");

        setIntent(intent);
    }

    @Override
    public void decide(JointMap map) {
        if (Math.random() < 0.5) {
            setDecide(abilities[0]);
        } else {
            setDecide(abilities[1]);
        }
    }

    @Override
    public void act(JointMap map) {
        selectRandomTile(map, getDecide());
    }

    @Override
    public void draw(int x, int y, Graphics g, boolean selected) {
        super.draw(x, y, g, selected);
        gif.draw(g, x, y);
    }
}
