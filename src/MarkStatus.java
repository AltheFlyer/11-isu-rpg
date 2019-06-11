public class MarkStatus extends StatusEffect {

    public MarkStatus(int stacks) {
        super("assets/icons/test.png", "Marked!", "This target is marked",
                stacks, 1, 2);
        this.getIcon().setName("Marked!");
    }

    @Override
    public void triggerEffect(JointMap map, Entity affected) {}

    @Override
    public void stack(StatusEffect effect) {
        this.setStacks(effect.getStacks() + this.getStacks());
        this.getIcon().setName("Marked!");
        this.setDuration(2);
    }

    @Override
    public StatusEffect spread() {
        return new MarkStatus(this.getStacks());
    }
}
