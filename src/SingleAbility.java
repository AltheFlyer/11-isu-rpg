public class SingleAbility extends Ability {
    SingleAbility(String name, int xRange, int yRange, int status, double damage, boolean enemyTarget, boolean friendTarget){
        super (name, xRange, yRange, status, damage, enemyTarget, friendTarget);
        setXAOE(0);
        setYAOE(0);
    }                                                                
}
