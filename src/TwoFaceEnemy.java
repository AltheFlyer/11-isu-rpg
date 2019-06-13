import utils.AnimatedSprite;

/**
 * [TwoFaceEnemy]
 * a drama mask that starts as a happy face but switches to a sad face after taking enough hits
 * @version 1.0
 * @author Allen Liu
 * @since June 12, 2019
 */
public class TwoFaceEnemy extends Enemy {

    private AnimatedSprite happyFace;
    private AnimatedSprite sadFace;

    private int hits;

    private Ability happyOrb;
    private Ability sadOrb;
    private Ability happyBuff;
    private Ability sadBuff;
    private Ability lonely;

    private Icon attackIcon;
    private Icon attackBuffIcon;
    private Icon defenceBuffIcon;
    private Icon warning;

    /**
     * Creates an enemy entity which doesn't need energy
     * @param x         the x Location on the grid that the enemy is located on
     * @param y         the y location on the grid that the enemy is located
     */
    TwoFaceEnemy(int x, int y) {
        super(x, y, 1000, 30, 0, "Two Face", new AnimatedSprite("spritesheets/happy_mask.png", 1, 7, 50),
                new Ability[] {
                        new FirstInRowAbility(new AnimatedSprite("spritesheets/red_orb.png", 1, 3, 100),
                        "Happy orb", "Deals damage to one target in the row. Only usable while happy.",
                                0, 0, 10, 1, true, false
                        ),
                        new StatusAbility(new AttackStatModifier(20), new AnimatedSprite("spritesheets/red_orb.png", 1, 3, 100),
                                "Happiness!", "Bonus attack! - only usable while happy.", 0, 0,
                                0, 0, false, true),

                        new AbilityPair(new AnimatedSprite("spritesheets/blue_orb.png", 1, 3, 100),
                                "Sad orb", "Deals damage to one target in the row and debuffs defence. Only usable while sad.",
                                0, 0,
                                new FirstInRowAbility(null, "", "",
                                0, 0, 20, 1, true, false),
                                new StatusAbility(new DefenceStatModifier(-20), null,
                                        "", "", 0, 0,
                                        0, 0, true, false)
                                ),


                        new StatusAbility(new DefenceStatModifier(20), new AnimatedSprite("spritesheets/blue_orb.png", 1, 3, 100),
                                "Depression", "Bonus defence... only usable while sad.", 0, 0,
                                0, 0, false, true),

                        new AOEAbility(new AnimatedSprite("spritesheets/blue_orb.png", 1, 3, 100),
                                "Loneliness", "Damages ALL players... only usable while sad, and while no players are in the same row as it.",
                                0, 2, 6, 1, 3, 3, 50, 0.8,
                                true, false)
                });

        happyOrb = getAbility(0);
        happyBuff = getAbility(1);
        sadOrb = getAbility(2);
        sadBuff = getAbility(3);
        lonely = getAbility(4);

        happyFace = new AnimatedSprite("spritesheets/happy_mask.png", 1, 7, 50);
        sadFace = new AnimatedSprite("spritesheets/sad_mask.png", 1, 7, 60);

        attackIcon = new Icon("assets/icons/sword.png", "Attack", "This enemy intends to attack a player.");
        attackBuffIcon = new Icon("assets/icons/attack_buff.png", "Attack Buff", "This enemy intends to buff its attack.");
        defenceBuffIcon = new Icon("assets/icons/defence_buff.png", "Defence Buff", "This enemy intends to buff its defence.");
        warning = new Icon("assets/icons/test.png", "DANGER!!!", "The mask feels lonely! Get someone in the same row as it " +
                "or it will do massive damage to all players!");

        hits = 0;
    }

    /**
     * [decide]
     * generates a next move (Ability and intent) - that the player can then react to (should be overriden).
     * Uses different abilities based on how many hits have been taken (switching it from happy to sad face),
     * where sad face is meant to be stronger
     * @param map the map that the entities are in
     */
    @Override
    public void decide(JointMap map) {
        if (hits >= 10) {
            int playersInRow = 0;
            for (int x = 0; x < 3; ++x) {
                if (!map.isEmpty(x, getYGrid())) {
                    playersInRow++;
                }
            }
            if ((playersInRow == 0) && (lonely.getCurrentCooldown() == 0)) {
                setDecide(lonely);
                setIntent(warning);
            } else if ((Math.random() < 0.5) || (getDefence() >= 0.6)) {
                setDecide(sadOrb);
                setIntent(attackIcon);
            } else {
                setDecide(sadBuff);
                setIntent(defenceBuffIcon);
            }
        } else {
            if (Math.random() < 0.5) {
                setDecide(happyOrb);
                setIntent(attackIcon);
            } else {
                setDecide(happyBuff);
                setIntent(attackBuffIcon);
            }
        }
    }

    /**
     * [act]
     * lets the enemy perform an action using an ability, this one is special in that the lonliness ability
     * will be canceled if anyone is in the same row as it (for fun gameplay)
     * @param map the map that the entities are in
     */
    @Override
    public void act(JointMap map) {
        if (getDecide().equals(lonely)) {
            int playersInRow = 0;
            for (int x = 0; x < 3; ++x) {
                if (!map.isEmpty(x, getYGrid())) {
                    playersInRow++;
                }
            }
            //Note how nothing happens if no players are in the row... this is intended to reward the movement
            if (playersInRow == 0) {
                getDecide().action(map, 1, 1);
                getDecide().resetCooldown();
            }
        } else {
            selectRandomTile(map, getDecide());
        }
    }

    /**
     * [damageEntity]
     * runs whenever the entity is damaged, its also used to count hits for the sad face transform
     * @param damage the amound of damage dealt to an entity
     */
    @Override
    public void damageEntity(double damage) {
        super.damageEntity(damage);
        hits++;
        if (hits == 10) {
            setAnimation(sadFace);

            //Overriding intents... a surprise for the unassuming actor
            setDecide(sadOrb);
            setIntent(attackIcon);
        }
    }
}
