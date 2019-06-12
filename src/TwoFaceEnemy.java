import utils.AnimatedSprite;

import java.awt.*;

public class TwoFaceEnemy extends Enemy {

    private AnimatedSprite happyFace;
    private AnimatedSprite sadFace;

    private int hits;

    private Ability happyOrb;
    private Ability sadOrb;

    private Icon attackIcon;

    /**
     * Creates an enemy entity which doesn't need energy
     * @param x         the x Location on the grid that the enemy is located on
     * @param y         the y location on the grid that the enemy is located
     */
    TwoFaceEnemy(int x, int y) {
        super(x, y, 500, 30, 0, "Two Face", new AnimatedSprite("spritesheets/happy_mask.png", 1, 7, 50),
                new Ability[] {
                        new FirstInRowAbility(new AnimatedSprite("spritesheets/red_orb.png", 1, 3, 100),
                        "Happy orb", "Deals damage to one target in the row. Only usable while happy.",
                                0, 0, 10, 1, true, false
                        ),
                        new FirstInRowAbility(new AnimatedSprite("spritesheets/blue_orb.png", 1, 3, 100),
                                "Sad orb", "Deals damage to one target in the row. Only usable while sad.",
                                0, 0, 20, 1, true, false
                        )

                });

        happyOrb = getAbility(0);
        sadOrb = getAbility(1);

        happyFace = new AnimatedSprite("spritesheets/happy_mask.png", 1, 7, 50);
        sadFace = new AnimatedSprite("spritesheets/sad_mask.png", 1, 7, 75);

        attackIcon = new Icon("assets/icons/sword.png", "Attack", "This enemy intends to attack a player.");

        hits = 0;
    }

    /**
     * [decide]
     * generates a next move (Ability and intent) - that the player can then react to (should be overriden).
     *
     * @param map the map that the entities are in
     */
    @Override
    public void decide(JointMap map) {
        if (hits >= 10) {
            setDecide(sadOrb);
            setIntent(attackIcon);
        } else {
            setDecide(happyOrb);
            setIntent(attackIcon);
        }
    }

    /**
     * [act]
     * lets the enemy perform an action using an ability
     *
     * @param map the map that the entities are in
     */
    @Override
    public void act(JointMap map) {
        selectRandomTile(map, getDecide());
    }

    @Override
    public void damageEntity(double damage) {
        super.damageEntity(damage);
        hits++;
        if (hits == 10) {
            setAnimation(sadFace);
            setDefense(0.3);
        }
    }
}
