/**
 * [DissectedStatus.java]
 * a status effect that will empower certain abilities
 * @version 1.0
 * @author Kevin Liu
 * @since June 9, 2019
 */
public class DissectedStatus extends StatusEffect {

    public DissectedStatus(int stacks) {
        super("assets/icons/dissect.png", "Dissected!", "This unit is being dissected",
                stacks, 15, 7, false);
        this.getIcon().setName("Dissected x" + this.getStacks() + "!");
    }

    /**
     * [triggerEffect]
     * triggers the status effect, should be called at the end of turns except for passives/'active immediately' effects
     * @param map the jointmap that
     */
    @Override
    public void triggerEffect(JointMap map, Entity affected) {
        //affected.damageEntity(getStacks());
    }

    /**
     * [stack]
     * combines the effect of this status and another status of the same type:
     * e.g a -15% attack debuff and a -30% attack debuff can combine to become a -45% attack debuff
     * a special method is used to allow for unique interactions
     * @param effect the status effect to attempt to stack
     */
    @Override
    public void stack(StatusEffect effect) {
        this.setStacks(effect.getStacks() + this.getStacks());
        this.getIcon().setName("Dissected x" + this.getStacks() + "!");
        this.setDuration(7);
    }

    /**
     * [spread]
     * generates a duplicate of the status effect, for use in abilities that can inflict statuses
     */
    @Override
    public StatusEffect spread() {
        return new DissectedStatus(this.getStacks());
    }
}