/**
 * [DissectedStatus.java]
 * a status effect that will empower certain abilities
 * @version 1.0
 * @author Allen Liu
 * @since May 31, 2019
 */
public class DissectedStatus extends StatusEffect {

    public DissectedStatus(int stacks) {
        super("assets/icons/test.png", "Dissected!", "This unit is being dissected",
                stacks, 15, 3);
        this.getIcon().setName("Dissected x" + this.getStacks() + "!");
    }

    @Override
    public boolean isActiveImmediately() {
        return false;
    }

    @Override
    public void triggerEffect(JointMap map, Entity affected) {
        //affected.damageEntity(getStacks());
    }

    @Override
    public void stack(StatusEffect effect) {
        this.setStacks(effect.getStacks() + this.getStacks());
        this.getIcon().setName("Dissected x" + this.getStacks() + "!");
        this.setDuration(3);
    }

    @Override
    public StatusEffect spread() {
        return new DissectedStatus(this.getStacks());
    }
}