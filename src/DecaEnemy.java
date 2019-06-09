import utils.AnimatedSprite;

import java.awt.*;

public class DecaEnemy extends Enemy {

    Icon attackIcon;

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
                    new StatusAbility(new CursedStatus( 4), null,
                                        "Crippling Debt!", "Deca deca deca...", 0, 1, 6, 6, true, false)
            });
        attackIcon = new Icon("assets/icons/sword.png", "Greed", "This enemy intends to deal damage to a player.");
    }

    @Override
    public void decide(JointMap map) {
        setIntent(attackIcon);
        if (Math.random() < 0.5) {
            setDecide(abilities[1]);
        } else {
            setDecide(abilities[1]);
        }
    }

    @Override
    public void act(JointMap map) {
        selectRandomTile(map, getDecide());
    }

}
