import utils.AnimatedSprite;

/**
 * [MusicManEnemy.java]
 * a music themed enemy
 * @version 1.0
 * @author Allen Liu
 * @since June 12, 2019
 */
public class MusicManEnemy extends Enemy {

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
                                4, 2, 0, 1, true, false)
                });
    }

    /**
     * [decide]
     * generates a next move (Ability and intent) - that the player can then react to (should be overriden).
     *
     * @param map the map that the entities are in
     */
    @Override
    public void decide(JointMap map) {
        setDecide(getAbility(0));
    }

    /**
     * [act]
     * lets the enemy perform an action using an ability
     *
     * @param map the map that the entities are in
     */
    @Override
    public void act(JointMap map) {

    }
}
