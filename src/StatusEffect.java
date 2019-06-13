/**
 * [StatusEffect.java]
 * represents a status that can affect entities in game.
 * WARNING: Status Effects should only be spread or applied onto entities using the .spread() method if you are creating
 * statuses off of pre-fab objects. Otherwise, the status effect would pass by reference, which will lead to
 * unintended behaviour.
 * @version 1.2
 * @author Allen Liu
 * @since June 12, 2019
 */
abstract public class StatusEffect {

    private Icon icon;
    private int stacks;
    private int maxStacks;

    private int duration;
    private boolean isActiveImmediately;

    public StatusEffect(String iconName, String name, String description, int stacks, int maxStacks, int duration, boolean isActiveImmediately) {
        icon = new Icon(iconName);
        icon.setName(name);
        icon.setDescription(description);

        this.stacks = stacks;
        this.maxStacks = maxStacks;
        this.duration = duration;
        this.isActiveImmediately = isActiveImmediately;
    }

    /**
     * [getIsActiveImmediately]
     * whether the status effect is active the moment it is applied
     * @return boolean, whether the effect is applied immediately
     */
    public boolean getIsActiveImmediately() {
        return isActiveImmediately;
    }

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

    /**
     * [getStacks]
     * gets the number of status stacks
     * @return int stacks, the number of status stacks
     */
    public int getStacks() {
        return stacks;
    }

    /**
     * [setStacks]
     * sets the number of status stacks
     * @param stacks the new number of status stacks
     */
    public void setStacks(int stacks) {
        this.stacks = stacks;
    }

    /**
     * [getMaxStacks]
     * gets the maximum number of status stacks for the status
     * @return int maxStacks, the maximum stacks for the status
     */
    public int getMaxStacks() {
        return maxStacks;
    }

    /**
     * [getDuration]
     * gets the duration of the status (note -1 should be treated as infinite)
     * @return int duration, how many turns the status should last
     */
    public int getDuration() {
        return duration;
    }

    /**
     * [setDuration]
     * sets the duration of the status (note -1 should be treated as infinite)
     * @param duration, how many turns the status should last
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * [getIcon]
     * gets the status effect's associated icon
     * @return Icon, the icon for the status
     */
    public Icon getIcon() {
        return icon;
    }

    /**
     * [setIcon]
     * sets the status effect's associated icon
     * @param ico the new icon for the status
     */
    public void setIcon(Icon ico) {
        this.icon = ico;
    }
}
