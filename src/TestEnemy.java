import utils.AnimatedSprite;

public class TestEnemy extends Enemy {

    Icon medicIcon;
    Icon attackIcon;

    TestEnemy() {
        super(10, "TestEnemy",
                new Ability[]
                        {new SingleAbility(new AnimatedSprite("spritesheets/basicAttack.png", 1,5,250),"basic","A basic attack that hits a random target in front",0,0,6,0,1,2,true, false),
                new SingleAbility(null,"healSelf","A basic self heal",0,0,0,0,1,-3,false, true)}
                );

        medicIcon = new Icon("assets/icons/medic.png", "Medic", "This enemy intends to restore health to itself.");
        attackIcon = new Icon("assets/icons/sword.png", "Attack", "A basic attack that will deal damage to a player.");
    }

    @Override
    public void decide(JointMap map) {
        //For testing/theory purposes only...
        if (this.getHealth() < this.getMaxHealth() / 2) {
            //A heal on itself
            setIntent(medicIcon);
            setDecide(abilities[1]);
        } else {
            setIntent(attackIcon);
            setDecide(abilities[0]);
        }
    }

    @Override
    public void act(JointMap map) {
        selectRandomTile(map, getDecide());
    }
}
