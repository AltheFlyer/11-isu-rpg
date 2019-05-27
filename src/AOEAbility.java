public class AOEAbility extends Ability{
    AOEAbility(String name, int xRange, int yRange, int xAOE, int yAOE, int status, double damage, boolean enemyTarget, boolean friendTarget){
        super (name, xRange, yRange, status, damage, enemyTarget, friendTarget);
        setXAOE(xAOE);
        setYAOE(yAOE);
    }
}
