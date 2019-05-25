public class AOEAbility extends Ability{
    AOEAbility(String name, int xRange, int yRange, int xAOE, int yAOE, int status, double damage){
        super (name, xRange, yRange, status, damage);
        setXAOE(xAOE);
        setYAOE(yAOE);
    }
}
