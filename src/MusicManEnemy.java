import utils.AnimatedSprite;

/**
 * [MusicManEnemy.java]
 * a music themed enemy
 * @version 1.0
 * @author Allen Liu
 * @since June 12, 2019
 */
public class MusicManEnemy extends Enemy {

    Icon attackIcon;

    /**
     * Creates an enemy entity which doesn't need energy
     *
     * @param x         the x Location on the grid that the enemy is located on
     * @param y         the y location on the grid that the enemy is located
     */
    MusicManEnemy(int x, int y) {
        super(x, y, 440, 20, 0, "Music Man",
                new AnimatedSprite("spritesheets/music_man.png", 2, 7, 100),
                new Ability[] {
                        new SingleAbility(new AnimatedSprite("spritesheets/blast.png", 1, 3, 250),
                                "Blast", "Deals damage to a player.", 0, 0,
                                4, 2, 0, 1, true, false),
                        new AbilityPair(new AnimatedSprite("spritesheets/blast.png", 1, 3, 250),
                                "Sforzando", "Deals massive damage to a row, but decreases attack power.",
                                0, 3,
                                new AOEAbility(null, "", "", 0, 0, 6, 2,
                                        3, 0, 0, 2, true, false),
                                new StatusAbility(new AttackStatModifier(-20), null, "", "", 0, 0,
                                        0, 0, false, true)
                        )
                });

        attackIcon = new Icon("assets/icons/sword.png", "Attack", "This enemy intends to attack.");
    }

    /**
     * [decide]
     * generates a next move (Ability and intent) - that the player can then react to (should be overriden).
     *
     * @param map the map that the entities are in
     */
    @Override
    public void decide(JointMap map) {
        if (getAbility(1).getCurrentCooldown() == 0) {
            setDecide(getAbility(1));
        } else {
            setDecide(getAbility(0));
        }
        setIntent(attackIcon);
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
        getDecide().resetCooldown();
    }
}
