import utils.AnimatedSprite;

/**
 * [ProcessingCloudEnemy.java]
 * An enemy that uses tech-based moves.
 * @version 1.0
 * @author Allen Liu
 * @since June 10, 2019
 */
public class ProcessingCloudEnemy extends Enemy {

    Icon beamIcon;

    Ability deconstructionBeam;

    /**
     *
     * @param x         the x Location on the grid that the enemy is located on
     * @param y         the y location on the grid that the enemy is located
     */
    ProcessingCloudEnemy(int x, int y) {
        super(x, y, 250, 40, 0.2, "Processing Cloud",
                new AnimatedSprite("spritesheets/processing_cloud.png", 2, 6, 100),
                new Ability[] {
                    new SingleAbility(new AnimatedSprite("spritesheets/inflame.png", 1, 2, 600),
                            "Deconstructor", "Deals MASSIVE damage to the nearest player in the row",
                            0, 3, 6, 0, 0, 2, true, false)
        });

        deconstructionBeam = getAbility(0);

        beamIcon = new Icon("assets/icons/beam.png", "Beam", "This enemy intends to do a LOT of damage to a single target.");
    }

    /**
     * [decide]
     * generates a next move (Ability and intent) - that the player can then react to (should be overriden).
     *
     * @param map the map that the entities are in
     */
    @Override
    public void decide(JointMap map) {
        setIntent(beamIcon);
        setDecide(getAbility(0));
    }

    /**
     * [act]
     * Lets the enemy perform an action using an ability.
     * If the Deconstruction Beam is used, it will try to prioritize targets closer to it
     * @param map the map that the entities are in
     */
    @Override
    public void act(JointMap map) {
        if (getDecide().equals(deconstructionBeam)) {
            int closestX = 0;
            for (int x = 0; x < 3; ++x) {
                if (!map.isEmpty(x, getYGrid())) {
                    closestX = x;
                }
            }
            setTargetedX(2);
            setTargetedY(getYGrid());
            getDecide().action(map, closestX, getYGrid());
        }
    }
}
