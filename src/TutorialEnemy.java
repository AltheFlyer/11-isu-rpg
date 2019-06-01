/**
 * [TutorialEnemy.java]
 * A basic enemy that alternates between attacking and moving
 * @version 1.0
 * @author Allen Liu
 * @since May 31, 2019
 */
public class TutorialEnemy extends Enemy {

    Icon attackIcon;
    Icon moveIcon;
    int turn;

    Ability attackAbility;
    Ability moveAbility;

    TutorialEnemy() {
        super(10, "Annoying Peon",
                new Ability[] {
                        new SingleAbility("Basic Attack", "Deals damage to a single target in the same row.",
                        0, 2, 6, 0, 1, 10, true, false
                        ),
                        new BasicMoveAbility("Rest", "This ability is useless!.",
                                0, 2,0)
                });

        attackIcon = new Icon("assets/icons/sword.png");
        attackIcon.setName("Basic Attack");
        moveIcon = new Icon("assets/icons/test.png");
        moveIcon.setName("Useless!");
        turn = 1;

        attackAbility = getAbility(0);
        moveAbility = getAbility(1);

        setIntent(attackIcon);
        setDecide(attackAbility);
    }

    @Override
    public void decide(JointMap map) {
        this.turn++;
        //Attacks on odd turns, moves on even turns
        System.out.println(turn);
        if (turn % 2 == 1) {
            setIntent(attackIcon);
            setDecide(attackAbility);
        } else {
            setIntent(moveIcon);
            setDecide(moveAbility);
        }
    }
}
