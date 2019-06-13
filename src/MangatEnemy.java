import utils.AnimatedSprite;

/**
 * [MangatEnemy.java]
 * Mr. Mangat, as an enemy in this game featuring cs themed abilities.
 * @version 1.0
 * @author Allen Liu
 * @since June 13, 2019
 */
public class MangatEnemy extends Enemy {

    Ability tracking;
    Ability forLoop;
    Ability refactor;
    Ability roast;

    Icon attackIcon;
    Icon medicIcon;

    /**
     * Creates a Mangat enemy at a specific location
     *
     * @param x         the x Location on the grid that the enemy is located on
     * @param y         the y location on the grid that the enemy is located
     */
    MangatEnemy(int x, int y) {
        super(x, y, 1000, 40, 0, "Mr. Mangat",
                new AnimatedSprite("spritesheets/mangat.png", 1, 5, 500),
                new Ability[] {
                        new BasicMoveAbility("Tracking", "PASSIVE: this ability is always used after each turn.",
                                0, 0, 2),
                        new AbilityPair(new AnimatedSprite("spritesheets/binary_attack.png", 3, 6, 100),
                                "For loop", "Hits all players in a column. Deals additional damage to " +
                                "anyone in the same row.", 0, 2,
                                new AOEAbility(null, "", "", 0, 0,
                                        3, 0, 0, 2, 0, 0.25, true, false),
                                new SingleAbility(null, "", "", 0, 0,
                                        3, 0, 0, 1, true, false)
                                ),
                        new AbilityPair(new AnimatedSprite("spritesheets/binary_attack.png", 3, 6, 100),
                                "Refactor", "Boosts attack and defence.", 0, 100,
                                new StatusAbility(new AttackStatModifier(25), null, "", "",
                                        0, 0, 0, 0, false, true),
                                new StatusAbility(new DefenceStatModifier(10), null, "", "",
                                        0, 0, 0, 0, false, true)
                                ),
                        new AOEAbility(new AnimatedSprite("spritesheets/roast.png", 1, 2, 500),
                                "Crowd Roaster", "Roasts the people at the front of the class- hits only " +
                                "the rightmost player column.", 0, 0, 4, 1, 0, 1,
                                0, 1, true, false),
                });

        tracking = getAbility(0);
        forLoop = getAbility(1);
        refactor = getAbility(2);
        roast = getAbility(3);

        attackIcon = new Icon("assets/icons/sword.png", "Attack", "This enemy intends to attack.");
        medicIcon = new Icon("assets/icons/medic.png", "Recovery", "This enemy intends to buff up.");
    }

    /**
     * [decide]
     * generates a next move (Ability and intent) - that the player can then react to (should be overriden).
     * Uses refactor when below 750 health,
     * uses roast or for loop randomly depending on how crowded the right side row is.
     * @param map the map that the entities are in
     */
    @Override
    public void decide(JointMap map) {
        if ((getHealth() < 750) && (refactor.getCurrentCooldown() == 0)) {
            setDecide(refactor);
            setIntent(medicIcon);
        } else {
            //get number of players in 2nd column (counting from 0 from the left)
            int playerCount = 0;
            for (int y = 0; y < 3; ++y) {
                if (!map.isEmpty(2, y)) {
                    playerCount++;
                }
            }
            if (Math.random() < 0.5 * playerCount) {
                setDecide(roast);
            } else {
                setDecide(forLoop);
            }
            setIntent(attackIcon);
        }

    }

    /**
     * [act]
     * lets the enemy perform an action using an ability, note that Mangat will ALWAYS perform a check to use tracking.
     *
     * @param map the map that the entities are in
     */
    @Override
    public void act(JointMap map) {
        if (getDecide().equals(forLoop)) {
            //Optimize for damage, but not kills
            int attackX = 0;
            int attackY = getYGrid();
            int optimum = 0;

            //For each column, loop through players to see how much damage can be done
            for (int x = 0; x < 3; ++x) {
                int attackPower = 0;
                for (int y = 0; y < 3; ++y) {
                    if (!map.isEmpty(x, getYGrid())) {
                        attackPower += 1;
                    }
                }
                //The for loop attack is very powerful against targets in the same row, so it has a higher weighting
                if (!map.isEmpty(x, getYGrid())) {
                    attackPower += 5;
                }
                if (attackPower > optimum) {
                    optimum = attackPower;
                    attackX = x;
                }
            }

            forLoop.indicateValidTiles(map);
            setTargetedX(attackX);
            setTargetedY(attackY);
            forLoop.action(map, attackX, attackY);
        } else if (getDecide().equals(roast)) {
            //ALWAYS hits right column of player side
            setTargetedX(2);
            setTargetedY(1);
            roast.action(map, 2, 1);
        } else {
            selectRandomTile(map, getDecide());
        }

        getDecide().resetCooldown();

        int optimalRow = 0;
        int maxRowPlayers = 0;

        for (int y = 0; y < 3; ++y) {
            int playerCount = 0;
            for (int x = 0; x < 3; ++x) {
                if (!map.isEmpty(x, y)) {
                    playerCount++;
                    if (playerCount > maxRowPlayers) {
                        optimalRow = y;
                        maxRowPlayers = playerCount;
                    }
                }
            }
        }

        tracking.indicateValidTiles(map);

        if ((getXGrid() > 3) && (Math.abs(getYGrid() - optimalRow) < 2)) {
            tracking.action(map, getXGrid() - 1, optimalRow);
        } else {
            tracking.action(map, getXGrid(), optimalRow);
        }
    }
}
