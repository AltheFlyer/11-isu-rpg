import utils.AnimatedSprite;

import java.awt.Graphics;

/**
 * [ProcessingCloudEnemy.java]
 * An enemy that uses tech-based moves.
 * @version 1.0
 * @author Allen Liu
 * @since June 10, 2019
 */
public class ProcessingCloudEnemy extends Enemy {

    Icon beamIcon;
    Icon medicIcon;
    Icon moveIcon;

    Ability deconstructionBeam;
    Ability constructorAbility;
    Ability seek;
    Ability protoCannon;

    /**
     * @param x         the x Location on the grid that the enemy is located on
     * @param y         the y location on the grid that the enemy is located
     */
    ProcessingCloudEnemy(int x, int y) {
        super(x, y, 250, 40, 0.2, "Processing Cloud",
                new AnimatedSprite("spritesheets/processing_cloud.png", 2, 6, 100),
                new Ability[] {
                    new AOEAbility(new AnimatedSprite("spritesheets/inflame.png", 1, 2, 600),
                            "Deconstructor", "Deals MASSIVE damage to all players in the row",
                            0, 3, 6, 0, 3, 0, 0, 2, true, false),
                    new ConstructorAbility(),
                    new BasicMoveAbility("Seek", "Moves to a row with players.",
                            0, 2,2),
                        new FirstInRowAbility(new AnimatedSprite("spritesheets/back.png", 1, 3, 400),
                                "Proto Cannon", "Hits the nearest enemy in the row.",
                                0, 0, 0, 1, true, false)
                        /*
                    new SingleAbility(new AnimatedSprite("spritesheets/back.png", 1, 3, 400),
                            "Proto Cannon", "Hits the nearest enemy in the row.",
                            0, 0, 6, 0, 0, 1, true, false)

                         */

        });

        deconstructionBeam = getAbility(0);
        constructorAbility = getAbility(1);
        seek = getAbility(2);
        protoCannon = getAbility(3);

        beamIcon = new Icon("assets/icons/beam.png", "Beam", "This enemy intends to attack.");
        medicIcon = new Icon("assets/icons/medic.png", "Reset", "This enemy will heal and refresh ALL cooldowns.");
        moveIcon = new Icon("assets/icons/move.png", "Seek", "This enemy intents to move to a row with players.");
    }

    /**
     * [decide]
     * generates a next move (Ability and intent) - that the player can then react to (should be overriden).
     *
     * @param map the map that the entities are in
     */
    @Override
    public void decide(JointMap map) {
        if ((getHealth() < getMaxHealth() * 0.4) && (constructorAbility.getCurrentCooldown() == 0)) {
            setIntent(medicIcon);
            setDecide(constructorAbility);
        } else if (deconstructionBeam.getCurrentCooldown() == 0) {
            setIntent(beamIcon);
            setDecide(deconstructionBeam);
        } else {
            int players = 0;
            for (int x = 0; x < 3; ++x) {
                if (!map.isEmpty(x, getYGrid())) {
                    players++;
                }
            }
            //The chance of attacking is based on how many players are in the same row,
            //More players means higher chances
            if (Math.random() < 0.5 * players) {
                setIntent(beamIcon);
                setDecide(protoCannon);
            } else {
                setIntent(moveIcon);
                setDecide(seek);
            }
        }
    }

    /**
     * [act]
     * Lets the enemy perform an action using an ability.
     * If the Deconstruction Beam is used, it will try to prioritize targets closer to it
     * @param map the map that the entities are in
     */
    @Override
    public void act(JointMap map) {
        if (getDecide().equals(seek)) {
            selectRowWithMostPlayers(map, seek);
        } else {
            selectRandomTile(map, getDecide());
            if (getDecide().equals(deconstructionBeam)) {
                setTargetedX(2);
                setTargetedY(getYGrid());
            }
        }
        getDecide().resetCooldown();
    }


}
