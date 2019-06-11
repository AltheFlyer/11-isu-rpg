/**
 * [AttackStatModifier.java]
 * An additive % attack buff/debuff
 * @version 1.0
 * @author Allen Liu
 * @since May 30, 2019
 */
public class AttackStatModifier extends StatusEffect {

    /**
     * @param attackMod modification of attack as a whole number percentage
     */
    public AttackStatModifier(int attackMod) {
        super("assets/icons/test.png", "Attack % Modifier", "Modifies attack", 0, 0, -1);
        this.setStacks(attackMod);
    }

    @Override
    public boolean isActiveImmediately() {
        return true;
    }

    @Override
    public void triggerEffect(JointMap map, Entity affected) {
        affected.setAttack(affected.getBaseAttack() * ((100 + getStacks()) / 100.0));
    }

    @Override
    public void stack(StatusEffect effect) {
    }

    @Override
    public StatusEffect spread() {
        return new AttackStatModifier(getStacks());
    }
}
