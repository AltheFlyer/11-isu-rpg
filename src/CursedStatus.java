/**
 * [CursedStatus.java]
 * a status effect that damages the entity at the end of each turn
 * @version 1.0
 * @author Allen Liu
 * @since May 31, 2019
 */
public class CursedStatus extends StatusEffect {

    public CursedStatus(Entity target, int stacks) {
        super(target, "assets/icons/test.png", "Cursed!", "Terrible curse...",
                stacks, 5, 3);
    }

    @Override
    public void triggerEffect(JointMap map) {
        getAffected().damageEntity(getStacks());
    }

    @Override
    public void stack(StatusEffect effect) {
        this.setStacks(effect.getStacks() + this.getStacks());
        this.setDuration(3);
    }
}
