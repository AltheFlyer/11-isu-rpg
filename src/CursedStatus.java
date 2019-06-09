/**
 * [CursedStatus.java]
 * a status effect that damages the entity at the end of each turn
 * @version 1.0
 * @author Allen Liu
 * @since May 31, 2019
 */
public class CursedStatus extends StatusEffect {

    public CursedStatus(int stacks) {
        super("assets/icons/test.png", "Cursed!", "Terrible curse...",
                stacks, 5, 3);
        this.getIcon().setName("Cursed x" + this.getStacks() + "!");
    }

    @Override
    public void triggerEffect(JointMap map, Entity affected) {
        affected.damageEntity(getStacks());
    }

    @Override
    public void stack(StatusEffect effect) {
        System.out.println(effect.getStacks());
        System.out.println(this.getStacks());

        this.setStacks(effect.getStacks() + this.getStacks());
        System.out.println(this.getStacks());

        this.getIcon().setName("Cursed x" + this.getStacks() + "!");

        this.setDuration(3);
    }

    @Override
    public StatusEffect spread() {
        return new CursedStatus(this.getStacks());
    }
}
