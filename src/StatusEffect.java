/**
 * [StatusEffect.java]
 * represents a status that can affect entities in game.
 * @version 1.0
 * @author Allen Liu
 * @since May 30, 2019
 */
abstract public class StatusEffect {

    private Icon icon;
    private int stacks;
    private int maxStacks;

    private int duration;

    private Entity affected;

    public StatusEffect(Entity affected, String iconName, String name, String description, int stacks, int maxStacks, int duration) {
        this.affected = affected;
        icon = new Icon(iconName);
        icon.setName(name);
        icon.setDescription(description);

        this.stacks = stacks;
        this.maxStacks = maxStacks;
        this.duration = duration;
    }

    /**
     * [triggerEffect]
     * triggers the status effect, should be called at the end of turns
     * @param map the jointmap that
     */
    abstract public void triggerEffect(JointMap map);

    /**
     * [stack]
     * combines the effect of this status and another status of the same type:
     * e.g a -15% attack debuff and a -30% attack debuff can combine to become a -45% attack debuff
     * a special method is used to allow for unique interactions
     * @param effect the status effect to attempt to stack
     */
    abstract public void stack(StatusEffect effect);

    /**
     * [tickDuration]
     * decreases the duration of the status effect
     */
    public void tickDuration() {
        duration--;
    }

    /**
     * [spread]
     * generates a duplicate of the status effect, for use in abilities that can inflict statuses
     */
    abstract public StatusEffect spread();

    //Access time!
    public int getStacks() {
        return stacks;
    }

    public void setStacks(int stacks) {
        this.stacks = stacks;
    }

    public int getMaxStacks() {
        return maxStacks;
    }

    public void setMaxStacks(int maxStacks) {
        this.maxStacks = maxStacks;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Entity getAffected() {
        return affected;
    }

    public void setAffected(Entity affected) {
        this.affected = affected;
    }

    public Icon getIcon() {
        return icon;
    }

}
