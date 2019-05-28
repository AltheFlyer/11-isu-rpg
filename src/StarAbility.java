import java.awt.*;

public class StarAbility extends Ability {
    StarAbility(String name, int xRange, int yRange, int status, double damage, boolean enemyTarget, boolean friendTarget){
        super (name, xRange, yRange, status, damage, enemyTarget, friendTarget);
    }

    public boolean action(JointMap jointMap, int i, int j){
        if (jointMap.getTargetable(i, j)) {
            for (int k = 0; k < 3; k++){
                for (int l = 0; l < 6; l++){
                    //Yeah might need to revamp Single and AOE ability so one can do empty tiles, one cannot do that
                    if (Math.abs(k-j) + Math.abs(l-i) <= 1 && jointMap.tileExists(l,k)){
                        if (getFriendTarget() && jointMap.getTileType(l,k) == jointMap.getTileType(getEntitySource().getXGrid(),getEntitySource().getYGrid())) {
                            jointMap.target(l, k, getDamage(), getStatus());
                        }
                        if (getEnemyTarget() && jointMap.getTileType(l,k) != jointMap.getTileType(getEntitySource().getXGrid(),getEntitySource().getYGrid())) {
                            jointMap.target(l, k, getDamage(), getStatus());
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    /**
     * [drawSelectedArea]
     * Draws the area that will be affected by an ability
     * @param g the graphics object to draw with
     */
    public void drawHoverAttack(int i, int j, Graphics g, JointMap jointMap) {
        //What to do here???
        int gridX = 323;
        int gridY = 108;

        int gridWidth = 120;
        int gridHeight = 120;

        int gridWidthSpace = 121;
        int gridHeightSpace = 121;
        for (int k = 0; k < 3; k++){
            for (int l = 0; l < 6; l++){
                //Yeah might need to revamp Single and AOE ability so one can do empty tiles, one cannot do that
                if (Math.abs(k-j) + Math.abs(l-i) <= 1){

                    if (jointMap.tileExists(l,k)){
                        if (getFriendTarget() && jointMap.getTileType(l,k) == jointMap.getTileType(getEntitySource().getXGrid(),getEntitySource().getYGrid())) {
                            g.setColor(Color.GREEN);
                            g.drawRect(gridX + gridWidthSpace * l, gridY + gridHeightSpace * k, gridWidth, gridHeight);
                        }
                        if (getEnemyTarget() && jointMap.getTileType(l,k) != jointMap.getTileType(getEntitySource().getXGrid(),getEntitySource().getYGrid())) {
                            g.setColor(Color.GREEN);
                            g.drawRect(gridX + gridWidthSpace * l, gridY + gridHeightSpace * k, gridWidth, gridHeight);
                        }
                    }
                }
            }
        }
    }

    public void indicateValidTiles(JointMap jointMap){
        int rangeAhead = getEntitySource().getXGrid() + getXRange();
        int rangeBehind = getEntitySource().getXGrid() - getXRange();
        int rangeDown = getEntitySource().getYGrid() + getYRange();
        int rangeUp = getEntitySource().getYGrid() - getYRange();

        for (int j = rangeUp; j <= rangeDown; j++) {
            for (int i = rangeBehind; i <= rangeAhead; i++) {
                if (jointMap.tileExists(i, j)) {
                    if (getFriendTarget() && jointMap.getTileType(i, j) == jointMap.getTileType(getEntitySource().getXGrid(),getEntitySource().getYGrid())) {
                        jointMap.indicate(i, j);
                        jointMap.isTargetable(i,j);
                    }
                    if (getEnemyTarget() && jointMap.getTileType(i, j) != jointMap.getTileType(getEntitySource().getXGrid(),getEntitySource().getYGrid())) {
                        jointMap.indicate(i, j);
                        jointMap.isTargetable(i,j);
                    }
                }
            }
        }
    }
}
