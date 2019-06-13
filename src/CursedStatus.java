/**
 * [CursedStatus.java]
 * a status effect that damages the entity at the end of each turn
 * @version 1.0
 * @author Allen Liu
 * @since May 31, 2019
 */
public class CursedStatus extends StatusEffect {

    public CursedStatus(int stacks) {
        super("assets/icons/cursed.png", "Cursed!", "Terrible curse...",
                stacks, 5, 3, false);
        this.getIcon().setName("Cursed x" + this.getStacks() + "!");
    }

    /**
     * [triggerEffect]
     * triggers the status effect, dealing damage to the player
     * @param map the jointmap that the affected is on
     * @param affected the player with the status effect
     */
    @Override
    public void triggerEffect(JointMap map, Entity affected) {
        affected.damageEntity(getStacks());
    }

    /**
     * [stack]
     * defines stacking behaviour - curse status stacks damage, and refreshes duration when it does so
     * @param effect the status effect to attempt to stack
     */
    @Override
    public void stack(StatusEffect effect) {
        this.setStacks(effect.getStacks() + this.getStacks());
        this.getIcon().setName("Cursed x" + this.getStacks() + "!");
        this.setDuration(3);
    }

    /**
     * [spread]
     * creates a duplicate of the ability for use in status inflicting abilities
     * @return CursedStatus, a copy of the cursed status effect with a duration of 3
     */
    @Override
    public StatusEffect spread() {
        return new CursedStatus(this.getStacks());
    }
}
