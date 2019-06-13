/**
 * [DefenceStatModifier]
 * An additive non-percent modifier on defence
 * @version 1.0
 * @author Allen Liu
 * @since June 12, 2019
 */
public class DefenceStatModifier extends StatusEffect {

    private Icon positiveDefence;
    private Icon negativeDefence;

    /**
     * Creates an additive (non-percentage modifier) for defence
     * @param bonusDefence the amount of defence the status grants, as a raw value (defence itself is a % reduction)
     */
    public DefenceStatModifier(int bonusDefence) {
        super("assets/icons/test.png", "Defence modifier", "", bonusDefence, 100, -1, true);

        positiveDefence = new Icon("assets/icons/defence_buff.png", "Defence up", "");
        negativeDefence = new Icon("assets/icons/defence_debuff.png", "Defence down", "");

        if (getStacks() > 0) {
            setIcon(positiveDefence);
            positiveDefence.setDescription("Grants " + getStacks() + "% damage resistance");
        } else if (getStacks() < 0) {
            setIcon(negativeDefence);
            negativeDefence.setDescription("Grants " + (getStacks() * -1) + "% damage weakness");
        }
    }

    /**
     * [triggerEffect]
     * triggers the status effect, should be called at the end of turns except for passives/'active immediately' effects
     *
     * @param map      the jointmap that the affected is on
     * @param affected the entity that has this status effect
     */
    @Override
    public void triggerEffect(JointMap map, Entity affected) {
        affected.setDefence(affected.getBaseDefence() + ((getStacks()) / 100.0));
        System.out.println("DEF:" + affected.getDefence());
    }

    /**
     * [stack]
     * combines the effect of this status and another status of the same type:
     * numerous defence modifiers sum to create one final modifier (e.g -10 + 20 = 10% def)
     * a special method is used to allow for unique interactions
     *
     * @param effect the status effect to attempt to stack
     */
    @Override
    public void stack(StatusEffect effect) {
        //Sum of buffs/debuffs
        System.out.println("Now: " + getStacks() + " New: " + effect.getStacks());

        this.setStacks(this.getStacks() + effect.getStacks());


        //Prevent > 100% defence
        if (this.getStacks() > this.getMaxStacks()) {
            this.setStacks(this.getMaxStacks());
        }

        System.out.println("DEF stack:" + this.getStacks());

        if (getStacks() > 0) {
            setIcon(positiveDefence);
            positiveDefence.setDescription("Grants " + getStacks() + "% damage resistance");
        } else if (getStacks() < 0) {
            setIcon(negativeDefence);
            negativeDefence.setDescription("Grants " + (getStacks() * -1) + "% damage weakness");
        } else {
            //Remove unecessary buff/debuff
            setDuration(0);
        }
    }

    /**
     * [spread]
     * generates a duplicate of the status effect, for use in abilities that can inflict statuses
     */
    @Override
    public StatusEffect spread() {
        return new DefenceStatModifier(this.getStacks());
    }
}
