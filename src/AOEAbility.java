public class AOEAbility extends Ability{
    AOEAbility(String name, int xRange, int yRange, int xAOE, int yAOE, int status, double damage, boolean enemyOnly){
        super (name, xRange, yRange, status, damage, enemyOnly);
        setXAOE(xAOE);
        setYAOE(yAOE);
    }
}
