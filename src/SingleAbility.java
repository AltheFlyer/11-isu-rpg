public class SingleAbility extends Ability {
    SingleAbility(String name, int xRange, int yRange, int status, double damage, boolean enemyOnly){
        super (name, xRange, yRange, status, damage, enemyOnly);
        setXAOE(0);
        setYAOE(0);
    }                                                                
}
