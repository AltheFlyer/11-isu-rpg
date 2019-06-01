public class CursedStatus extends StatusEffect {

    public CursedStatus(Entity target, int stacks) {
        super(target, "assets/icons/test.png", "Cursed!", "Terrible curse...",
                stacks, 5, 3);
    }

    @Override
    public void triggerEffect(JointMap map) {
        getAffected().damageEntity(getStacks() * 1);
    }

    @Override
    public void stack(StatusEffect effect) {
        this.setStacks(effect.getStacks() + this.getStacks());
        this.setDuration(3);
    }
}
