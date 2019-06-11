/**
 * [StatusEffect.java]
 * represents a status that can affect entities in game.
 * WARNING: Status Effects should only be spread or applied onto entities using the .spread() method if you are creating
 * statuses off of pre-fab objects. Otherwise, the status effect would pass by reference, which will lead to
 * unintended behaviour.
 * @version 1.1
 * @author Allen Liu
 * @since June 7, 2019
 */
abstract public class StatusEffect {

    private Icon icon;
    private int stacks;
    private int maxStacks;

    private int duration;


    public StatusEffect(String iconName, String name, String description, int stacks, int maxStacks, int duration) {
        icon = new Icon(iconName);
        icon.setName(name);
        icon.setDescription(description);

        this.stacks = stacks;
        this.maxStacks = maxStacks;
        this.duration = duration;
    }

    /**
     * [isActiveImmediately]
     * whether the status effect is active the moment it is applied
     * @return boolean, whether the effect is applied immediately
     */
    abstract public boolean isActiveImmediately();

    /**
     * [triggerEffect]
     * triggers the status effect, should be called at the end of turns except for passives/'active immediately' effects
     * @param map the jointmap that
     */
    abstract public void triggerEffect(JointMap map, Entity affected);

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

    public Icon getIcon() {
        return icon;
    }

}
