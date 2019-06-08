/**
 * [StatModifier.java]
 *
 * @version 1.0
 * @author Allen Liu
 * @since May 30, 2019
 */
public class StatModifier extends StatusEffect {

    //As a percent
    double attackMod;


    public StatModifier(int attackMod, int duration) {
        super(null, "assets/icons/test", "Stat Modifier", "Changes up stats", 0, 0, duration);


    }

    @Override
    public void triggerEffect(JointMap map) {

    }

    @Override
    public void stack(StatusEffect effect) {

    }

    @Override
    public StatusEffect spread() {
        return null;
    }
}
