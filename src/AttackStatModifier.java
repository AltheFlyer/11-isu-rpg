/**
 * [AttackStatModifier.java]
 * An additive % attack buff/debuff
 * @version 1.0
 * @author Allen Liu
 * @since June 12, 2019
 */
public class AttackStatModifier extends StatusEffect {

    private Icon positiveAttack;
    private Icon negativeAttack;

    /**
     * Creates an additive percent modification on attack
     * @param attackMod modification of attack as a whole number percentage
     */
    public AttackStatModifier(int attackMod) {
        super("assets/icons/test.png", "Attack % Modifier", "Modifies attack", attackMod, 0, -1, true);

        positiveAttack = new Icon("assets/icons/attack_buff.png", "Attack up", "");
        negativeAttack = new Icon("assets/icons/attack_debuff.png", "Attack down", "");

        if (getStacks() > 0) {
            setIcon(positiveAttack);
            positiveAttack.setDescription("Increases attack by " + getStacks() + "%");
        } else if (getStacks() < 0) {
            setIcon(negativeAttack);
            negativeAttack.setDescription("Decreases attack by " + (getStacks() * -1) + "%");
        }
    }

    @Override
    public void triggerEffect(JointMap map, Entity affected) {
        affected.setAttack(affected.getBaseAttack() * ((100 + getStacks()) / 100.0));
    }

    @Override
    public void stack(StatusEffect effect) {
        //Sum of buffs/debuffs
        this.setStacks(this.getStacks() + effect.getStacks());

        if (getStacks() > 0) {
            setIcon(positiveAttack);
            positiveAttack.setDescription("Increases attack by " + getStacks() + "%");
        } else if (getStacks() < 0) {
            setIcon(negativeAttack);
            negativeAttack.setDescription("Decreases attack by " + (getStacks() * -1) + "%");
        } else {
            //Remove unecessary buff/debuff
            setDuration(0);
        }
    }

    @Override
    public StatusEffect spread() {
        return new AttackStatModifier(getStacks());
    }
}
